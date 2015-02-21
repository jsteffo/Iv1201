package resources;

import java.util.List;

import javax.inject.Inject;

import model.Person;
import dto.ApplicantDTO;
import dto.AvailabilityDTO;
import dto.CompetenceDTO;
import dto.PersonDTO;
/**
 * The methods to be seen from managed been through dependency injection
 *
 */
public class ControllerEJB {

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
	 */
	public void addApplication(PersonDTO persDTO, List<AvailabilityDTO> availDTO, List<CompetenceDTO> compDTO) {
		Person p = dao.insertPerson(persDTO);
		dao.insertAvailability(availDTO, p);
		dao.insertCompetenceProfile(compDTO, p);
		
	}
	//public void doSomeOtherStuff
}
