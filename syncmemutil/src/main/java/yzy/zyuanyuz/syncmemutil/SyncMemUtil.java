package yzy.zyuanyuz.syncmemutil;

import com.alibaba.fastjson.TypeReference;
import io.lettuce.core.RedisClient;

import java.util.function.Consumer;

/**
 * @author yu
 * @since 2019/11/3 17:59
 */
public class SyncMemUtil {

  private SyncMemManager syncMemManager;

  public SyncMemUtil(RedisClient redisClient, String channel) {
    this.syncMemManager = new SyncMemManager(redisClient, channel);
  }

  public void register(String methodId, Consumer consumer, TypeReference dataType) {
    this.syncMemManager.register(methodId, consumer, dataType);
  }

  public void publishSyncMem(String methodId, Object data) {
    this.syncMemManager.publishMessage(methodId, data);
  }
}
