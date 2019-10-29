package zyz.zyuanyuz.syncmem.example.service;

import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zyz.zyuanyuz.syncmem.example.syncmemutil.SyncMemUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author George Yu
 * @since 2019/10/29 10:04
 */
@Service
public class SyncMemMapService implements InitializingBean {
  Map<String, List<String>> map = new HashMap<>();

  @Autowired SyncMemUtil syncMemUtil;

  @Override
  public void afterPropertiesSet() throws Exception {
    syncMemUtil.register(
        "SyncMemMapService.syncAddMap",
        o -> syncAddMap((Map<String, List<String>>) o),
        new TypeReference<Map<String, List<String>>>() {});
  }

  public Map getMap() {
    return this.map;
  }

  public void addMap() {
    Map<String, List<String>> tmpMap = new HashMap<>();
    tmpMap.put("a", Arrays.asList("a", "b", "c"));
    tmpMap.put("b", Arrays.asList("a", "b", "c"));
    tmpMap.put("c", Arrays.asList("a", "b", "c"));
    this.map.putAll(tmpMap);
    this.syncMemUtil.syncMemPublish("SyncMemMapService.syncAddMap", tmpMap);
  }

  public void syncAddMap(Map<String, List<String>> map) {
      this.map.putAll(map);
  }
}
