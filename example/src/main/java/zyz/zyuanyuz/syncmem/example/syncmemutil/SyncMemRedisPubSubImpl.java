package zyz.zyuanyuz.syncmem.example.syncmemutil;

import io.lettuce.core.pubsub.RedisPubSubListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author George Yu
 * @since 2019/10/16 17:46
 */
public class SyncMemRedisPubSubImpl implements RedisPubSubListener<String, String> {
  private final Logger logger = LoggerFactory.getLogger(SyncMemRedisPubSubImpl.class);

  private String subChannel;

  private SyncMemUtil syncMemUtil;

  public SyncMemRedisPubSubImpl(String channel, SyncMemUtil syncMemUtil) {
    this.subChannel = channel;
    this.syncMemUtil = syncMemUtil;
  }

  @Override
  public void message(String channel, String message) {
    logger.info("channel :{} receive message:{}", channel, message);
    if (this.subChannel.equals(channel)) {

      syncMemUtil.handleSyncMem(message);
    }
  }

  @Override
  public void message(String pattern, String channel, String message) {
    logger.info("channel:{} receive message:{} with pattern:{}", this.subChannel, message, pattern);
  }

  @Override
  public void subscribed(String channel, long count) {
    logger.info("channel:{} get subscribed count:{}", channel, count);
  }

  @Override
  public void psubscribed(String pattern, long count) {}

  @Override
  public void unsubscribed(String channel, long count) {

  }

  @Override
  public void punsubscribed(String pattern, long count) {}
}
