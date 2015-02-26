package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import resources.ControllerEJB;
import dto.ApplicantDTO;
import dto.AvailabilityDTO;
import dto.CompetenceDTO;
import dto.PersonDTO;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import model.Competence;

import exception.NoSuchCompetenceException;


@ManagedBean
@SessionScoped
public class ApplicationBean {

	
	@Inject
	ControllerEJB controller;
	
	@Inject
	Logger logger;
	
	private String firstName;
	private String lastName;
	private String ssn;
	private String email;
	private String password;
	private String userName;
	private LocalDate fromDate;
        private String fromDateDay;
        private String fromDateMonth;
        private String fromDateYear;
        
	private LocalDate toDate;
        private String toDateDay;
        private String toDateMonth;
        private String toDateYear;
        
	//Vill väl egentligen ha nedanstående i lista?
	private String competenceName;
	private String competenceDuration;
	private CompetenceDTO competence;
        private List<AvailabilityDTO> availabilityList = new ArrayList<AvailabilityDTO>();
        private List<String> days = new ArrayList<String>();
        private List<String> months = new ArrayList<String>();
        private List<String> years = new ArrayList<String>();
        
        private List<CompetenceDTO> competenceList = new ArrayList<CompetenceDTO>();
	private List<CompetenceDTO> existingCompetences = new ArrayList<CompetenceDTO>();
        private List<String> dropdownCompetences = new ArrayList<String>();

      
        private void initAvailability(){
            
            for (int i = 1; i < 13; i++) {
                months.add(Integer.toString(i));
            }
            for (int i = 1; i < 32; i++) {
                days.add(Integer.toString(i));
            }
            for (int i = 2014; i < 2020; i++) {
                years.add(Integer.toString(i));
            }
            
            
            
            
        }

    public String getFromDateDay() {
        return fromDateDay;
    }

    public void setFromDateDay(String fromDateDay) {
        this.fromDateDay = fromDateDay;
    }

    public String getFromDateMonth() {
        return fromDateMonth;
    }

    public void setFromDateMonth(String fromDateMonth) {
        this.fromDateMonth = fromDateMonth;
    }

    public String getFromDateYear() {
        return fromDateYear;
    }

    public void setFromDateYear(String fromDateYear) {
        this.fromDateYear = fromDateYear;
    }

    public String getToDateDay() {
        return toDateDay;
    }

    public void setToDateDay(String toDateDay) {
        this.toDateDay = toDateDay;
    }

    public String getToDateMonth() {
        return toDateMonth;
    }

    public void setToDateMonth(String toDateMonth) {
        this.toDateMonth = toDateMonth;
    }

    public String getToDateYear() {
        return toDateYear;
    }

    public void setToDateYear(String toDateYear) {
        this.toDateYear = toDateYear;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public List<String> getMonths() {
        return months;
    }

    public void setMonths(List<String> months) {
        this.months = months;
    }

    public List<String> getYears() {
        return years;
    }

    public void setYears(List<String> years) {
        this.years = years;
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
        public List<AvailabilityDTO> getAvailabilityList() {
                return availabilityList;
        }

        public List<CompetenceDTO> getCompetenceList() {
                return competenceList;
        }
        public List<CompetenceDTO> getExistingCompetences() {
            return existingCompetences;
        }
        
        public void addCompetence(){
            CompetenceDTO cDTO = new CompetenceDTO(competenceName, Integer.parseInt(competenceDuration));
            competenceList.add(cDTO);
            competenceName = "";
            competenceDuration = "";
            
        }
        
        
        
        public void clearCompetences(){
            competenceList.clear();
        }
        
        //this probably will not work without regex validation and conversion!!!!! NOT TESTED
	public void addAvailability(){
            
            AvailabilityDTO aDTO = new AvailabilityDTO();
            LocalDate from = LocalDate.of(Integer.parseInt(fromDateYear), Integer.parseInt(fromDateMonth), Integer.parseInt(fromDateDay));
            LocalDate to = LocalDate.of(Integer.parseInt(toDateYear), Integer.parseInt(toDateMonth), Integer.parseInt(toDateDay));
            
            
            aDTO.setFromDate(from);
            aDTO.setToDate(to);
            availabilityList.add(aDTO);
          
        }
        
        public void clearAvailabilities(){
           availabilityList.clear();
        }
        public List<String> getDropdownCompetences() {
        dropdownCompetences.clear();
        for (int i = 0; i < existingCompetences.size(); i++) {
                dropdownCompetences.add(existingCompetences.get(i).getName());
            }
        
        return dropdownCompetences;
        }
        
	//early version do not use. Is ok to use for test.
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
		try {
			controller.addApplication(personDTO, availabilityList, competenceList);
		} catch (NoSuchCompetenceException e) {
			logger.log(Level.WARNING, "Name of competence: " + e.getName(), e);
			
		}
	}
 
        
        
        //will be used with the form for registration part 1
        public String savePerson(){
          
        
            //add validation before reroute to next step
            return "success";
        }
        
        
        //will be used with the form for registration part 3
        public String submitAvailability(){
            AvailabilityDTO aDTO = new AvailabilityDTO();
            aDTO.setFromDate(fromDate);
            aDTO.setToDate(toDate);
            
            return "fail";
        }
        
        
        //will be used with the form for registration part 2
        public String submitCompetence(){
            CompetenceDTO cDTO = new CompetenceDTO(competenceName, Integer.parseInt(competenceDuration));
            
            //add validation before reroute to next step
            return "fail";
        }
        
        
        /**
	 * Get all the available competences in the DB from controller
	 * @return - List of CompetenceDTO which will be available for the dropdowns in the application process
	 */
        @PostConstruct
        public void getAllCompetences(){
            
            existingCompetences =  controller.getAllCompetences();
            initAvailability();
           // SelectItem convert data..
        }
        
        
        /**
	 * Will take all the data submitted by the user and call the 
	 * @return - right now no return value
	 */
        public String approveApplicant(){
            PersonDTO pDTO = new PersonDTO();
            pDTO.setFirstName(firstName);
            pDTO.setLastName(lastName);
            pDTO.setSsn(ssn);
            pDTO.setEmail(email);
            pDTO.setUserName(userName);
            pDTO.setPassword(password);
            
            try {
			 controller.addApplication(pDTO, availabilityList, competenceList);
		} catch (NoSuchCompetenceException e) {
			logger.log(Level.WARNING, "Name of competence: " + e.getName(), e);
			
		}
           
            availabilityList.clear();
            competenceList.clear();
            userName="";
            lastName="";
            email="";
            ssn="";
            userName="";
            password="";
            
            return "success";
        }
	
}
