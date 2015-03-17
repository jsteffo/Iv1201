package dto;

import java.math.BigDecimal;

/**
 * Class used to transfer data related to a Competence entity
 * @author stefan
 *
 */
public class CompetenceDTO {

	private String name;
	private BigDecimal yearsOfExperience;
	public CompetenceDTO(String name, BigDecimal yearsOfExperience) {
		super();
		this.name = name;
		this.yearsOfExperience = yearsOfExperience;
	}
        public CompetenceDTO() {
		super();
	}
        
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(BigDecimal yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	
	
}
