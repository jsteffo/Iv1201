package dto;

import java.time.LocalDate;
import java.util.List;

public class ApplicantDTO {

	private String firstName;
	private String lastName;
	private String ssn;
	private String email;
	private String password;
	private String userName;
	private LocalDate fromDate;
	private LocalDate toDate;
	private List<CompetenceDTO> competence;
	
	
	
	public ApplicantDTO(String firstName, String lastName, String ssn,
			String email, String password, String userName, LocalDate fromDate,
			LocalDate toDate, List<CompetenceDTO> competence) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.email = email;
		this.password = password;
		this.userName = userName;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.competence = competence;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	public List<CompetenceDTO> getCompetence() {
		return competence;
	}
	public void setCompetence(List<CompetenceDTO> competence) {
		this.competence = competence;
	}
	
}
