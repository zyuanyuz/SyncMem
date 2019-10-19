package zyz.zyuanyuz.syncmem.example.syncmemutil;

import com.alibaba.fastjson.JSONObject;
import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zyz.zyuanyuz.syncmem.example.syncmemutil.annotation.SyncMemMethod;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author George Yu
 * @since 2019/10/16 17:45
 */
public class SyncMemUtil {
  private final Logger logger = LoggerFactory.getLogger(SyncMemUtil.class);

  private StatefulRedisPubSubConnection<String, String> pubConnection;

  private StatefulRedisPubSubConnection<String, String> subConnection;

  // use to identify the SyncMemUtil
  private String uuid;

  // use to store the obj and method
  private Map<String, SyncMemEntry> entryMap = new HashMap<>();

  private String channel;

  public SyncMemUtil(RedisClient redisClient, String channel) {
    this.pubConnection = redisClient.connectPubSub();

    this.subConnection = redisClient.connectPubSub();
    this.subConnection.addListener(
        new SyncMemRedisPubSubImpl(channel, this::handleSyncMem)); // use this for callback
    this.subConnection.sync().subscribe(channel);

    this.uuid = UUID.randomUUID().toString();
    this.channel = channel;
  }

  /**
   * now just for singleton object to register,and it seem multi object operation is Pointless.
   *
   * @param o
   */
  public void register(Object o) {
    Method[] methods = o.getClass().getMethods();
    for (Method method : methods) {
      if (method.isAnnotationPresent(SyncMemMethod.class)) {
        String id = method.getAnnotation(SyncMemMethod.class).value();
        entryMap.put(id, new SyncMemEntry(id, o, method));
      }
    }
  }

  /**
   * use to publish data to channel
   *
   * @param methodId
   * @param data
   */
  public void syncMemPublish(String methodId, Object data) {
    syncMemPublish(methodId, data, data.getClass());
  }

  private void syncMemPublish(String methodName, Object data, Class<?> clazz) {
    SyncMemProtocol protocol = new SyncMemProtocol(this.uuid, methodName, data, clazz.getName());
    String jsonStr = JSONObject.toJSONString(protocol);
    logger.info("publish to channel:{} data:{}", this.channel, jsonStr);
    pubConnection.sync().publish(this.channel, jsonStr);
  }

  void handleSyncMem(String message) {
    logger.info("start handle sync message {},entry map now is {}", message, entryMap);
    try {
      SyncMemProtocol protocol = JSONObject.parseObject(message, SyncMemProtocol.class);
      logger.warn("protocol:{}", protocol);
      if (protocol
          .getSyncMemId()
          .equals(this.uuid)) { // receive the message this SyncMemUtil published
        return;
      }
      SyncMemEntry entry = entryMap.get(protocol.getMethodId());
      // method invoke need test
      logger.info("method ready to invoke is :{}", entry.getMethod().getName());
      entry.getMethod().invoke(entry.getObj(), protocol.getData());
    } catch (Exception e) {
      logger.error("handle sync mem failed with exception :{}", e.getMessage());
      e.printStackTrace();
    }
  }
}
