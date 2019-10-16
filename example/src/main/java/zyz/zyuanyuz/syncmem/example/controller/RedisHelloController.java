package zyz.zyuanyuz.syncmem.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author George Yu
 * @since 2019/10/16 17:45
 */
@RestController
public class RedisHelloController {

  @GetMapping("")
  public List<String> getString() {
    return null;
  }

  @GetMapping("/add")
  public String addString() {
    return null;
  }

  @GetMapping("del")
  public String delString() {
    return null;
  }
}
