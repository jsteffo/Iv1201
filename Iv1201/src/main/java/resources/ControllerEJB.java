package resources;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.interceptor.Interceptors;
import javax.persistence.NoResultException;

import util.LoggingInterceptor;
import model.Person;
import dto.ApplicantDTO;
import dto.AvailabilityDTO;
import dto.CompetenceDTO;
import dto.PersonDTO;
import exception.NoSuchCompetenceException;
/**
 * The methods to be seen from managed been through dependency injection
 *
 */
@Stateless
@Interceptors(LoggingInterceptor.class)
public class ControllerEJB {

	@Inject
	Logger logger;

	@Inject
	private GeneralDAO dao;

	//	public void addApplicant(ApplicantDTO dto){
	//		dao.insertPerson(dto);
	//		dao.insertAvailability(dto);
	//		//Call etc etc...
	//	}

	/**
	 * Add an applicant into the Database. An Applicant is a Person with associated data about its competence
	 * and availability.
	 * @param persDTO - Data describing Person
	 * @param availDTO - Data describing the availability of Person
	 * @param compDTO - Data describing the competence of Person
	 * @throws NoSuchCompetenceException 
	 */
	public void addApplication(PersonDTO persDTO, List<AvailabilityDTO> availDTO, List<CompetenceDTO> compDTO)
			throws NoSuchCompetenceException {
		Person p = dao.insertPerson(persDTO);
		dao.insertAvailability(availDTO, p);
		dao.insertCompetenceProfile(compDTO, p);
	}

}
