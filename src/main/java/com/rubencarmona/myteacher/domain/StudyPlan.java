package com.rubencarmona.myteacher.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the study_plans database table.
 * 
 */
@Entity
@Table(name="study_plans")
@NamedQuery(name="StudyPlan.findAll", query="SELECT s FROM StudyPlan s")
public class StudyPlan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idstudyplan;

	private String codename;

	private int numberofsessions;

	private BigDecimal price;

	public StudyPlan() {
	}
	
	public StudyPlan(String codename, int numberofsessions, BigDecimal price) {
		super();
		this.codename = codename;
		this.numberofsessions = numberofsessions;
		this.price = price;
	}



	public int getIdstudyplan() {
		return this.idstudyplan;
	}

	public void setIdstudyplan(int idstudyplan) {
		this.idstudyplan = idstudyplan;
	}

	public String getCodename() {
		return this.codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	public int getNumberofsessions() {
		return this.numberofsessions;
	}

	public void setNumberofsessions(int numberofsessions) {
		this.numberofsessions = numberofsessions;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "StudyPlan [idstudyplan=" + idstudyplan + ", codename=" + codename + ", numberofsessions="
				+ numberofsessions + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codename == null) ? 0 : codename.hashCode());
		result = prime * result + idstudyplan;
		result = prime * result + numberofsessions;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		StudyPlan other = (StudyPlan) obj;
		if (codename == null) {
			if (other.codename != null)
				return false;
		} else if (!codename.equals(other.codename))
			return false;
		if (idstudyplan != other.idstudyplan)
			return false;
		if (numberofsessions != other.numberofsessions)
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
	
	


}