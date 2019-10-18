package zyz.zyuanyuz.syncmem.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zyz.zyuanyuz.syncmem.example.service.RedisHelloService;

import java.util.List;

/**
 * @author George Yu
 * @since 2019/10/16 17:45
 */
@RestController
public class RedisHelloController {
  @Autowired RedisHelloService redisHelloService;

  @GetMapping("/get")
  public List<String> getString() {
    return redisHelloService.getList();
  }

  @GetMapping("/add")
  public List<String> addString() {
    redisHelloService.addString();
    return redisHelloService.getList();
  }

  @GetMapping("del")
  public List<String> delString() {
    redisHelloService.delString();
    return redisHelloService.getList();
  }
}
