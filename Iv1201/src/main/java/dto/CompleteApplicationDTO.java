package dto;

import java.util.List;

/**
 * This class represent a complete application by a User
 * @author stefan
 *
 */
public class CompleteApplicationDTO {

	private List<AvailabilityDTO> availabilityDTO;
	private List<CompetenceDTO> competenceDTO;
	private PersonDTO personDTO;
	private RoleDTO roleDTO;
	
	public List<AvailabilityDTO> getAvailabilityDTO() {
		return availabilityDTO;
	}
	public void setAvailabilityDTO(List<AvailabilityDTO> availabilityDTO) {
		this.availabilityDTO = availabilityDTO;
	}
	public List<CompetenceDTO> getCompetenceDTO() {
		return competenceDTO;
	}
	public void setCompetenceDTO(List<CompetenceDTO> competenceDTO) {
		this.competenceDTO = competenceDTO;
	}
	public PersonDTO getPersonDTO() {
		return personDTO;
	}
	public void setPersonDTO(PersonDTO personDTO) {
		this.personDTO = personDTO;
	}
	public RoleDTO getRoleDTO() {
		return roleDTO;
	}
	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}
	
	
}
