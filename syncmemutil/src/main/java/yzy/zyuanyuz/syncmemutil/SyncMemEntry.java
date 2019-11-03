package yzy.zyuanyuz.syncmemutil;

import com.alibaba.fastjson.TypeReference;

import java.util.function.Consumer;

/**
 * @author yu
 * @since 2019/11/3 17:59
 */
public class SyncMemEntry {
    private Consumer consumer;
    private TypeReference dataType;

    public SyncMemEntry(Consumer consumer, TypeReference dataType) {
        this.consumer = consumer;
        this.dataType = dataType;
    }

    //getter and setter

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public TypeReference getDataType() {
        return dataType;
    }

    public void setDataType(TypeReference dataType) {
        this.dataType = dataType;
    }
}
