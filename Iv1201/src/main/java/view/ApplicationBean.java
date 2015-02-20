package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import resources.ControllerEJB;
import dto.ApplicantDTO;
import dto.AvailabilityDTO;
import dto.CompetenceDTO;
import dto.PersonDTO;

@ManagedBean
@SessionScoped
public class ApplicationBean {

	
	@Inject
	ControllerEJB controller;
	
	private String firstName;
	private String lastName;
	private String ssn;
	private String email;
	private String password;
	private String userName;
	private LocalDate fromDate;
	private LocalDate toDate;
	//Vill väl egentligen ha nedanstående i lista?
	private String competenceName;
	private String competenceDuration;
	private CompetenceDTO competence;
	
	
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
	public String getCompetenceName() {
		return competenceName;
	}
	public void setCompetenceName(String competenceName) {
		this.competenceName = competenceName;
	}
	public String getCompetenceDuration() {
		return competenceDuration;
	}
	public void setCompetenceDuration(String competenceDuration) {
		this.competenceDuration = competenceDuration;
	}
	public CompetenceDTO getCompetence() {
		return competence;
	}
	public void setCompetence(CompetenceDTO competence) {
		this.competence = competence;
	}
	
	
	public void submit(){
		//Add just a bunch of data. Rest of person data is null for now
		PersonDTO personDTO = new PersonDTO();
		personDTO.setFirstName(firstName);
		personDTO.setLastName(lastName);
		personDTO.setSsn(ssn);
		
		//Add competences. Make sure supplied names exist in DB
		List <CompetenceDTO> competenceList = new ArrayList<CompetenceDTO>();
		CompetenceDTO cDTO = new CompetenceDTO("kock", 5);
		competenceList.add(cDTO);
		cDTO = new CompetenceDTO("dykare", 3);
		competenceList.add(cDTO);
		
		
		//Add availabilities. For person
		List<AvailabilityDTO> availabilityList= new ArrayList<AvailabilityDTO>();
		AvailabilityDTO aDTO  = new AvailabilityDTO();
		aDTO.setFromDate(LocalDate.of(2014, 1, 1));
		aDTO.setToDate(LocalDate.of(2014, 3, 3));
		availabilityList.add(aDTO);
		aDTO  = new AvailabilityDTO();
		aDTO.setFromDate(LocalDate.of(2015, 2, 2));
		aDTO.setToDate(LocalDate.of(2015, 4, 4));
		availabilityList.add(aDTO);
		controller.addApplication(personDTO, availabilityList, competenceList);
	}
	
}
