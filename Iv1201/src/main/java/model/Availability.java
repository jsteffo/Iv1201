package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the availability database table.
 * 
 */
@Entity
@Table(name="availability")
@NamedQuery(name="Availability.findAll", query="SELECT a FROM Availability a")
public class Availability implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="availability_id")
	private String availabilityId;

	@Temporal(TemporalType.DATE)
	@Column(name="from_date")
	private Date fromDate;

	@Column(name="person_id")
	private BigInteger personId;

	@Temporal(TemporalType.DATE)
	@Column(name="to_date")
	private Date toDate;

	public Availability() {
	}

	public String getAvailabilityId() {
		return this.availabilityId;
	}

	public void setAvailabilityId(String availabilityId) {
		this.availabilityId = availabilityId;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public BigInteger getPersonId() {
		return this.personId;
	}

	public void setPersonId(BigInteger personId) {
		this.personId = personId;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

}