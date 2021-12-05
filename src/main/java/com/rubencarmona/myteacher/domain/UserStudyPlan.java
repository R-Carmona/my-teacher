package com.rubencarmona.myteacher.domain;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang3.time.DateUtils;


/**
 * The persistent class for the user_study_plans database table.
 * 
 */
@Entity
@Table(name = "user_study_plans")
@NamedQuery(name = "UserStudyPlan.findAll", query = "SELECT u FROM UserStudyPlan u")
public class UserStudyPlan implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int userplanid;

  @Temporal(TemporalType.TIMESTAMP)
  private Date endingdate;

  private BigDecimal paidup;

  private int remainingsessions;

  @Temporal(TemporalType.TIMESTAMP)
  private Date startdate;

  // bi-directional many-to-one association to StudyPlan
  @ManyToOne
  @JoinColumn(name = "studyplanid")
  private StudyPlan studyPlan;

  // bi-directional many-to-one association to User
  @ManyToOne
  @JoinColumn(name = "userid")
  private User user;

  public UserStudyPlan() {}



  public UserStudyPlan(StudyPlan studyPlan, User user) {
    super();
    this.paidup = studyPlan.getPrice();
    this.startdate = new Date();
    this.remainingsessions = studyPlan.getNumberofsessions();
    this.studyPlan = studyPlan;
    this.endingdate = DateUtils.addMonths(startdate, 1);
    this.user = user;
  }



  public int getUserplanid() {
    return this.userplanid;
  }

  public void setUserplanid(int userplanid) {
    this.userplanid = userplanid;
  }

  public Date getEndingdate() {
    return this.endingdate;
  }

  public void setEndingdate(Date endingdate) {
    this.endingdate = endingdate;
  }

  public BigDecimal getPaidup() {
    return this.paidup;
  }

  public void setPaidup(BigDecimal paidup) {
    this.paidup = paidup;
  }

  public int getRemainingsessions() {
    return this.remainingsessions;
  }

  public void setRemainingsessions(int remainingsessions) {
    this.remainingsessions = remainingsessions;
  }

  public Date getStartdate() {
    return this.startdate;
  }

  public void setStartdate(Date startdate) {
    this.startdate = startdate;
  }

  public StudyPlan getStudyPlan() {
    return this.studyPlan;
  }

  public void setStudyPlan(StudyPlan studyPlan) {
    this.studyPlan = studyPlan;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "UserStudyPlan [userplanid=" + userplanid + ", endingdate=" + endingdate + ", paidup="
        + paidup + ", remainingsessions=" + remainingsessions + ", startdate=" + startdate
        + ", studyPlan=" + studyPlan + ", user=" + user + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((endingdate == null) ? 0 : endingdate.hashCode());
    result = prime * result + ((paidup == null) ? 0 : paidup.hashCode());
    result = prime * result + remainingsessions;
    result = prime * result + ((startdate == null) ? 0 : startdate.hashCode());
    result = prime * result + ((studyPlan == null) ? 0 : studyPlan.hashCode());
    result = prime * result + ((user == null) ? 0 : user.hashCode());
    result = prime * result + userplanid;
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
    UserStudyPlan other = (UserStudyPlan) obj;
    if (endingdate == null) {
      if (other.endingdate != null)
        return false;
    } else if (!endingdate.equals(other.endingdate))
      return false;
    if (paidup == null) {
      if (other.paidup != null)
        return false;
    } else if (!paidup.equals(other.paidup))
      return false;
    if (remainingsessions != other.remainingsessions)
      return false;
    if (startdate == null) {
      if (other.startdate != null)
        return false;
    } else if (!startdate.equals(other.startdate))
      return false;
    if (studyPlan == null) {
      if (other.studyPlan != null)
        return false;
    } else if (!studyPlan.equals(other.studyPlan))
      return false;
    if (user == null) {
      if (other.user != null)
        return false;
    } else if (!user.equals(other.user))
      return false;
    if (userplanid != other.userplanid)
      return false;
    return true;
  }



}
