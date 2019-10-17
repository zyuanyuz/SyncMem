package zyz.zyuanyuz.syncmem.example.syncmemutil;

/**
 * Redis pubsub protocol message
 *
 * @author George Yu
 * @since 2019/10/17 10:33
 */
public class SyncMemProtocol {

  private String methodId;
  private Class<?> dataClazz;
  private Object data;

  public SyncMemProtocol(String methodId, Object data, Class<?> clazz) {
    this.methodId = methodId;
    this.dataClazz = clazz;
    this.data = data;
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

  public Class<?> getDataClazz() {
    return dataClazz;
  }

  public void setDataClazz(Class<?> dataClazz) {
    this.dataClazz = dataClazz;
  }
}
