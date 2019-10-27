package zyz.zyuanyuz.syncmem.example.syncmemutil;

import com.alibaba.fastjson.TypeReference;

import java.util.function.Consumer;

/**
 * @author zyuanyuz
 * @since 2019/10/27 13:28
 */

public class SyncMemConsumerEntry<T> {
    Consumer<T> consumer;
    TypeReference<T> typeReference; // for args cast

    public SyncMemConsumerEntry(Consumer<T> consumer, TypeReference<T> typeReference) {
        this.consumer = consumer;
        this.typeReference = typeReference;
    }

    public Consumer<T> getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    public TypeReference<T> getTypeReference() {
        return typeReference;
    }

    public void setTypeReference(TypeReference<T> typeReference) {
        this.typeReference = typeReference;
    }
}
