package zyz.zyuanyuz.syncmem.example.syncmemutil;

import com.alibaba.fastjson.TypeReference;
import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zyz.zyuanyuz.syncmem.example.syncmemutil.serializer.JsonSerializer;
import zyz.zyuanyuz.syncmem.example.syncmemutil.serializer.SyncMemSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

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

  // use to store the consumer method
  private Map<String, Consumer<Object>> methodMap = new HashMap<>();

  private String channel;

  private SyncMemSerializer serializer;

  public SyncMemUtil(RedisClient redisClient, String channel) {
    this.uuid = UUID.randomUUID().toString();
    this.channel = channel;

    this.pubConnection = redisClient.connectPubSub();

    this.subConnection = redisClient.connectPubSub();
    this.subConnection.addListener(
        new SyncMemRedisPubSubImpl(this.channel, this::handleSyncMem)); // use this for callback
    this.subConnection.sync().subscribe(channel);
    this.serializer = new JsonSerializer();
  }

  public void setSerializer(SyncMemSerializer serializer) {
    this.serializer = serializer;
  }

  public void register(String methodId, Consumer<Object> method) {
    methodMap.put(methodId, method);
  }

  public void syncMemPublish(String methodName, Object data, TypeReference<?> typeReference){

  }

  public void syncMemPublish(String methodName, Object data, Class<?>... clazzs) {
    SyncMemProtocol protocol = new SyncMemProtocol(this.uuid, methodName, data, clazzs);
    String jsonStr = serializer.serializeObject(protocol);
    logger.info("publish to channel:{} data:{}", this.channel, jsonStr);
    pubConnection.sync().publish(this.channel, jsonStr);
  }

  private void handleSyncMem(String message) {
    logger.info("start handle sync message:{}", message);
    try {
      SyncMemProtocol protocol = serializer.deserializeObject(message);
      if (protocol
          .getSyncMemId()
          .equals(this.uuid)) { // receive the message this SyncMemUtil published
        return;
      }
      // method invoke need test
      logger.info("method ready to invoke is :{}", protocol.getMethodId());
      // callback method consumer
      methodMap.get(protocol.getMethodId()).accept(protocol.getData());
    } catch (Exception e) {
      logger.error("handle sync mem failed with exception :{}", e.getMessage());
      e.printStackTrace();
    }
  }
}
