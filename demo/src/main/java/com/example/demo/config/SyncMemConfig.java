package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import us.zoom.util.syncmemutil.SyncMemUtil;

/**
 * @author George
 * @date 2019/11/6 11:20
 */
@Configuration
public class SyncMemConfig {
    @Bean
    @ConditionalOnMissingBean
    public JedisPool jedisPool(){
        return new JedisPool("127.0.0.1",6379);
    }

    @Bean
    @ConditionalOnMissingBean
    public SyncMemUtil syncMemUtil(JedisPool jedisPool, @Value("example")String channel){
        return new SyncMemUtil(jedisPool, channel);
    }
}
