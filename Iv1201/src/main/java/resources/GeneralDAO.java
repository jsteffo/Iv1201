package resources;



import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Availability;
import model.Person;
import dto.ApplicantDTO;

@Stateless
public class GeneralDAO {
	
	@PersistenceContext
	EntityManager em;

	/**
	 * Insert a new Person in the Table person. 
	 * @param dto - Information to be inserted
	 */
	public void insertPerson(ApplicantDTO dto){
	
		Person p = new Person();
		p.setName(dto.getFirstName());
		p.setSurname(dto.getLastName());
		p.setEmail(dto.getEmail());
		p.setPassword(dto.getPassword());
	
		p.setSsn(dto.getSsn());
		p.setUsername(dto.getUserName());
		em.persist(p);
	}
	
	/**
	 * Insert availability row into database. The availability specified is associated with the person specified in argument
	 * @param dto - The dates to be inserted and social security number of person whom dates should be associated with
	 */
	public void insertAvailability(ApplicantDTO dto) {
		Availability a = new Availability();
		a.setFromDate(java.sql.Date.valueOf(dto.getFromDate()));
		a.setToDate(java.sql.Date.valueOf(dto.getToDate()));
		BigInteger personId = getPersonIdFromSsn(dto.getSsn());
		a.setPersonId(personId);
		em.persist(a);
	}
	/**
	 * Looks up the primary key associated with specified social security number in database.
	 * @param ssn - Social security number
	 * @return primary key value in database
	 */
	public BigInteger getPersonIdFromSsn(String ssn){
		String sql = "SELECT p FROM Person p where p.ssn = :personSsn";
		Person p = (Person) em.createQuery(sql).setParameter("personSsn", ssn).getSingleResult();
		return new BigInteger(p.getSsn());
		
		
	}
	
	//insertRole
//	public void addRole(String str){
//		em.create...
//	}
	
}
