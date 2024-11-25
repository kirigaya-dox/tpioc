package tpioc2.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tpioc2.entity.Machine;
import tpioc2.entity.Salle;
import tpioc2.service.MachineService;
import tpioc2.service.SalleService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        SalleService salleService = new SalleService();
        MachineService machineService = new MachineService();
        // Création des salles
        Salle salle1 = new Salle("A1");
        Salle salle2 = new Salle("B2");
        salleService.create(salle1);
        salleService.create(salle2);

        // Vérification de l'identifiant des salles après la création
        System.out.println("Salle 1 ID: " + salle1.getId());
        System.out.println("Salle 2 ID: " + salle2.getId());

        // Création des machines
        Machine machine1 = new Machine("M123", LocalDateTime.now(), salle1);
        Machine machine2 = new Machine("M124", LocalDateTime.now(), salle2);
        machineService.create(machine1);
        machineService.create(machine2);

        // Affichage des salles et leurs machines
        List<Salle> salles = salleService.findAll();
        for (Salle salle : salles) {
            System.out.println("Salle: " + salle.getCode());
            for (Machine machine : salle.getMachines()) {
                System.out.println("  Machine: " + machine.getRef());
            }
        }

        // Utilisation de la méthode findBetweenDate
        LocalDateTime d1 = LocalDateTime.of(2010, 1, 1, 0, 0); // 1er janvier 2010
        LocalDateTime d2 = LocalDateTime.now(); // Date actuelle
        System.out.println("Machines achetées entre " + d1 + " et " + d2 + ":");

        for (Machine m : machineService.findBetweenDate(d1, d2)) {
            System.out.println(m.getRef() + " achetée le " + m.getDateAchat());
        }
    }
}
