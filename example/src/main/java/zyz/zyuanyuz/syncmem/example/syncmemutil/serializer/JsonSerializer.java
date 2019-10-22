package zyz.zyuanyuz.syncmem.example.syncmemutil.serializer;

import com.alibaba.fastjson.JSON;
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
public class JsonSerializer implements SyncMemSerializer {
  private static final Logger logger = LoggerFactory.getLogger(JsonSerializer.class);

  @Override
  public String serializeObject(SyncMemProtocol protocol) {
    return JSONObject.toJSONString(protocol);
  }

  // TODO deserialize the collection like Map<?,List<?>>or Map<?,Map<?,?>>
  @Override
  public SyncMemProtocol deserializeObject(String json) throws Exception {
    SyncMemProtocol protocol = JSONObject.parseObject(json, SyncMemProtocol.class);
    logger.info("typeNames are {}", protocol.getTypeNames());
    JSONObject jsonObject = JSON.parseObject(json);
    protocol.setData(
        deserialzeData(0, protocol.getTypeNames(), jsonObject.getString(SyncMemProtocol.DATA_STR)));
    return protocol;
  }

  private Object deserialzeData(int index, List<String> types, String data) throws Exception {
    if (Class.forName(types.get(index)).isInstance(Collection.class)) {

    } else if (Class.forName(types.get(index)).isInstance(Map.class)) {

    }
    return JSONObject.parseObject(data, Class.forName(types.get(index)));
  }
}
