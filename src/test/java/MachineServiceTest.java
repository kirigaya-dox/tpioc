
import org.hibernate.type.LocalDateTimeType;
import org.hibernate.type.LocalTimeType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tpioc2.entity.Machine;
import tpioc2.entity.Salle;
import tpioc2.service.MachineService;
import tpioc2.service.SalleService;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class MachineServiceTest {

    private  MachineService machineService;
    private Machine machine;
    private Salle salle;
    private SalleService salleService;

    @Before
    public void setUp() {
        machineService = new MachineService();
        salleService = new SalleService();
        salle = new Salle("A101"); // Exemple de salle
        // Vous devriez persister la salle avant d'utiliser
        salleService.create(salle); // Assurez-vous que salleService existe

        machine = new Machine();
        machine.setRef("MACH-001");
        machine.setDateAchat(LocalDateTime.now());
        machine.setSalle(salle);

        // Créer et persister la machine avant chaque test
        machineService.create(machine);
    }

    @After
    public void tearDown() {
        // Supprimer la machine après chaque test si elle existe
        Machine foundMachine = machineService.findById(machine.getId());
        if (foundMachine != null) {
            machineService.delete(foundMachine);
        }

        // Supprimer la salle après chaque test si elle existe
        Salle foundSalle = salleService.findById(salle.getId());
        if (foundSalle != null) {
            salleService.delete(foundSalle);
        }
    }

    @Test
    public void testCreate() {
        assertNotNull("Machine should have been created with an ID", machine.getId());
    }

    @Test
    public void testFindById() {
        Machine foundMachine = machineService.findById(machine.getId());
        assertNotNull("Machine should be found", foundMachine);
        assertEquals("Found machine should match", machine.getRef(), foundMachine.getRef());
    }

    @Test
    public void testUpdate() {
        machine.setRef("MACH-002"); // Modifiez la référence pour tester la mise à jour
        boolean result = machineService.update(machine);
        assertTrue("Machine should be updated successfully", result);

        Machine updatedMachine = machineService.findById(machine.getId());
        assertEquals("Updated machine ref should match", "MACH-002", updatedMachine.getRef());
    }

    @Test
    public void testDelete() {
        boolean result = machineService.delete(machine);
        assertTrue("Machine should be deleted successfully", result);

        Machine foundMachine = machineService.findById(machine.getId());
        assertNull("Machine should not be found after deletion", foundMachine);
    }

    @Test
    public void testFindBetweenDate() {
        // Utiliser LocalDateTime pour la plage de dates
        LocalDateTime yesterday = LocalDateTime.now().minus(1, ChronoUnit.DAYS); // Il y a 24 heures
        LocalDateTime today = LocalDateTime.now(); // Date actuelle

        // Appel de la méthode avec LocalDateTime
        List<Machine> machines = machineService.findBetweenDate(yesterday, today);

        // Vérifications
        assertNotNull("Machines list should not be null", machines);
        assertTrue("Machines list should contain at least one machine", machines.size() > 0);
    }
}
