package zyz.zyuanyuz.syncmem.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zyz.zyuanyuz.syncmem.example.service.SyncMemMapService;

import java.util.Map;

/**
 * @author George Yu
 * @since 2019/10/29 10:52
 */
@RestController
@RequestMapping("/map")
public class SyncMemMapController {
    @Autowired
    SyncMemMapService syncMemMapService;

    @GetMapping("/get")
    public Map getMap(){
        return syncMemMapService.getMap();
    }

    @GetMapping("/add")
    public Map addMap(){
        syncMemMapService.addMap();
        return syncMemMapService.getMap();
    }
}
