package zyz.zyuanyuz.syncmem.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.zoom.util.syncmemutil.SyncMemUtil;

/**
 * @author George Yu
 * @since 2019/10/16 17:39
 */
@Configuration
public class RedisClientConfig {

    @Bean
    public SyncMemUtil syncMemUtil(){

    }

}
