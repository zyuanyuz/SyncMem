package zyz.zyuanyuz.syncmem.example.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author George Yu
 * @since 2019/10/16 17:39
 */
@Configuration
public class RedisClientConfig {

    @Bean
    public RedisClient redisClient(){
        RedisURI uri = RedisURI.create("127.0.0.1",6379);
        RedisClient client =  RedisClient.create(uri);
        return client;
    }


}
