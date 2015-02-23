package arquilliantest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.Test;
import org.junit.runner.RunWith;

import dto.*;
import model.*;
import javax.inject.Inject;
import org.jboss.shrinkwrap.api.ArchivePaths;
import resources.*;

import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;

/**
 * For testing the applicant
 */
@RunWith(Arquillian.class)
public class ApplicantTest 
{
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(GeneralDAO.class)
                .addPackage(Person.class.getPackage())
    		.addPackage(PersonDTO.class.getPackage())
    		.addAsManifestResource("test-persistence.xml",ArchivePaths.create("persistence.xml"))
    		.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    
    
    @Inject  
    private GeneralDAO dao;
    
    /**
     * Test to see if deployment is working
     * Will always pass
     */
    @Test
    public void testIsDeployed()
    {
        Assert.assertNotNull(dao);
    }
   
    /**
     * Test to see that the DAO works correctly with the Person Entity
     * Also checks that data is stored correctly
     */
    @Test
    public void testNewPerson()
    {
         //Code to be added later
    }
}
