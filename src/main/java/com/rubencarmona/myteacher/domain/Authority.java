package com.rubencarmona.myteacher.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;


/**
 * Clase Authority * Un rol propiamente dicho, en Spring se usa las Authorityes...
 *
 * @author Rubén Carmona García
 * @version 0.1
 * @see Visitar <a href="http://www.rubencarmona.com" target="_blank">www.rubencarmona.com</a>
 * @see Visitar <a href=
 *      "https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/using-boot-using-springbootapplication-annotation.html"
 *      target="_blank">Documentación de @SpringBootApplication.</a>
 */
@Entity
@NamedQuery(name = "Authority.findAll", query = "SELECT a FROM Authority a")
public class Authority implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int authorityid;

  private String authority;

  // bi-directional many-to-many association to User
  @ManyToMany(mappedBy = "authorities")
  private List<User> authorities;

  public Authority() {}

  public int getAuthorityid() {
    return this.authorityid;
  }

  public void setAuthorityid(int authorityid) {
    this.authorityid = authorityid;
  }

  public String getAuthority() {
    return this.authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }

  public List<User> getAuthorities() {
    return this.authorities;
  }

  public void setAuthorities(List<User> authorities) {
    this.authorities = authorities;
  }

}
