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
	
	public void addApplication(PersonDTO persDTO, List<AvailabilityDTO> availDTO, List<CompetenceDTO> compDTO) {
		Person p = dao.insertPerson(persDTO);
		dao.insertAvailability(availDTO, p);
		dao.insertCompetence(compDTO, p);
		
	}
	//public void doSomeOtherStuff
}
