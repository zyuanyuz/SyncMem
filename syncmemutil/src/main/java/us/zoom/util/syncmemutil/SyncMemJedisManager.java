package us.zoom.util.syncmemutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author zyuanyuz
 * @since 2019/11/4 21:38
 */
public class SyncMemJedisManager extends AbstractSyncMemManager {
  private static final Logger logger = LoggerFactory.getLogger(SyncMemJedisManager.class);

  private Jedis pubJedis;

  public SyncMemJedisManager(JedisPool jedisPool, String channel) {
    this.channel = channel;
    this.pubJedis = jedisPool.getResource();
    Jedis subJedis = jedisPool.getResource();
    new Thread(
            () -> {
              while (true) {
                try {
                  subJedis.subscribe(
                      new SyncMemPubSubImpl(this.syncMemId, this.channel, this::handleMessage),
                      channel);
                } catch (Exception e) {
                  logger.error("sync mem : jedis subscribe with exception :{}", e.getMessage());
                }
              }
            })
        .start();
  }

  @Override
  public void publish(String consumerId, Object data) {
    if (this.consumerMap.containsKey(consumerId)) {
      logger.info(
          "sync mem : other {} instance consumer {} will be invoked", this.channel, consumerId);
      pubJedis.publish(
          this.channel,
          SyncMemJSONSerializer.getJsonMessage(
              new SyncMemProtocol<>(this.syncMemId, consumerId, data)));
    } else {
      logger.warn("sync mem : no consumerId {} exist", consumerId);
    }
  }

  @Override
  void handleMessage(String message) {
    String consumerId = SyncMemJSONSerializer.getConsumerId(message);
    SyncMemEntry entry = this.consumerMap.get(consumerId);
    Object data = SyncMemJSONSerializer.getData(message, entry.getDataType());
    logger.info("sync mem : consumer {} was invoked.", consumerId);
    entry.getConsumer().accept(data);
  }
}
