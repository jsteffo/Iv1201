package exception;

import javax.ejb.ApplicationException;

import dto.CompetenceDTO;

/**
 * Exception to be thrown when data to be persisted requires persisted data that is missing
 * @author stefan
 *
 */
//Gör just nu ingen skillnad vilket är märkligt då det skall defaulta till false
@ApplicationException(rollback=true)
public class NoSuchCompetenceException extends Exception {

  private static final long serialVersionUID = 1L;
	
  private String name;



  public NoSuchCompetenceException(String name) {
	  super();
	  this.name= name;
  }
  public String getName(){
	  return name;
  }
}
