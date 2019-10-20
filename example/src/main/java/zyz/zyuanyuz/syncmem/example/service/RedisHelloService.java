package zyz.zyuanyuz.syncmem.example.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zyz.zyuanyuz.syncmem.example.syncmemutil.SyncMemUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author George Yu
 * @since 2019/10/16 18:07
 */
@Service
public class RedisHelloService implements InitializingBean {
  List<String> list = new ArrayList<>();

  Lock lock = new ReentrantLock();

  @Autowired SyncMemUtil syncMemUtil;

  @Override
  public void afterPropertiesSet() throws Exception {
    syncMemUtil.register("redisHelloService.syncDelete", o -> syncDelString((List<String>) o));
    syncMemUtil.register("redisHelloService.syncAdd", o -> syncAddString((List<String>) o));
  }

  public List<String> getList() {
    return list;
  }

  public void initStrList(){
    List<String> strList = Arrays.asList("ab","bc","cd");

  }

  public void addString() {
    List<String> strList = Arrays.asList("a", "b", "c");
    syncMemUtil.syncMemPublish("redisHelloService.syncAdd", strList);
    list.addAll(strList);
  }

  public void delString() {
    List<String> strList = Arrays.asList("a");
    syncMemUtil.syncMemPublish("redisHelloService.syncDelete", strList);
    list.removeAll(strList);
  }

  // @SyncMemMethod("redisHelloService.list.add")
  public void syncAddString(List<String> data) {
    list.addAll(data);
  }

  // @SyncMemMethod("redisHelloService.list.delete")
  public void syncDelString(List<String> data) {
    list.removeAll(data);
  }
}
