package zyz.zyuanyuz.syncmem.example.syncmem;

import zyz.zyuanyuz.syncmem.example.syncmem.annotation.SyncMemMethod;

import java.util.Collection;

/**
 * @author George Yu
 * @since 2019/10/16 17:45
 */
public class SyncMemUtil {
    SyncMemRedisPubSubImpl pubSub;

    public SyncMemUtil(){
        pubSub = new SyncMemRedisPubSubImpl();
        pubSub.subscribed("cahnnel",1);
    }

    public void syncMemPub(SyncMemMethod method, Collection<?> data){

    }
}
