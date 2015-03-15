package resources;

import java.time.LocalDate;

import dto.*;
import model.*;
/**
 * Utility class to help transform entity to DTO
 * @author stefan
 *
 */
public class TransformEntityToDTO {
	
	public static PersonDTO getPersonDTO(Person p){
		PersonDTO personDTO = new PersonDTO();
		personDTO.setEmail(p.getEmail());
		personDTO.setFirstName(p.getName());
		personDTO.setLastName(p.getSurname());
		personDTO.setSsn(p.getSsn());
		personDTO.setUserName(p.getUsername());
		return personDTO;
	}
	public static RoleDTO getRoleDTO(Role r){
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setName(r.getName());
		return roleDTO;
	}
	public static CompetenceDTO getCompetenceDTO(CompetenceProfile cp){
		CompetenceDTO competenceDTO = new CompetenceDTO();
		competenceDTO.setName(cp.getCompetence().getName());
		competenceDTO.setYearsOfExperience(cp.getYearsOfExperience());
		return competenceDTO;
	}
	public static AvailabilityDTO getAvailabilityDTO(Availability a) {
		AvailabilityDTO availabilityDTO = new AvailabilityDTO();
		
		availabilityDTO.setFromDate(a.getFromDate().toLocalDate());
		availabilityDTO.setToDate(a.getToDate().toLocalDate());
		return availabilityDTO;
	}
	
}
