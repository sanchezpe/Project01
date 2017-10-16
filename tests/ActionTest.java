import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ActionTest {
    Action a = new Action();

    @BeforeEach
    void setUp() {
        a.newFile("TestMedicine", LocalTime.of(8, 30), LocalTime.of(3, 0));
        a.addDose(new Dose(LocalTime.of(9, 0), 3.8));

        Dose d = new Dose(LocalTime.of(6, 6), 33.8);
        d.setTestDose();
        a.addDose(d);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void printDoses() {
        assertEquals(a.getMedicine().getDoses().size(), 2);
        a.printDoses();
    }

    @Test
    void addDose() {
        a.addDose(new Dose(LocalTime.of(3, 20), 6.8));
        assertEquals(a.getMedicine().getDoses().size(), 3);
        a.printDoses();
    }

    @Test
    void removeDose() {
        a.removeDose(0);

        //remaining dose changes from index 1 to 0
        a.removeDose(0);
        assertTrue(a.getMedicine().getDoses().isEmpty());
    }

    @Test
    void removeAllDoses() {
        a.addDose(new Dose(LocalTime.of(3, 20), 6.1));
        a.addDose(new Dose(LocalTime.of(4, 40), 7));
        a.addDose(new Dose(LocalTime.of(5, 10), 6.3));

        a.printDoses();
        a.removeAllDoses();
        assertTrue(a.getMedicine().getDoses().isEmpty());
    }

    @Test
    void getCurrentConcentration() {
        a.addDose(new Dose(LocalTime.of(3, 20), 6.1));
        a.addDose(new Dose(LocalTime.of(4, 40), 7));
        a.addDose(new Dose(LocalTime.of(5, 10), 6.3));
        System.out.println(a.getCurrentConcentration(LocalTime.now()));
    }

    @Test
    void printCurrentConcentration() {
        a.printCurrentConcentration(LocalTime.of(3, 20));
    }

    @Test
    void getPeakConcentrationTime() {
        System.out.println(a.getPeakConcentrationTime(true));
        System.out.println(a.getPeakConcentrationTime(false));
    }

    @Test
    void printPeakConcentrationTime() {
        a.printPeakConcentrationTime(true);
        a.printPeakConcentrationTime(false);
    }

    @Test
    void getWhenToDose() {
        System.out.println(a.getWhenToDose(62.3));
    }

    @Test
    void printWhenToDose() {
        a.printWhenToDose(62.3);
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
        a.newFile("NewTestMedicine", LocalTime.of(2, 50), LocalTime.of(4, 2));
        a.printMedicine();
    }

    @Test
    void printMedicine() {
        a.printMedicine();
    }

    @Test
    void removeTestDoses() {
        a.removeTestDoses();
        a.printDoses();
        assertEquals(a.getMedicine().getDoses().size(), 1);
    }

    @Test
    void getMedicine() {
        a.getMedicine();
    }

}