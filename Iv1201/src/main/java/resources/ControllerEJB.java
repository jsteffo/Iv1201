package resources;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dto.ApplicantDTO;
/**
 * The methods to be seen from managed been through dependency injection
 *
 */
public class ControllerEJB {

	@Inject
	private GeneralDAO dao;
	
	public void addApplicant(ApplicantDTO dto){
		dao.insertPerson(dto);
		dao.insertAvailability(dto);
		//Call etc etc...
	}
	//public void doSomeOtherStuff
}
