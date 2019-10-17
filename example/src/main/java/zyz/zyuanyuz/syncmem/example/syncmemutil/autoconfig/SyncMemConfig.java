package zyz.zyuanyuz.syncmem.example.syncmemutil.autoconfig;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zyz.zyuanyuz.syncmem.example.syncmemutil.SyncMemUtil;

/**
 * sync mem auto configuration for springboot
 *
 * @author George Yu
 * @since 2019/10/16 19:11
 */
@Configuration
public class SyncMemConfig {

  @Bean
  @ConditionalOnMissingBean
  public RedisClient redisClient() {
    return RedisClient.create(RedisURI.builder().withHost("localhost").withPort(6379).build());
  }

  @Bean
  @ConditionalOnMissingBean
  public SyncMemUtil syncMemUtil(@Autowired RedisClient redisClient) {
    return new SyncMemUtil(redisClient, "example");
  }
}
