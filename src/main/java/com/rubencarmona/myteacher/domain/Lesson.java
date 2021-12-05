package com.rubencarmona.myteacher.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


/**
 * The persistent class for the lesson database table.
 * 
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name = "Lesson.findAll", query = "SELECT l FROM Lesson l")
public class Lesson implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int idlesson;

  @Column
  private String title;

  @Column
  private int assessment;

  @Column
  private Date starDate;

  @Column
  private Date endDate;


  @Column(name = "teacherid")
  private int teacher;


  @Column(name = "userid")
  private int user;

  public Lesson() {}

  public Lesson(Teacher teacher, User user, Date date) {
    super();

    Calendar endDateCalendar = Calendar.getInstance();
    endDateCalendar.setTime(date);
    endDateCalendar.add(Calendar.MINUTE, 25);

    this.assessment = 0;
    this.starDate = date;
    this.title = "Clase MyTeacher";
    this.endDate = endDateCalendar.getTime();
    this.teacher = teacher.getTeacherid();
    this.user = user.getUserid();
  }



  public int getIdlesson() {
    return this.idlesson;
  }

  public void setIdlesson(int idlesson) {
    this.idlesson = idlesson;
  }

  public int getAssessment() {
    return this.assessment;
  }

  public void setAssessment(int assessment) {
    this.assessment = assessment;
  }

  public Date getDate() {
    return this.starDate;
  }

  public void setDate(Date date) {
    this.starDate = date;
  }

  public int getTeacher() {
    return teacher;
  }

  public void setTeacher(int teacher) {
    this.teacher = teacher;
  }

  public int getUser() {
    return user;
  }

  public void setUser(int user) {
    this.user = user;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getStarDate() {
    return starDate;
  }

  public void setStarDate(Date starDate) {
    this.starDate = starDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + assessment;
    result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
    result = prime * result + idlesson;
    result = prime * result + ((starDate == null) ? 0 : starDate.hashCode());
    result = prime * result + teacher;
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + user;
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
    Lesson other = (Lesson) obj;
    if (assessment != other.assessment)
      return false;
    if (endDate == null) {
      if (other.endDate != null)
        return false;
    } else if (!endDate.equals(other.endDate))
      return false;
    if (idlesson != other.idlesson)
      return false;
    if (starDate == null) {
      if (other.starDate != null)
        return false;
    } else if (!starDate.equals(other.starDate))
      return false;
    if (teacher != other.teacher)
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (user != other.user)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Lesson [idlesson=" + idlesson + ", title=" + title + ", assessment=" + assessment
        + ", starDate=" + starDate + ", endDate=" + endDate + ", teacher=" + teacher + ", user="
        + user + "]";
  }



}
