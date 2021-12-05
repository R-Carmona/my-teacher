package com.rubencarmona.myteacher.domain;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the teacher database table.
 * 
 */
@Entity
@NamedQuery(name = "Teacher.findAll", query = "SELECT t FROM Teacher t")
public class Teacher implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int teacherid;

  @Column
  private String name;

  @Column
  private int userid;

  @Column
  private String surname;

  @Column
  private byte enabled;

  public Teacher() {}

  public Teacher(int teacherid, String name, String surname) {
    super();
    this.teacherid = teacherid;
    this.name = name;
    this.surname = surname;
    this.enabled = 1;

  }

  public Teacher(String name, String surname) {
    super();
    this.enabled = 1;
    this.name = name;
    this.surname = surname;
  }

  public Teacher(String name, int userid, String surname, byte enabled) {
    super();
    this.name = name;
    this.userid = userid;
    this.surname = surname;
    this.enabled = enabled;
  }

  public Teacher(String name, int userid, String surname) {
    super();
    this.name = name;
    this.userid = userid;
    this.surname = surname;

  }

  public int getTeacherid() {
    return teacherid;
  }

  public void setTeacherid(int teacherid) {
    this.teacherid = teacherid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public byte getEnabled() {
    return enabled;
  }

  public void setEnabled(byte enabled) {
    this.enabled = enabled;
  }

  public int getUserid() {
    return userid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + enabled;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((surname == null) ? 0 : surname.hashCode());
    result = prime * result + teacherid;
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
    Teacher other = (Teacher) obj;
    if (enabled != other.enabled)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (surname == null) {
      if (other.surname != null)
        return false;
    } else if (!surname.equals(other.surname))
      return false;
    if (teacherid != other.teacherid)
      return false;
    if (userid != other.userid)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Teacher [teacherid=" + teacherid + ", name=" + name + ", userid=" + userid
        + ", surname=" + surname + ", enabled=" + enabled + "]";
  }



}
