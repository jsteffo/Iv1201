package model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;




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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="availability_id")
	private long availabilityId;


	@Column(name="from_date")
	//@Temporal(TemporalType.DATE)
	private Date fromDate;

	
	@Column(name="to_date")
	//@Temporal(TemporalType.DATE)
	private Date toDate;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;

	public Availability() {
	}

	public long getAvailabilityId() {
		return this.availabilityId;
	}

	public void setAvailabilityId(long availabilityId) {
		this.availabilityId = availabilityId;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}