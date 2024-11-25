


import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tpioc2.entity.Salle;
import tpioc2.service.SalleService;


public class SalleServiceTest {
    private SalleService salleService;
    private Salle salle;

    public SalleServiceTest() {
    }

    @Before
    public void setUp() {
        this.salleService = new SalleService();
        this.salle = new Salle();
        this.salle.setCode("A101");
        this.salleService.create(this.salle);
    }

    @After
    public void tearDown() {
        Salle foundSalle = this.salleService.findById(this.salle.getId());
        if (foundSalle != null) {
            this.salleService.delete(foundSalle);
        }

    }

    @Test
    public void testCreate() {
        Assert.assertNotNull("Salle should have been created with an ID", this.salle.getId());
    }

    @Test
    public void testFindById() {
        Salle foundSalle = this.salleService.findById(this.salle.getId());
        Assert.assertNotNull("Salle should be found", foundSalle);
        Assert.assertEquals("Found salle should match", this.salle.getCode(), foundSalle.getCode());
    }

    @Test
    public void testUpdate() {
        this.salle.setCode("B202");
        boolean result = this.salleService.update(this.salle);
        Assert.assertTrue("Salle should be updated successfully", result);
        Salle updatedSalle = this.salleService.findById(this.salle.getId());
        Assert.assertEquals("Updated salle code should match", "B202", updatedSalle.getCode());
    }

    @Test
    public void testDelete() {
        boolean result = this.salleService.delete(this.salle);
        Assert.assertTrue("Salle should be deleted successfully", result);
        Salle foundSalle = this.salleService.findById(this.salle.getId());
        Assert.assertNull("Salle should not be found after deletion", foundSalle);
    }

    @Test
    public void testFindAll() {
        List<Salle> salles = this.salleService.findAll();
        Assert.assertNotNull("Salles list should not be null", salles);
        Assert.assertTrue("Salles list should contain at least one salle", salles.size() > 0);
    }
}

