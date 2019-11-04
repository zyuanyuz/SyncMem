package us.zoom.util.syncmemutil;

import redis.clients.jedis.JedisPubSub;

/**
 * @author zyuanyuz
 * @since 2019/11/4 21:13
 */

public class SyncMemPubSubImpl extends JedisPubSub {
    private String syncMemId;
    private String channel;
    public SyncMemPubSubImpl(String syncMemId,String channel){
        this.syncMemId = syncMemId;
        this.channel = channel;
    }

    @Override
    public void onMessage(String channel, String message) {
        super.onMessage(channel, message);
    }
}
