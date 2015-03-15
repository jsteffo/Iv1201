package resources;



import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;




import model.Availability;
import model.Competence;
import model.CompetenceProfile;
import model.Person;
import model.Role;
import util.LoggingInterceptor;
import dto.ApplicantDTO;
import dto.AvailabilityDTO;
import dto.CompetenceDTO;
import dto.PersonDTO;
import dto.SearchCriteriaDTO;
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
			a.setFromDate(Date.valueOf(aDto.getFromDate()));
			a.setToDate(Date.valueOf(aDto.getToDate()));
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
			cProfile.setYearsOfExperience(cDto.getYearsOfExperience());
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



	/**
	 * Get all the available competences in the DB
	 * @return - List of CompetenceDTO which will be available
	 */
	public List<CompetenceDTO> getAllCompetences(){

		TypedQuery<Competence> getAll = em.createNamedQuery("Competence.findAll", Competence.class);
		List<Competence> resultQuery = getAll.getResultList();
		List<CompetenceDTO> finalResultList = new ArrayList<CompetenceDTO>();
		for (int i = 0; i < resultQuery.size(); i++) {
			CompetenceDTO resObject = new CompetenceDTO();
			resObject.setName(resultQuery.get(i).getName());
			finalResultList.add(resObject);
		}

		return finalResultList;
	}

	//Eventuellt vill vi dela upp koden i mindre bitar... Men börjar med att bara skriva skiten

	public List <Person> getAllPersons(){
		return em.createNamedQuery("Person.findAll", Person.class).getResultList();
	}

	public List<Person> getAllApplicants(){
		String sql = "Select p from Person p INNER JOIN p.role r where r.roleId= :param1";
		return em.createQuery(sql, Person.class).setParameter("param1", applicant).getResultList();
	}
	
	
	/**
	 * Get all Persons that fulfills searchCriteria in SearchDTO
	 * It's enough if one availability or competence of listed fullfills criteria
	 * @param s
	 * @return
	 */
	public List<Person> searchPersons(SearchCriteriaDTO s){
		
		List<Person> personList = getAllApplicants();
		List<Person> returnList = new ArrayList<Person>();
		
		for(Person p : personList) {
			Boolean accept = true;
			if(!s.getName().isEmpty()){
				if(!p.getName().equals(s.getName())){
					accept = false;
				}
			}
			if(!s.getSurname().isEmpty()) {
				if(!p.getSurname().equals(s.getSurname())){
					accept = false;
				}
			}
			boolean isFrom = false;
			boolean isTo = false;
			for(Availability a : p.getAvailabilities()){
				if(s.getFromDate().isBefore(a.getFromDate().toLocalDate())){
					isFrom = true;
					break;
				}
			}
			for(Availability a : p.getAvailabilities()){
				if(s.getToDate().isAfter(a.getToDate().toLocalDate())){
					isTo= true;
					break;
				}
			}
			if(!isFrom || !isTo){
				accept = false;
			}
			 
			if(!s.getCompetence().isEmpty()){
				boolean isCompetence = false;
				for(CompetenceProfile cp : p.getCompetenceProfiles()){
					if(s.getCompetence().equals(cp.getCompetence().getName())){
						isCompetence = true;
					}
				}
				if(!isCompetence){
					accept = false;
				}
			}
			if(accept){
				returnList.add(p);
			}
		}
		return returnList;

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
