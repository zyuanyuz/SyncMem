package zyz.zyuanyuz.syncmem.example.syncmemutil;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author George Yu
 * @since 2019/10/17 16:11
 */
public class SyncMemEntry {
  private String id;
  private Object obj; // use to call method
  private Method method;

  public SyncMemEntry() {}

  public SyncMemEntry(String id, Object obj, Method method) {
    this.id = id;
    this.obj = obj;
    this.method = method;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Object getObj() {
    return obj;
  }

  public void setObj(Object obj) {
    this.obj = obj;
  }

  public Method getMethod() {
    return method;
  }

  public void setMethod(Method method) {
    this.method = method;
  }
}
