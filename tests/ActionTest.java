import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ActionTest {
    Dose d = new Dose();
    Action a = new Action();

    @BeforeEach
    void setUp() {
        a.newFile("Test medicine", LocalTime.of(16, 0), LocalTime.of(22, 0));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void printDoses() {
        a.printDoses();

        a.addDose(new Dose(LocalTime.now(), 2.3));
        a.printDoses();
    }

    @Test
    void addDose() {
        a.printDoses();

        a.addDose(new Dose(LocalTime.of(6, 30), 2.3));
        a.printDoses();
    }

    @Test
    void removeDose() {
        a.addDose(new Dose(LocalTime.of(6, 30), 2.3));
        a.printDoses();

        a.removeDose(new Dose(LocalTime.of(6, 30), 2.3));
        a.printDoses();

    }

    @Test
    void removeAllDoses() {
        a.addDose(new Dose(LocalTime.of(6, 30), 2.3));
        a.addDose(new Dose(LocalTime.of(5, 30), 2.3));
        a.addDose(new Dose(LocalTime.of(4, 30), 2.3));

        a.removeAllDoses();
        a.printDoses();
    }

    @Test
    void getCurrentConcentration() {
        a.addDose(new Dose(LocalTime.of(6, 30), 2.3));
        a.getCurrentConcentration(LocalTime.of(8, 45)).get(0);

        //System.out.println(a.getCurrentConcentration(LocalTime.of(8, 45)).get(0));

        double d = a.getCurrentConcentration(LocalTime.of(8, 45)).get(0);
        assertEquals(d, 1.7458221401896885);
    }

    @Test
    void printCurrentConcentration() {
        a.addDose(new Dose(LocalTime.of(6, 30), 2.3));
        a.printCurrentConcentration(a.getCurrentConcentration(LocalTime.of(8, 45)));
    }

    @Test
    void saveFile() {
        a.saveFile("test");
    }

    @Test
    void loadFile() {
        a.loadFile("test");
        a.printMedicine();
    }

    @Test
    void newFile() {
        a.newFile("Other test medicine", LocalTime.now(), LocalTime.of(23, 59));
        a.printMedicine();
    }

    @Test
    void printMedicine() {
        a.printMedicine();
    }
}