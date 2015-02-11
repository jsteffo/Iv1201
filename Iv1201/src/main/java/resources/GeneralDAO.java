package resources;



import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Person;
import dto.ApplicantDTO;

@Stateless
public class GeneralDAO {
	
	@PersistenceContext
	EntityManager em;

	public void insertPerson(ApplicantDTO dto){
	
		Person p = new Person();
		p.setName(dto.getFirstName());
		p.setSurname(dto.getLastName());
		p.setEmail(dto.getEmail());
		p.setPassword(dto.getPassword());
	
		p.setSsn(dto.getSsn());
		p.setUsername(dto.getUserName());
		em.persist(p);
	}
	
	//insertRole
//	public void addRole(String str){
//		em.create...
//	}
	
}
