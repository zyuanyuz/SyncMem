package us.zoom.util.syncmemutil;

/**
 * @author zyuanyuz
 * @since 2019/11/4 21:30
 */
public class SyncMemProtocol<T> {
  public static final String SYNCMEMID_STR = "syncMemId";
  public static final String CONSUMERID_STR = "consumerId";
  public static final String DATA_STR = "data";

  private String syncMemId;
  private String consumerId;
  private T data;

  public SyncMemProtocol() {}

  public SyncMemProtocol(String syncMemId, String consumerId, T data) {
    this.syncMemId = syncMemId;
    this.consumerId = consumerId;
    this.data = data;
  }

  public String getSyncMemId() {
    return syncMemId;
  }

  public void setSyncMemId(String syncMemId) {
    this.syncMemId = syncMemId;
  }

  public String getConsumerId() {
    return consumerId;
  }

  public void setConsumerId(String consumerId) {
    this.consumerId = consumerId;
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
        + "],ConsumerId:["
        + consumerId
        + "],data:["
        + data.toString()
        + "]";
  }
}
