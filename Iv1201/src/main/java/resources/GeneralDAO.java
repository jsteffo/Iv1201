package resources;



import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.interceptor.Interceptor;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import util.LoggingInterceptor;
import model.Availability;
import model.Competence;
import model.CompetenceProfile;
import model.Person;
import model.Role;
import dto.ApplicantDTO;
import dto.AvailabilityDTO;
import dto.CompetenceDTO;
import dto.PersonDTO;
import exception.NoSuchCompetenceException;

@Stateless
@Interceptors(LoggingInterceptor.class)
public class GeneralDAO {

	private final long recruit = 1;
	private final long applicant = 2;
	
	
	@PersistenceContext
	EntityManager em;

	/**
	 * NOT TO BE USED AT ALL. Keep it temporarily for everyone to compared old vs new way of dealing with inserts.
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
		p.setRole(em.find(Role.class, applicant));
		em.persist(p);
		
		
	}

	/**
	 * Insert a Person into the DB
	 * @param dto - The data of the Person to be inserted
	 * @return - A reference to the newly inserted Person
	 */
	public Person insertPerson(PersonDTO dto){

		Person p = new Person();
		p.setName(dto.getFirstName());
		p.setSurname(dto.getLastName());
		p.setEmail(dto.getEmail());
		p.setPassword(dto.getPassword());
		p.setSsn(dto.getSsn());
		p.setUsername(dto.getUserName());
		p.setRole(em.find(Role.class, applicant));
		em.persist(p);
		return p;
	}

	/**
	 * Insert Availability of a Person into the DB
	 * @param dto - The availability data to be inserted
	 * @param p - The Person to which the availability data is to be associated with
	 */
	public void insertAvailability(List<AvailabilityDTO> dto, Person p) {

		for(AvailabilityDTO aDto : dto){
			Availability a = new Availability();
			a.setFromDate(java.sql.Date.valueOf(aDto.getFromDate()));
			a.setToDate(java.sql.Date.valueOf(aDto.getToDate()));
			p.addAvailability(a);
			em.persist(a);	
		}
	}

	/**
	 * Insert Competence of a Person into the DB
	 * @param dto - The competence data to be inserted
	 * @param p - The Person to which the competence Data is to be associated with
	 * @throws NoSuchCompetenceException - If name of competence doesn't exist in database
	 */
	public void insertCompetenceProfile(List<CompetenceDTO> dto, Person p) throws NoSuchCompetenceException {

		for(CompetenceDTO cDto : dto) {
			CompetenceProfile cProfile = new CompetenceProfile();
			cProfile.setPerson(p);
			cProfile.setYearsOfExperience(new BigDecimal(cDto.getYearsOfExperience()));
			Competence c = getCompetence(cDto.getName());
			cProfile.setCompetence(c);
			em.persist(cProfile);

		}
	}
	/**
	 * Get the Competence entity object with the specified competence name
	 * @param competenceName - name of the competence in question
	 * @return - The entity with specified competenceName
	 */
	public Competence getCompetence(String competenceName) throws NoSuchCompetenceException {
		String sql = "SELECT c from Competence c where c.name = :competenceName";
		List compList = em.createQuery(sql).setParameter("competenceName", competenceName).getResultList();
		if(compList.size() == 0){
			throw new NoSuchCompetenceException(competenceName);
		}
		return (Competence) compList.get(0);
	}


	//	/**
	//	 * Insert availability row into database. The availability specified is associated with the person specified in argument
	//	 * @param dto - The dates to be inserted and social security number of person whom dates should be associated with
	//	 */
	//	public void insertAvailability(ApplicantDTO dto) {
	//		Availability a = new Availability();
	//		a.setFromDate(java.sql.Date.valueOf(dto.getFromDate()));
	//		a.setToDate(java.sql.Date.valueOf(dto.getToDate()));
	//		BigInteger personId = getPersonIdFromSsn(dto.getSsn());
	//		a.setPersonId(personId);
	//		em.persist(a);
	//	}
	//	/**
	//	 * Looks up the primary key associated with specified social security number in database.
	//	 * @param ssn - Social security number
	//	 * @return primary key value in database
	//	 */
	//	public BigInteger getPersonIdFromSsn(String ssn){
	//		String sql = "SELECT p FROM Person p where p.ssn = :personSsn";
	//		Person p = (Person) em.createQuery(sql).setParameter("personSsn", ssn).getSingleResult();
	//		return new BigInteger(p.getSsn());
	//		
	//	}

	//insertRole
	//	public void addRole(String str){
	//		em.create...
	//	}

}
