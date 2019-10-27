package zyz.zyuanyuz.syncmem.example.syncmemutil.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zyz.zyuanyuz.syncmem.example.syncmemutil.SyncMemProtocol;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author George Yu
 * @since 2019/10/21 17:05
 */
public class JsonSerializer extends SyncMemSerializer {
  private static final Logger logger = LoggerFactory.getLogger(JsonSerializer.class);

  @Override
  public String serializeObject(SyncMemProtocol protocol) throws Exception {
    return JSONObject.toJSONString(protocol);
  }

  // TODO deserialize the collection like Map<?,List<?>>or Map<?,Map<?,?>>
  @Override
  public SyncMemProtocol deserializeObject(String msg) throws Exception {
    //    SyncMemProtocol protocol = JSONObject.parseObject(msg, SyncMemProtocol.class);
    JSONObject msgJson = JSON.parseObject(msg);
    JSONObject syncMemUtiIdJson = msgJson.getJSONObject(SyncMemProtocol.SYNCMEMID_STR);
    JSONObject methodIdJson = msgJson.getJSONObject(SyncMemProtocol.METHODID_STR);
    JSONArray typeNamesJson = msgJson.getJSONArray(SyncMemProtocol.TYPENAMES);
    JSONObject dataJson = msgJson.getJSONObject(SyncMemProtocol.DATA_STR);

    return null;
  }
}
