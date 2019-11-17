package us.zoom.util.syncmemutil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Type;

/**
 * @author zyuanyuz
 * @since 2019/11/4 21:28
 */
public abstract class SyncMemJSONSerializer {
  public static String getJsonMessage(SyncMemProtocol<?> protocol) {
    return JSONObject.toJSONString(protocol);
  }

  public static String getSyncMemId(String message) {
    return JSON.parseObject(message).getString(SyncMemProtocol.SYNCMEMID_STR);
  }

  public static String getConsumerId(String message) {
    return JSON.parseObject(message).getString(SyncMemProtocol.CONSUMERID_STR);
  }

  public static Object getData(String message, Type dataType) {
    return JSON.parseObject(message).getObject(SyncMemProtocol.DATA_STR, dataType);
  }
}
