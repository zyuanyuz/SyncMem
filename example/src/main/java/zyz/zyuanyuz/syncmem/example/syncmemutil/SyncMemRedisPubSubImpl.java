package zyz.zyuanyuz.syncmem.example.syncmemutil;

import io.lettuce.core.pubsub.RedisPubSubListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

/**
 * @author George Yu
 * @since 2019/10/16 17:46
 */
public class SyncMemRedisPubSubImpl implements RedisPubSubListener<String, String> {
  private final Logger logger = LoggerFactory.getLogger(SyncMemRedisPubSubImpl.class);

  private String subChannel;

  private Consumer<String> consumer;

  SyncMemRedisPubSubImpl(String channel, Consumer<String> consumer) {
    this.subChannel = channel;
    this.consumer = consumer;
  }

  @Override
  public void message(String channel, String message) {
    if (this.subChannel.equals(channel)) {
      consumer.accept(message);
    }
  }

  @Override
  public void message(String pattern, String channel, String message) {
    logger.info("channel:{} receive message:{} with pattern:{}", this.subChannel, message, pattern);
  }

  @Override
  public void subscribed(String channel, long count) {
    if (this.subChannel.equals(channel)) {
      logger.info(
          "this service build a new instance with channel:{} get subscribed count:{}",
          channel,
          count);
    }
  }

  @Override
  public void psubscribed(String pattern, long count) {
    // do nothing
  }

  @Override
  public void unsubscribed(String channel, long count) {
    // do nothing
  }

  @Override
  public void punsubscribed(String pattern, long count) {
    // do nothing
  }
}
