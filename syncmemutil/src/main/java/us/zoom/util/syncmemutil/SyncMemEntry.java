package us.zoom.util.syncmemutil;

import com.alibaba.fastjson.TypeReference;

import java.util.function.Consumer;

/**
 * @Author George
 * @Date 2019/11/5 9:47
 */
public class SyncMemEntry {
    private Consumer<Object> consumer;
    private TypeReference<?> dataType;

    public SyncMemEntry(Consumer<Object> consumer, TypeReference<?> dataType) {
        this.consumer = consumer;
        this.dataType = dataType;
    }

    public Consumer<Object> getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer<Object> consumer) {
        this.consumer = consumer;
    }

    public TypeReference<?> getDataType() {
        return dataType;
    }

    public void setDataType(TypeReference<?> dataType) {
        this.dataType = dataType;
    }
}
