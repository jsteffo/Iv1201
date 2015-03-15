package view;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import resources.ControllerEJB;
import dto.CompleteApplicationDTO;
import dto.SearchCriteriaDTO;

@ManagedBean(name="listApplicantBean")
@SessionScoped
public class ListApplicantBean {


	@Inject
	Logger logger;

	@EJB
	ControllerEJB controller;
	private String fromDate;
	private String toDate;
	private String registrationDate; //Måste impl i DB
	private String name;
	private String surname;
	private String competence;

	public String getCompetence() {
		return competence;
	}

	public void setCompetence(String competence) {
		this.competence = competence;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	//Rimligt att tillåta null
	public void submitSearchApplicants() {

	}
	public void search(){
		System.out.println("Search pressed");

		SearchCriteriaDTO s = new SearchCriteriaDTO();
		//Om date ej existerar här. Sätt till max och min!
		if(fromDate.isEmpty()) {
			s.setFromDate(LocalDate.MIN);
		}
		else {
			String lineArray[] = fromDate.split("-");	
			s.setFromDate(LocalDate.of(Integer.parseInt(lineArray[0]), Integer.parseInt(lineArray[1]),
					Integer.parseInt(lineArray[2])));			

		}
		if(toDate.isEmpty()){
			s.setToDate(LocalDate.MAX);
		}	
		else {
			String lineArray[] = toDate.split("-");	
			s.setToDate(LocalDate.of(Integer.parseInt(lineArray[0]), Integer.parseInt(lineArray[1]), 
					Integer.parseInt(lineArray[2])));			

		}

		s.setCompetence(competence);
		s.setName(name);
		s.setSurname(surname);


		List<CompleteApplicationDTO> complete = controller.searchApplications(s);
		for(CompleteApplicationDTO c : complete ){
			System.out.println(c.getPersonDTO().getFirstName());
			System.out.println(c.getPersonDTO().getLastName());
			System.out.println(c.getCompetenceDTO().get(0).getName());
			System.out.println(c.getCompetenceDTO().get(0).getYearsOfExperience());
		}
	}
}
