package us.zoom.util.syncmemutil;

import com.alibaba.fastjson.TypeReference;

import java.lang.reflect.Type;
import java.util.function.Consumer;

/**
 * @author George
 * @date 2019/11/5 9:47
 */
public class SyncMemEntry {
  private Consumer<Object> consumer;
  private Type dataType;

  public SyncMemEntry(Consumer<Object> consumer, TypeReference<?> dataType) {
    this.consumer = consumer;
    this.dataType = dataType.getType();
  }

  public SyncMemEntry(Consumer<Object> consumer, Type dataType) {
    this.consumer = consumer;
    this.dataType = dataType;
  }

  public Consumer<Object> getConsumer() {
    return consumer;
  }

  public void setConsumer(Consumer<Object> consumer) {
    this.consumer = consumer;
  }

  public Type getDataType() {
    return dataType;
  }

  public void setDataType(Type dataType) {
    this.dataType = dataType;
  }
}
