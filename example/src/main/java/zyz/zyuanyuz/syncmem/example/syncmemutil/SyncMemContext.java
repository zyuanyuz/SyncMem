package zyz.zyuanyuz.syncmem.example.syncmemutil;

import com.alibaba.fastjson.TypeReference;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * @author zyuanyuz
 * @since 2019/10/27 20:08
 */
public class SyncMemContext {
  private Map<String, SyncMemConsumerEntity<?>> consumerMap = new ConcurrentHashMap<>();

  public boolean addConsumer(String key, Consumer<?> consumer, TypeReference<?> typeReference) {
    if (consumerMap.get(key) != null) {
      return false;
    }
    consumerMap.put(key, new SyncMemConsumerEntity(consumer, typeReference));
    return true;
  }

  public boolean addConsumer(String key, SyncMemConsumerEntity<?> consumerEntity) {
    if (consumerMap.get(key) != null) {
      return false;
    }
    consumerMap.put(key, consumerEntity);
    return true;
  }

  public SyncMemConsumerEntity<?> getConsumerEntity(String key) {
      return consumerMap.get(key);
  }

}
