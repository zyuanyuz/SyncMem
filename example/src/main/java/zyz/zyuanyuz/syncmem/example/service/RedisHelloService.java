package zyz.zyuanyuz.syncmem.example.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import zyz.zyuanyuz.syncmem.example.domain.ComplexDomain;
import zyz.zyuanyuz.syncmem.example.domain.SimpleDomain;
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
  @Value("${spring.application.name}")
  private String appName;

  List<ComplexDomain> list = new ArrayList<>();

  Lock lock = new ReentrantLock();

  @Autowired SyncMemUtil syncMemUtil;

  @Override
  public void afterPropertiesSet() throws Exception {
    syncMemUtil.register("redisHelloService.syncDelete", o -> syncDel((List<ComplexDomain>) o));
    syncMemUtil.register("redisHelloService.syncAdd", o -> syncAdd((List<ComplexDomain>) o));
  }

  public List<ComplexDomain> getList() {
    return list;
  }

  public void initList() {}

  public void add() {
    List<ComplexDomain> addList = new ArrayList<>();
    List<SimpleDomain> simpleList =
        Arrays.asList(
            new SimpleDomain("ten", 10), new SimpleDomain("two", 2), new SimpleDomain("one", 1));
    addList.add(new ComplexDomain("a", new ArrayList<>().addAll(Arrays.asList("a", "aa", "aaa"))));
    addList.add(new ComplexDomain("b", new ArrayList<>().addAll(Arrays.asList("a", "aa", "aaa"))));
    addList.add(new ComplexDomain("c", new ArrayList<>().addAll(Arrays.asList("a", "aa", "aaa"))));
    syncMemUtil.syncMemPublish("redisHelloService.syncAdd", addList);
    this.list.addAll(addList);
  }

  public void del() {}

  public void syncAdd(List<ComplexDomain> data) {
    System.out.println(data.get(0).getObj().getClass());
    this.list.addAll(data);
  }

  public void syncDel(List<ComplexDomain> data) {}
}
