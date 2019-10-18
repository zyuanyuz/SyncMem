package zyz.zyuanyuz.syncmem.example.syncmemutil;

import java.io.Serializable;

/**
 * Redis pubsub protocol message
 *
 * @author George Yu
 * @since 2019/10/17 10:33
 */
public class SyncMemProtocol implements Serializable {

  private String syncMemId;
  private String methodId;
  private String typeName;
  private Object data;

  public SyncMemProtocol(String syncMemId, String methodId, Object data, String typeName) {
    this.syncMemId = syncMemId;
    this.methodId = methodId;
    this.typeName = typeName;
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

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  @Override
  public String toString() {
    return "SyncMemId:["
        + syncMemId
        + "],MethodId:["
        + methodId
        + "],data type:["
        + typeName
        + "],data:["
        + data.toString()
        + "]";
  }
}
