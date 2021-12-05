package com.rubencarmona.myteacher.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "authorities_users", joinColumns = @JoinColumn(name = "userid"),
      inverseJoinColumns = @JoinColumn(name = "authorityid"))
  private Set<Authority> authorities;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int userid;

  @Column
  private String address;

  @Column
  private String bankaccountnumber;

  @Column(name = "dni_nie")
  private String dniNie;

  @Column
  private byte enabled;

  @Column
  private String mail;

  @Column
  private String name;

  @Column
  private String password;

  @Column
  private int phone;

  @Column(name = "registrationdate")
  private Date registrationdate;

  @Column
  private String surname;

  public User() {}

  public User(String name, String surname, String address, String dniNie, String mail,
      String password, int phone, String bankaccountnumber) {
    super();

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);

    this.address = address;
    this.bankaccountnumber = bankaccountnumber;
    this.dniNie = dniNie;
    this.mail = mail;
    this.name = name;
    this.password = bCryptPasswordEncoder.encode(password);
    this.phone = phone;
    this.surname = surname;
    this.registrationdate = new Date();
    this.enabled = 1;

  }



  public User(int userid, String address, String bankaccountnumber, String dniNie, String mail,
      String name, String password, int phone, String surname) {
    super();
    this.userid = userid;
    this.address = address;
    this.bankaccountnumber = bankaccountnumber;
    this.dniNie = dniNie;
    this.enabled = 1;
    this.mail = mail;
    this.name = name;
    this.password = password;
    this.phone = phone;
    this.registrationdate = new Date();
    this.surname = surname;
  }

  public int getUserid() {
    return this.userid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getBankaccountnumber() {
    return this.bankaccountnumber;
  }

  public void setBankaccountnumber(String bankaccountnumber) {
    this.bankaccountnumber = bankaccountnumber;
  }

  public String getDniNie() {
    return this.dniNie;
  }

  public void setDniNie(String dniNie) {
    this.dniNie = dniNie;
  }

  public byte getEnabled() {
    return this.enabled;
  }

  public void setEnabled(byte enabled) {
    this.enabled = enabled;
  }

  public String getMail() {
    return this.mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getPhone() {
    return this.phone;
  }

  public void setPhone(int phone) {
    this.phone = phone;
  }

  public Date getRegistrationdate() {
    return this.registrationdate;
  }

  public void setRegistrationdate(Date registrationdate) {
    this.registrationdate = registrationdate;
  }

  public String getSurname() {
    return this.surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }



  public Set<Authority> getAuthority() {
    return authorities;
  }

  public void setAuthority(Set<Authority> authority) {
    this.authorities = authority;
  }



  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((dniNie == null) ? 0 : dniNie.hashCode());
    result = prime * result + ((mail == null) ? 0 : mail.hashCode());
    result = prime * result + userid;
    return result;
  }

  @Override
  public String toString() {
    return "User [userid=" + userid + ", address=" + address + ", bankaccountnumber="
        + bankaccountnumber + ", dniNie=" + dniNie + ", enabled=" + enabled + ", mail=" + mail
        + ", name=" + name + ", password=" + password + ", phone=" + phone + ", registrationdate="
        + registrationdate + ", surname=" + surname + "]";
  }
}
