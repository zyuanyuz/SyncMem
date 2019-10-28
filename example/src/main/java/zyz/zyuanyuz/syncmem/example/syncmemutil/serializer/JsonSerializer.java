package zyz.zyuanyuz.syncmem.example.syncmemutil.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zyz.zyuanyuz.syncmem.example.syncmemutil.SyncMemConsumerEntity;
import zyz.zyuanyuz.syncmem.example.syncmemutil.SyncMemProtocol;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.parseObject;

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

  @Override
  public SyncMemProtocol deserializeObject(String msg) throws Exception {
    JSONObject protocolObject = JSON.parseObject(msg);
    String syncMemId = protocolObject.getString(SyncMemProtocol.SYNCMEMID_STR);
    String methodId = protocolObject.getString(SyncMemProtocol.METHODID_STR);
    SyncMemConsumerEntity consumerEntity = this.context.getConsumerEntity(methodId);
    Object dataObj =
        JSON.parseObject(msg)
            .getObject(SyncMemProtocol.DATA_STR, consumerEntity.getTypeReference());
    return new SyncMemProtocol<>(syncMemId, methodId, dataObj);
  }
}
