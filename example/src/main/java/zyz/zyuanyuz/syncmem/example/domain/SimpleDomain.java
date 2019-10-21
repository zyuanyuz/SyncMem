package zyz.zyuanyuz.syncmem.example.domain;

/**
 * @author George Yu
 * @since 2019/10/21 16:13
 */
public class SimpleDomain {
  String id;
  Integer num;

  public SimpleDomain(String id, Integer num) {
    this.id = id;
    this.num = num;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }
}
