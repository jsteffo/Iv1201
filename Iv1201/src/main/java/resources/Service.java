package resources;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Person;
import dto.CompleteApplicationDTO;
import dto.PersonDTO;
/**
 * Restless service used to show all applicants in database
 * @author stefan
 *
 */
@Path("/applicants")
@Produces(MediaType.APPLICATION_XML)
public class Service {

	@EJB
	GeneralDAO dao;
	@GET
	public List<PersonDTO> allApplicants() {
//		dto.Person p = new dto.Person();
//		p.setEmail("jsteffo@gmail.com");
//		p.setName("Stefan");
//		p.setPhone("1234");
//		return p; 
		ArrayList<PersonDTO> returnList = new ArrayList<PersonDTO>();
		for(Person p : dao.getAllApplicants()) {
			returnList.add(TransformEntityToDTO.getPersonDTO(p));
		}
		return returnList;
		
	}
}
