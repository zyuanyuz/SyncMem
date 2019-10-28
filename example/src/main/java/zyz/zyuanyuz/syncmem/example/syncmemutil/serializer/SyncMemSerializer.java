package zyz.zyuanyuz.syncmem.example.syncmemutil.serializer;

import zyz.zyuanyuz.syncmem.example.syncmemutil.SyncMemContext;
import zyz.zyuanyuz.syncmem.example.syncmemutil.SyncMemProtocol;

/**
 * @author George Yu
 * @since 2019/10/21 17:04
 */
public abstract class SyncMemSerializer {

  SyncMemContext context = new SyncMemContext();

  public SyncMemContext getContext() {
    return this.context;
  }

  public void setContext(SyncMemContext context) {
    this.context = context;
  }

  public abstract String serializeObject(SyncMemProtocol protocol) throws Exception;

  public abstract SyncMemProtocol deserializeObject(String msg) throws Exception;
}
