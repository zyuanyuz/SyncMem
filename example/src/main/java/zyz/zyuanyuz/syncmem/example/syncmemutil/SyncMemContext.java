package zyz.zyuanyuz.syncmem.example.syncmemutil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zyuanyuz
 * @since 2019/10/27 20:08
 */

public class SyncMemContext {
    Map<String,SyncMemConsumerEntry<?>> consumerMap = new ConcurrentHashMap<>();



}
