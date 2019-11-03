package yzy.zyuanyuz.syncmemutil;

import com.alibaba.fastjson.TypeReference;
import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * @author yu
 * @since 2019/11/3 17:40
 */
public class SyncMemManager {
  /** record the consumer information for callback */
  private Map<String, SyncMemEntry> consumerMap = new ConcurrentHashMap<>();

  private String syncMemId = UUID.randomUUID().toString();
  private String channel;
  private StatefulRedisPubSubConnection<String, String> pubConnection;
  private StatefulRedisPubSubConnection<String, String> subConnection;

  public SyncMemManager(RedisClient redisClient, String channel) {
    this.channel = channel;
    this.pubConnection = redisClient.connectPubSub();
    this.subConnection = redisClient.connectPubSub();
    this.subConnection.addListener(
        new SyncMemPubSubImpl(this::handleMessage, channel, this.syncMemId));
    this.subConnection.sync();
  }

  public void register(String methodId, Consumer consumer, TypeReference dataTye) {
    consumerMap.put(methodId, new SyncMemEntry(consumer, dataTye));
  }

  public void publishMessage(String methodId, Object data) {
    this.pubConnection
        .sync()
        .publish(
            this.channel,
            SyncMemJSONSerializer.getMessage(new SyncMemProtocol<>(this.syncMemId, methodId, data)));
  }

  void handleMessage(String message) {
    String consumerId = SyncMemJSONSerializer.getConsumerId(message);
    SyncMemEntry entry = this.consumerMap.get(consumerId);
    Object data = SyncMemJSONSerializer.getData(message,entry.getDataType());
    entry.getConsumer().accept(data);
  }
}
