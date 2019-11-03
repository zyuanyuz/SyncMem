package yzy.zyuanyuz.syncmemutil;

/**
 * Redis pubsub protocol message
 *
 * @author yu
 * @since 2019/11/3 17:32
 */
public class SyncMemProtocol<T> {
    public static final String SYNCMEMID_STR = "syncMemId";
    public static final String METHODID_STR = "methodId";
    public static final String DATA_STR = "data";

    private String syncMemId;
    private String methodId;
    private T data;

    public SyncMemProtocol() {
    }

    public SyncMemProtocol(String syncMemId, String methodId, T data) {
        this.syncMemId = syncMemId;
        this.methodId = methodId;
        this.data = data;
    }

    public String getSyncMemId() {
        return syncMemId;
    }

    public void setSyncMemId(String syncMemId) {
        this.syncMemId = syncMemId;
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SyncMemId:["
                + syncMemId
                + "],MethodId:["
                + methodId
                + "],data:["
                + data.toString()
                + "]";
    }
}
