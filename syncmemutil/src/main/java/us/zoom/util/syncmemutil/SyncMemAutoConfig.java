package us.zoom.util.syncmemutil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

@Configuration
public class SyncMemAutoConfig {
    @Bean
    @ConditionalOnMissingBean
    public JedisPool jedisPool(){
       return new JedisPool("localhost",6379);
    }

    @Bean
    @ConditionalOnMissingBean
    public SyncMemUtil syncMemUtil(JedisPool jedisPool, @Value("example")String channel){
        return new SyncMemUtil(jedisPool, channel);
    }
}
