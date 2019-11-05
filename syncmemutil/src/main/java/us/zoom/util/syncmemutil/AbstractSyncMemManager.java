package us.zoom.util.syncmemutil;

import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * @Author George
 * @Date 2019/11/5 10:30
 */
public abstract class AbstractSyncMemManager {
    private static final Logger logger = LoggerFactory.getLogger(AbstractSyncMemManager.class);
    protected Map<String,SyncMemEntry> consumerMap = new ConcurrentHashMap<>();
    protected String syncMemId = UUID.randomUUID().toString();
    protected String channel;

    public void register(String consumerId, Consumer<Object> consumer, TypeReference<?> dataType){
        if (this.consumerMap.containsKey(consumerId)) {
            logger.warn("sync mem : can't register the consumerId {} already exist", consumerId);
            return;
        }
        this.consumerMap.put(consumerId, new SyncMemEntry(consumer, dataType));
    }

    public abstract void publish(String consumerId,Object data);

    abstract void handleMessage(String message);
}
