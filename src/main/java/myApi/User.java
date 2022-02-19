package myApi;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class User {

  private @Id @GeneratedValue Long id;
  private String name;
  private String email;
  private String phone;
  private int credit;

  User() {}

  User(String name, String email, String phone, int credit) {

    this.name = name;
    this.email = email;
    this.phone = phone;
    this.credit = credit;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
	    return this.name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }
  public Long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
	  this.id = id;
  }

  public String getPhone() {
    return this.phone;
  }
  
  public void setPhone(String phone ) {
	  this.phone = phone;
  }
  
  public int getCredit() {
	  return this.credit;
  }
  
  public void setCredit( int credit) {
	  this.credit = credit;
  }
  
 
  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof User))
      return false;
    User user = (User) o ;
    return Objects.equals(this.id, user.id) && Objects.equals(this.name, user.name)
        && Objects.equals(this.email, user.email) && Objects.equals(this.phone, user.phone) 
        && Objects.equals(this.credit, user.credit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.credit, this.phone, this.email);
  }

  @Override
  public String toString() {
    return "User{" + "id=" + this.id + ", name='" + this.name + '\'' + ", email='" + this.email
        + '\'' + ", phone='" + this.phone + '\'' + ", credit='" + this.credit + '\'' + '}';
  }
}