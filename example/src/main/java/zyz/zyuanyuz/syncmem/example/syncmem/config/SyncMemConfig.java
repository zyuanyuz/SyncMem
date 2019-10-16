package zyz.zyuanyuz.syncmem.example.syncmem.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zyz.zyuanyuz.syncmem.example.syncmem.SyncMemUtil;

/**
 * sync mem auto configuration for springboot
 * @author George Yu
 * @since 2019/10/16 19:11
 */
@Configuration
public class SyncMemConfig {
    @Bean
    @ConditionalOnMissingBean
    public SyncMemUtil syncMemUtil(){
        return new SyncMemUtil();
    }
}