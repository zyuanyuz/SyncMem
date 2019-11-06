package com.example.demo.controller;

import com.example.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import us.zoom.util.syncmemutil.SyncMemUtil;

/**
 * @author George
 * @date 2019/11/6 11:18
 */
@RestController
public class HelloController {
  @Autowired HelloService helloService;

  @GetMapping("/get")
  public String getMemCache() {
    return helloService.get().toString();
  }

  @GetMapping("/add")
  public String addMemCache() {
    helloService.add();
    return helloService.get().toString();
  }
}
