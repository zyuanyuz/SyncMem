package zyz.zyuanyuz.syncmem.example.syncmem.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import zyz.zyuanyuz.syncmem.example.syncmem.SyncMemUtil;

import java.net.URI;

/**
 * sync mem auto configuration for springboot
 * @author George Yu
 * @since 2019/10/16 19:11
 */
@Configuration
public class SyncMemConfig {

    @Bean
    @ConditionalOnMissingBean
    public StatefulRedisPubSubConnection redisClient(){
        return RedisClient.create(RedisURI.builder().withHost("127.0.0.1").withPort().build()).connectPubSub();
    }

    @Bean
    @ConditionalOnMissingBean
    public SyncMemUtil syncMemUtil(){
        return new SyncMemUtil();
    }


}