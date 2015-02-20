package model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="competence_profile_id")
	private long competenceProfileId;

	@Column(name="years_of_experience")
	private BigDecimal yearsOfExperience;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;

	//bi-directional many-to-one association to Competence
	@ManyToOne
	@JoinColumn(name="competence_id")
	private Competence competence;

	public CompetenceProfile() {
	}

	public long getCompetenceProfileId() {
		return this.competenceProfileId;
	}

	public void setCompetenceProfileId(long competenceProfileId) {
		this.competenceProfileId = competenceProfileId;
	}

	public BigDecimal getYearsOfExperience() {
		return this.yearsOfExperience;
	}

	public void setYearsOfExperience(BigDecimal yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Competence getCompetence() {
		return this.competence;
	}

	public void setCompetence(Competence competence) {
		this.competence = competence;
	}

}