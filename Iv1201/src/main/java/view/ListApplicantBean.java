package view;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import model.Availability;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import resources.ControllerEJB;
import dto.AvailabilityDTO;
import dto.CompetenceDTO;
import dto.CompleteApplicationDTO;
import dto.PersonDTO;
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
	
	private List<CompleteApplicationDTO> completeApplicationList = new ArrayList<>();
	
	
	public String getCompetence() {
		return competence;
	}

	public List<CompleteApplicationDTO> getCompleteApplicationList() {
		return completeApplicationList;
	}

	public void setCompleteApplicationList(
			List<CompleteApplicationDTO> completeApplicationList) {
		this.completeApplicationList = completeApplicationList;
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


	public void submitSearchApplicants() {

	}
	/**
	 * Calls backend to look for application that fullfills search criteria
	 */
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


		setCompleteApplicationList(controller.searchApplications(s));

	}

	/**
	 * Transforms the complete application of an applicant to a pdf
	 * @param dto Used for transformation
	 */
	public void pdf(CompleteApplicationDTO dto){
		try{
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Document doc = new Document();
			PdfWriter.getInstance(doc, baos);
			doc.open();
			

			PersonDTO pDTO = dto.getPersonDTO();
			String firstName = "name: " + pDTO.getFirstName();
			String lastName = "lastname: " + pDTO.getLastName();
			String email = "email:" + pDTO.getEmail();
			String ssn = "ssn: " + pDTO.getSsn();
			
			
			doc.add(new Paragraph(firstName));
			doc.add(new Paragraph(lastName));
			doc.add(new Paragraph(email));
			doc.add(new Paragraph(ssn));
			
			List<AvailabilityDTO> aDTO = dto.getAvailabilityDTO();
			doc.add(new Paragraph("Availability:"));
			for(AvailabilityDTO a : aDTO) {
				String from = "from: " + a.getFromDate();
				String to  = "to : " + a.getToDate();
				doc.add(new Paragraph(from));
				doc.add(new Paragraph(to));
			}
			doc.add(new Paragraph("Competence:"));
			List<CompetenceDTO> competenceDTO = dto.getCompetenceDTO();
			for(CompetenceDTO c : competenceDTO){
				String competence = c.getName() + ", " + c.getYearsOfExperience();
				doc.add(new Paragraph(competence));
			}
			
			
			//doc.add(new Paragraph(text));
			doc.close();
			ec.setResponseHeader("Expires", "0");
			ec.setResponseHeader("Cache-Control",
					"must-revalidate, post-check=0, pre-check=0");
			ec.setResponseHeader("Pragma", "public");
			ec.setResponseContentType("application/pdf");
			ec.setResponseContentLength(baos.size());
			OutputStream os = ec.getResponseOutputStream();
			baos.writeTo(os);

			os.flush();
			os.close();
			fc.responseComplete();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
