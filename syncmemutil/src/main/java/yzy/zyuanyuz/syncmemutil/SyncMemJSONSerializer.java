package yzy.zyuanyuz.syncmemutil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
 * @author yu
 * @since 2019/11/3 18:35
 */
public abstract class SyncMemJSONSerializer {

  public static String getMessage(SyncMemProtocol protocol) {
    return JSONObject.toJSONString(protocol);
  }

  public static String getSyncMemId(String message) {
    return JSON.parseObject(message).getString(SyncMemProtocol.SYNCMEMID_STR);
  }

  public static String getConsumerId(String message) {
    return JSON.parseObject(message).getString(SyncMemProtocol.METHODID_STR);
  }

  public static Object getData(String message, TypeReference dataType) {
    return JSON.parseObject(message).getObject(SyncMemProtocol.DATA_STR, dataType);
  }
}
