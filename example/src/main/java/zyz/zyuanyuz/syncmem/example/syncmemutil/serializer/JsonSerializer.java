package zyz.zyuanyuz.syncmem.example.syncmemutil.serializer;

import com.alibaba.fastjson.JSONObject;
import zyz.zyuanyuz.syncmem.example.syncmemutil.SyncMemProtocol;

/**
 * @author George Yu
 * @since 2019/10/21 17:05
 */
public class JsonSerializer implements SyncMemSerializer {

  @Override
  public String serializeObject(SyncMemProtocol protocol) {
    return JSONObject.toJSONString(protocol);
  }

  @Override
  public SyncMemProtocol deserializeObject(String msg) {
    SyncMemProtocol protocol = JSONObject.parseObject(msg,SyncMemProtocol.class);

    return null;
  }
}
