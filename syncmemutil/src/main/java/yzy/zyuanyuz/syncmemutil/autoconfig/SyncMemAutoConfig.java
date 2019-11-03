package yzy.zyuanyuz.syncmemutil.autoconfig;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yzy.zyuanyuz.syncmemutil.SyncMemUtil;

/**
 * @author yu
 * @since 2019/11/3 20:15
 */
@Configuration
public class SyncMemAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public RedisClient redisClient(){
        return RedisClient.create(RedisURI.builder().withHost("localhost").withPort(6379).withDatabase(0).build());
    }

    @Bean
    @ConditionalOnMissingBean
    public SyncMemUtil syncMemUtil(@Autowired RedisClient redisClient, @Value("syncmem.channel")String channel){
        return new SyncMemUtil(redisClient, channel);
    }
}
