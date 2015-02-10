package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * The persistent class for the competence_profile database table.
 * 
 */
@Entity
@Table(name="competence_profile")
@NamedQuery(name="CompetenceProfile.findAll", query="SELECT c FROM CompetenceProfile c")
public class CompetenceProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="competence_profile_id")
	private String competenceProfileId;

	@Column(name="competence_id")
	private BigInteger competenceId;

	@Column(name="person_id")
	private BigInteger personId;

	@Column(name="years_of_experience")
	private BigDecimal yearsOfExperience;

	public CompetenceProfile() {
	}

	public String getCompetenceProfileId() {
		return this.competenceProfileId;
	}

	public void setCompetenceProfileId(String competenceProfileId) {
		this.competenceProfileId = competenceProfileId;
	}

	public BigInteger getCompetenceId() {
		return this.competenceId;
	}

	public void setCompetenceId(BigInteger competenceId) {
		this.competenceId = competenceId;
	}

	public BigInteger getPersonId() {
		return this.personId;
	}

	public void setPersonId(BigInteger personId) {
		this.personId = personId;
	}

	public BigDecimal getYearsOfExperience() {
		return this.yearsOfExperience;
	}

	public void setYearsOfExperience(BigDecimal yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

}