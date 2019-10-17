package zyz.zyuanyuz.syncmem.example.syncmemutil;

import com.alibaba.fastjson.JSONObject;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zyz.zyuanyuz.syncmem.example.syncmemutil.annotation.SyncMemMethod;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author George Yu
 * @since 2019/10/16 17:45
 */
public class SyncMemUtil {
  private final Logger logger = LoggerFactory.getLogger(SyncMemUtil.class);

  private StatefulRedisPubSubConnection<String, String> pubSubConnection;

  private Map<String, SyncMemEntry> entryMap = new HashMap<>();

  private String channel;

  public SyncMemUtil(
      StatefulRedisPubSubConnection<String, String> pubSubConnection, String channel) {
    this.pubSubConnection = pubSubConnection;
    this.pubSubConnection.addListener(
        new SyncMemRedisPubSubImpl(channel, this)); // use this for callback
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
    SyncMemProtocol protocol = new SyncMemProtocol(methodName, data, clazz);
    String jsonStr = JSONObject.toJSONString(protocol);
    logger.info("pub to channel {}", jsonStr);
    pubSubConnection.sync().publish(this.channel, jsonStr);
  }

  void handleSyncMem(String message) {
    logger.info("start handle sync message {}", message);
    SyncMemProtocol protocol;
    try {
      protocol = JSONObject.parseObject(message, SyncMemProtocol.class);
      SyncMemEntry entry = entryMap.get(protocol.getMethodId());
      // need test
      entry.getMethod().invoke(entry.getObj(), protocol.getDataClazz().cast(protocol.getData()));
    } catch (Exception e) {
      logger.error("Handle sync mem failed with exception :{}", e.getMessage());
    }
  }
}
