package resources;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.interceptor.Interceptors;
import javax.persistence.NoResultException;

import util.LoggingInterceptor;
import model.Availability;
import model.CompetenceProfile;
import model.Person;
import model.Competence;
import dto.ApplicantDTO;
import dto.AvailabilityDTO;
import dto.CompetenceDTO;
import dto.CompleteApplicationDTO;
import dto.PersonDTO;
import dto.RoleDTO;
import dto.SearchCriteriaDTO;
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



	/**
	 * Get all the available competences in the DB from the DAO
	 * @return - List of CompetenceDTO which will be available
	 */
	public List<CompetenceDTO> getAllCompetences(){
		return dao.getAllCompetences();
	}
	
	
	//Diskutabelt om detta är för mycket kod för att vara i controller...?
	//Men å andra sidan har vi änså länge bara en sådan bloated metod varför vi kanske kan låta vara sålänge.
	/**
	 * Get all applications available. Only permitted by user in an admin role
	 * @return a List of all applications available
	 */
	//@RolesAllowed("admin")
	public List<CompleteApplicationDTO> searchApplications(SearchCriteriaDTO searchCriteriaDTO){
		
		List <Person> personList = dao.searchPersons(searchCriteriaDTO);
		List<CompleteApplicationDTO> completeApplicationList = new ArrayList<>();
		for(Person p : personList){
			
			CompleteApplicationDTO completeApplication = new CompleteApplicationDTO();
			completeApplication.setPersonDTO(TransformEntityToDTO.getPersonDTO(p));
			completeApplication.setRoleDTO(TransformEntityToDTO.getRoleDTO(p.getRole()));
			List<AvailabilityDTO> availabilityDTOList = new ArrayList<>();
			for(Availability a : p.getAvailabilities()){
				availabilityDTOList.add(TransformEntityToDTO.getAvailabilityDTO(a));
			}
			completeApplication.setAvailabilityDTO(availabilityDTOList);
			
			List<CompetenceDTO> competenceDTOList = new ArrayList<>();
			for(CompetenceProfile c : p.getCompetenceProfiles()){
				competenceDTOList.add(TransformEntityToDTO.getCompetenceDTO(c));
			}
			completeApplication.setCompetenceDTO(competenceDTOList);
			completeApplicationList.add(completeApplication);
		}
		return completeApplicationList;
	}
	
	public void test(SearchCriteriaDTO s){
		dao.searchPersons(s);
	}
	//public void doSomeOtherStuff

}
