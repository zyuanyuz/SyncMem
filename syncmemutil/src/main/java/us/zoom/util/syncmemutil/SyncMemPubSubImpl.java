package us.zoom.util.syncmemutil;

import redis.clients.jedis.JedisPubSub;

import java.util.function.Consumer;

/**
 * @author zyuanyuz
 * @since 2019/11/4 21:13
 */

public class SyncMemPubSubImpl extends JedisPubSub {
    private String syncMemId;
    private String channel;
    private Consumer<String> callback;

    public SyncMemPubSubImpl(String syncMemId, String channel,Consumer<String> callback) {
        this.syncMemId = syncMemId;
        this.channel = channel;
        this.callback = callback;
    }

    @Override
    public void onMessage(String channel, String message) {
        if(this.channel.equals(channel)&&!this.syncMemId.equals(SyncMemJSONSerializer.getSyncMemId(message))){
            callback.accept(message);
        }
    }
}
