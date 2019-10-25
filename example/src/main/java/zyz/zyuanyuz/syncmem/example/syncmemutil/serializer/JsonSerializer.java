package zyz.zyuanyuz.syncmem.example.syncmemutil.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zyz.zyuanyuz.syncmem.example.syncmemutil.SyncMemProtocol;

import java.util.Collection;
import java.util.Map;

/**
 * @author George Yu
 * @since 2019/10/21 17:05
 */
public class JsonSerializer implements SyncMemSerializer {
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

//
//    if (clazz.isInstance(Collection.class)) {
//      protocol.setData(
//          JSONObject.parseObject(
//              jsonData.toJSONString(),
//              Class.forName(protocol.getTypeNames().get(1)),
//              Feature.OrderedField));
//    } else if (clazz.isInstance(Map.class)) {
//
//    } else {
//
//    }
    return null;
  }
}
