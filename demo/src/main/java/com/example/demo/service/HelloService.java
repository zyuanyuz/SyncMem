package com.example.demo.service;

import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.zoom.util.syncmemutil.SyncMemUtil;

import java.util.*;

/**
 * @author George
 * @date 2019/11/6 11:19
 */
@Service
public class HelloService implements InitializingBean {
  @Autowired SyncMemUtil syncMemUtil;

  private Map<String, List<List<String>>> map = new HashMap<>();

  @Override
  public void afterPropertiesSet() throws Exception {
    syncMemUtil.registerConsumer(
        "HelloService.addMap",
        o -> this.syncAdd((Map<String, List<List<String>>>) o),
        new TypeReference<Map<String, List<List<String>>>>() {});
  }

  public Map<?, ?> get() {
    return this.map;
  }

  public void add() {
    Map<String, List<List<String>>> temp = new HashMap<>();
    temp.put("a", Arrays.asList(Arrays.asList("a", "a"), Arrays.asList("b", "c")));
    temp.put("b", Arrays.asList(Arrays.asList("b", "b"), Arrays.asList("a", "c")));
    temp.put("c", Arrays.asList(Arrays.asList("c", "c"), Arrays.asList("a", "b")));
    this.map.putAll(temp);
    syncMemUtil.publishSyncMem("HelloService.addMap", temp);
  }

  private void syncAdd(Map<String, List<List<String>>> map) {
    this.map.putAll(map);
  }
}
