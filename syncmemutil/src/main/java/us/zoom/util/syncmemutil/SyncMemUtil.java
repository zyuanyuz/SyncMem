package us.zoom.util.syncmemutil;

import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;

import java.util.function.Consumer;

/**
 * @author zyuanyuz
 * @since 2019/11/4 21:12
 */

public class SyncMemUtil {
    private static final Logger logger = LoggerFactory.getLogger(SyncMemUtil.class);

    private AbstractSyncMemManager syncMemManager;

    public SyncMemUtil(JedisPool jedisPool, String channel) {
        this.syncMemManager = new SyncMemJedisManager(jedisPool, channel);
    }

    public void publishSyncMem(String consumerId, Object data) {
        this.syncMemManager.publish(consumerId, data);
    }

    public void registerConsumer(String consumerId, Consumer<Object> consumer, TypeReference<?> dataType) {
        this.syncMemManager.register(consumerId, consumer, dataType);
    }
}
