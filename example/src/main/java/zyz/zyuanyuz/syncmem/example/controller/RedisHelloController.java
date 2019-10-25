package zyz.zyuanyuz.syncmem.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zyz.zyuanyuz.syncmem.example.service.RedisHelloService;

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

  @GetMapping("del")
  public String delString() {
    redisHelloService.del();
    return redisHelloService.getList().toString();
  }
}