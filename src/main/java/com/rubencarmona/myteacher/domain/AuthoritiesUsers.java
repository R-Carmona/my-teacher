package com.rubencarmona.myteacher.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Clase AuthoritiesUsers * Clase encargada de alvergar los usuarios y sus roles.
 *
 * @author Rubén Carmona García
 * @version 0.1
 * @see Visitar <a href="http://www.rubencarmona.com" target="_blank">www.rubencarmona.com</a>
 * @see Visitar <a href=
 *      "https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/using-boot-using-springbootapplication-annotation.html"
 *      target="_blank">Documentación de @SpringBootApplication.</a>
 */
@Entity(name = "authorities_users")
public class AuthoritiesUsers {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column
  private int userid;

  @Column
  private int authorityid;



  public AuthoritiesUsers() {
    super();
  }

  public AuthoritiesUsers(int userid, int authorityid) {
    super();
    this.userid = userid;
    this.authorityid = authorityid;
  }



  public AuthoritiesUsers(int id, int userid, int authorityid) {
    super();
    this.id = id;
    this.userid = userid;
    this.authorityid = authorityid;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUserid() {
    return userid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  public int getAuthorityid() {
    return authorityid;
  }

  public void setAuthorityid(int authorityid) {
    this.authorityid = authorityid;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + authorityid;
    result = prime * result + id;
    result = prime * result + userid;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AuthoritiesUsers other = (AuthoritiesUsers) obj;
    if (authorityid != other.authorityid)
      return false;
    if (id != other.id)
      return false;
    if (userid != other.userid)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "AuthoritiesUsers [id=" + id + ", userid=" + userid + ", authorityid=" + authorityid
        + "]";
  }



}
