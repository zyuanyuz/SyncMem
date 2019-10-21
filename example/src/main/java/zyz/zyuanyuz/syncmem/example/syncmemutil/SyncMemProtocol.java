package zyz.zyuanyuz.syncmem.example.syncmemutil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Redis pubsub protocol message
 *
 * @author George Yu
 * @since 2019/10/17 10:33
 */
public class SyncMemProtocol implements Serializable {

  private String syncMemId;
  private String methodId;
  private List<String> typeNames; // can't set as Class type because fastJson can't serialize it
  private Object data;

  public SyncMemProtocol() {}

  public SyncMemProtocol(String syncMemId, String methodId, String typeName, Object data) {
    this.syncMemId = syncMemId;
    this.methodId = methodId;
    this.data = data;
    this.typeNames = new ArrayList<>();
    typeNames.add(typeName);
  }

  public SyncMemProtocol(String syncMemId, String methodId, List<String> typeNames, Object data) {
    this.syncMemId = syncMemId;
    this.methodId = methodId;
    this.typeNames = typeNames;
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

  public List<String> getTypeNames() {
    return typeNames;
  }

  public void setTypeNames(List<String> typeNames) {
    this.typeNames = typeNames;
  }

  @Override
  public String toString() {
    return "SyncMemId:["
        + syncMemId
        + "],MethodId:["
        + methodId
        + "],data types:["
        + typeNames
        + "],data:["
        + data.toString()
        + "]";
  }
}
