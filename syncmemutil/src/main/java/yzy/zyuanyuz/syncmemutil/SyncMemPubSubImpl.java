package yzy.zyuanyuz.syncmemutil;

import io.lettuce.core.pubsub.RedisPubSubListener;

import java.util.function.Consumer;

/**
 * @author yu
 * @since 2019/11/3 18:20
 */
public class SyncMemPubSubImpl implements RedisPubSubListener<String, String> {
  private Consumer<String> consumer;
  private String channel;
  private String syncMemId;

  public SyncMemPubSubImpl(Consumer<String> consumer, String channel, String syncMemId) {
    this.consumer = consumer;
    this.channel = channel;
    this.syncMemId = syncMemId;
  }

  @Override
  public void message(String channel, String message) {
    if (channel.equals(this.channel)
        && this.syncMemId.equals(SyncMemJSONSerializer.getSyncMemId(message))) {
      consumer.accept(message);
    }
  }

  @Override
  public void message(String pattern, String channel, String message) {
    // do nothing
  }

  @Override
  public void subscribed(String channel, long count) {
    // do nothing
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
