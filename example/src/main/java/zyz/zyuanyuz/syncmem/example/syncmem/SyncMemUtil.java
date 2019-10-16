package zyz.zyuanyuz.syncmem.example.syncmem;

/**
 * @author George Yu
 * @since 2019/10/16 17:45
 */
public class SyncMemUtil {
    RedisPubSubImpl pubSub;
    public SyncMemUtil(){
        pubSub = new RedisPubSubImpl();
    }

}
