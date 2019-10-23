package zyz.zyuanyuz.syncmem.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zyz.zyuanyuz.syncmem.example.domain.ComplexDomain;
import zyz.zyuanyuz.syncmem.example.domain.SimpleDomain;
import zyz.zyuanyuz.syncmem.example.service.RedisHelloService;
import zyz.zyuanyuz.syncmem.example.syncmemutil.SyncMemProtocol;

import java.util.ArrayList;
import java.util.List;

/**
 * @author George Yu
 * @since 2019/10/16 17:45
 */
@RestController
public class RedisHelloController {
  @Autowired RedisHelloService redisHelloService;


  @GetMapping("/get")
  public String getString() {
    return redisHelloService.getList().toString();
  }

  @GetMapping("/add")
  public String addString() {
    redisHelloService.add();
    return redisHelloService.getList().toString();
  }

  @GetMapping("/del")
  public String delString() {
    redisHelloService.del();
    return redisHelloService.getList().toString();
  }

  @GetMapping("/test")
  public String testTypeReference(){
    List<SimpleDomain> list = new ArrayList<>();
    SyncMemProtocol<List<SimpleDomain>> syncMemProtocol = new SyncMemProtocol<>("asdfasda","methodId",list,new TypeReference<List<SimpleDomain>>(){});
    String jsonStr = JSONObject.toJSONString(syncMemProtocol);
    SyncMemProtocol protocol = JSONObject.parseObject(jsonStr,SyncMemProtocol.class);
    return protocol.getTypeReference().toString();
  }
}
