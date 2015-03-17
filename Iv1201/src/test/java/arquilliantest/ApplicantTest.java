package arquilliantest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.Test;
import org.junit.runner.RunWith;

import dto.*;
import exception.NoSuchCompetenceException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import model.*;
import util.*;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ArchivePaths;
import resources.*;

import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;

/**
 * For testing Persistence and DAO
 */
@RunWith(Arquillian.class)
public class ApplicantTest 
{
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(GeneralDAO.class)
                .addClass(NoSuchCompetenceException.class)
                .addPackage(Person.class.getPackage())
    		.addPackage(PersonDTO.class.getPackage())
                .addPackage(LoggingInterceptor.class.getPackage())
    		.addAsManifestResource("test-persistence.xml",ArchivePaths.create("persistence.xml"))
    		.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @PersistenceContext
    EntityManager em;
    
    
    @Inject  
    private GeneralDAO dao;
    
    @Inject
    UserTransaction utx;
    
    /**
     * Test to see if deployment is working
     * If it doesn't pass, something wrong with Arquillian itself
     */
    @Test @InSequence(1)
    public void testIsDeployed()
    {
        Assert.assertNotNull(dao);
    }
   
    /**
     * Adds recruiter and applicant Roles, adds a tester Competence
     * @throws Exception 
     */
    @Test @InSequence(2)
    public void addData() throws Exception
    {
        utx.begin();
        em.joinTransaction();

        Role recruit = new Role();
        recruit.setName("recruit");
        em.persist(recruit);
        
        Role applicant = new Role();
        applicant.setName("applicant");
        //applicant.setRoleId(Long.valueOf(2));
        em.persist(applicant);
        
        Competence tester = new Competence();
        tester.setName("Tester");
        em.persist(tester);
        
        utx.commit();
        
        Competence found = em.find(Competence.class, Long.valueOf(1));
        Assert.assertEquals("Tester", found.getName());
    }
    
    /**
     * Tests the Person entity
     */
    
    @Test @InSequence(3)
    public void testPerson()
    {
        PersonDTO pdto = new PersonDTO();
        pdto.setFirstName("John");
        pdto.setLastName("Doe");
        pdto.setSsn("1010");
        dao.insertPerson(pdto);
        
        List<Person> pList = dao.getAllPersons();
        Person p = pList.get(0);
        Assert.assertEquals("Doe", p.getSurname());
    }
    
    /**
     * Tests the Role entity
     */
    
    @Test @InSequence(4)
    public void testRole()
    {
        PersonDTO pdto = new PersonDTO();
        pdto.setFirstName("Jane");
        pdto.setLastName("Doe");
        pdto.setSsn("2020");
        dao.insertPerson(pdto); //Assigns role of applicant
        
        List<Person> pList = dao.getAllPersons();
        Person p = pList.get(0);
        Role r = p.getRole();
        Assert.assertEquals(2, r.getRoleId()); //2 is roleId for applicant as seen in GeneralDAO
    }
    
    /**
     * Tests getAllApplicants method
     * @throws Exception 
     */
    
    @Test @InSequence(5)  
    public void testApplicants() throws Exception
    {
        utx.begin();
        em.joinTransaction();
        
        Person p = new Person();
        p.setName("Recruiter");
        p.setSurname("Admin");
        p.setSsn("3030");
        p.setRole(em.find(Role.class, Long.valueOf(1))); //Sets role as recruit
        
        em.persist(p);
        utx.commit();
        
        List<Person> pList = dao.getAllApplicants();
        Assert.assertEquals(2, pList.size()); //Only 2 applicants, 1 recruit
    }
    
    /**
     * Tests the Availability entity
     */
    
    @Test @InSequence(6)
    public void testAvailability()
    {
        List<AvailabilityDTO> aList = new ArrayList<AvailabilityDTO>();
        AvailabilityDTO adto = new AvailabilityDTO();
        
        LocalDate from = LocalDate.of(2015, Month.FEBRUARY, 1);
        LocalDate to = LocalDate.of(2015, Month.MARCH, 1);
        
        adto.setFromDate(from);
        adto.setToDate(to);
        aList.add(adto);
        
        List<Person> pList = dao.getAllPersons();
        Person p = pList.get(0);
        dao.insertAvailability(aList, p);
        
        
        //List<Availability> aNewList = p.getAvailabilities();
        //Availability a = aNewList.get(0);
        
        Availability a = em.find(Availability.class, Long.valueOf(1));
        Assert.assertEquals("John", a.getPerson().getName());
    }
    
    /**
     * Test the Competence entity
     * @throws Exception 
     */
    
    @Test @InSequence(7)
    public void testCompetence() throws Exception
    {
        List<CompetenceDTO> cList = new ArrayList<CompetenceDTO>();
        
        CompetenceDTO cdto = new CompetenceDTO("Tester", BigDecimal.valueOf(3));
        cList.add(cdto);
        
        List<Person> pList = dao.getAllPersons();
        Person p = pList.get(0);
        dao.insertCompetenceProfile(cList, p);
        
        CompetenceProfile cp = em.find(CompetenceProfile.class, Long.valueOf(1));
        Assert.assertEquals("John", cp.getPerson().getName());     
    }
    
    
    /**
     * Test getAllCompetences
     * @throws Exception 
     */
    
    @Test @InSequence(8)
    public void testAllCompetence() throws Exception
    {
        utx.begin();
        em.joinTransaction();
        
        Competence c1 = new Competence();
        c1.setName("Programmer");
        em.persist(c1);
        
        Competence c2 = new Competence();
        c2.setName("Engineer");
        em.persist(c2);
        
        Competence c3 = new Competence();
        c3.setName("Teacher");
        em.persist(c3);
        
        utx.commit();
        
        List<CompetenceDTO> cList = dao.getAllCompetences();
        Assert.assertEquals(4, cList.size());
    }
}
