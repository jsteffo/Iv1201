package resources;

import java.util.Random;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Person;
import dto.ApplicantDTO;

@Stateless
public class GeneralDAO {
	
	@PersistenceContext
	EntityManager em;
	
	private int i = 222;
	public void insertPerson(ApplicantDTO dto){
		i++;
		Person p = new Person();
		p.setName(dto.getFirstName());
		p.setSurname(dto.getLastName());
		p.setEmail(dto.getEmail());
		p.setPassword(dto.getPassword());
		
		p.setPersonId(Integer.toString(i));
		
		p.setSsn(dto.getSsn());
		p.setUsername(dto.getUserName());
		em.persist(p);
	}
	
	//insertRole
//	public void addRole(String str){
//		em.create...
//	}
	
}
