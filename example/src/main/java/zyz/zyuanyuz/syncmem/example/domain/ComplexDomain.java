package zyz.zyuanyuz.syncmem.example.domain;

import java.util.List;

/**
 * @author George Yu
 * @since 2019/10/21 16:03
 */
public class ComplexDomain {
    String name;
    Object obj;

    public ComplexDomain(String name, Object obj) {
        this.name = name;
        this.obj = obj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
