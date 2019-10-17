package zyz.zyuanyuz.syncmem.example.syncmemutil.annotation;

import java.lang.annotation.*;

/**
 * @author George Yu
 * @since 2019/10/16 18:00
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SyncMemMethod {
    /**
     *
     * @return
     */
    String value();
}