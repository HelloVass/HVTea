package info.hellovass.hv_tea.db.po;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by hello on 2017/3/27.
 */

@Entity
public class User {

  @Id
  private String id;

  @Property
  private String name;

  @Generated(hash = 1037321026)
  public User(String id, String name) {
      this.id = id;
      this.name = name;
  }

  @Generated(hash = 586692638)
  public User() {
  }

  public String getId() {
      return this.id;
  }

  public void setId(String id) {
      this.id = id;
  }

  public String getName() {
      return this.name;
  }

  public void setName(String name) {
      this.name = name;
  }
}
