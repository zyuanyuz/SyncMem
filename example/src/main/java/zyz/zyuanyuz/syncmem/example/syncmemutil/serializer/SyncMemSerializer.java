package zyz.zyuanyuz.syncmem.example.syncmemutil.serializer;

import zyz.zyuanyuz.syncmem.example.syncmemutil.SyncMemProtocol;

/**
 * @author George Yu
 * @since 2019/10/21 17:04
 */
public interface SyncMemSerializer {
  String serializeObject(SyncMemProtocol protocol);

  SyncMemProtocol deserializeObject(String msg) throws Exception;
}
