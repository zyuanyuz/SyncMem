package zyz.zyuanyuz.syncmem.example.service;

import org.springframework.stereotype.Service;
import zyz.zyuanyuz.syncmem.example.syncmem.SyncMemMethodType;
import zyz.zyuanyuz.syncmem.example.syncmem.annotation.SyncMemMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author George Yu
 * @since 2019/10/16 18:07
 */
@Service
public class RedisHelloService {
    List<String> list = new ArrayList<>();

    public String addString(){
        return null;
    }

    @SyncMemMethod(dataName = "list",methodType = SyncMemMethodType.ADD)
    public void syncAddString(){

    }
}
