package zyz.zyuanyuz.syncmem.example.domain;

import java.util.List;

/**
 * @author George Yu
 * @since 2019/10/21 16:03
 */
public class ComplexDomain<T> {
  String name;
  T obj;

  public ComplexDomain(String name, T obj) {
    this.name = name;
    this.obj = obj;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public T getObj() {
    return obj;
  }

  public void setObj(T obj) {
    this.obj = obj;
  }

  @Override
  public String toString() {
    return "name:[" + name + "],obj:[" + obj + "]";
  }
}
