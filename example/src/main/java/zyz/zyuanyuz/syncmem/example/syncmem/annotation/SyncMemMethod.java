package zyz.zyuanyuz.syncmem.example.syncmem.annotation;

import zyz.zyuanyuz.syncmem.example.syncmem.SyncMemMethodType;

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
    String dataName();

    /**
     *
     * @return
     */
    SyncMemMethodType methodType();
}