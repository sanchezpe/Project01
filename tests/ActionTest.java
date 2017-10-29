import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ActionTest {
    private Action a = new Action();

    @BeforeEach
    void setUp() {
        a = new Action(new Medicine("Aspirin", LocalTime.of(9, 30), LocalTime.of(0, 30)));

        a.addDose(new Dose(LocalDateTime.parse("2017-10-29T08:00"), 32));
        a.addDose(new Dose(LocalDateTime.parse("2017-10-29T09:00"), 10, true));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getMedicine() {
        assertEquals(a.getMedicine().toString(), "Medicine: Aspirin   TMax: 09:30   Half Life: 00:30");
    }

    @Test
    void printMedicine() {
        a.printMedicine();
    }

    @Test
    void printDoses() {
        a.printDoses();
    }

    @Test
    void addDose() {
        a.addDose(new Dose(LocalDateTime.parse("2017-10-29T08:30"), 12));
        a.addDose(new Dose(LocalDateTime.parse("2017-10-29T08:45"), 15, true));
        assertEquals(a.getMedicine().getDoses().size(), 4);
        a.printDoses();
    }

    @Test
    void removeDose() {
        a.removeDose(0);
        assertEquals(a.getMedicine().getDoses().size(), 1);
        a.printDoses();
    }

    @Test
    void removeAllDoses() {
        a.removeAllDoses();
        assertTrue(a.getMedicine().getDoses().isEmpty());
    }

    @Test
    void getConcentrationAtTime() {
        Double concentrationAtTime = a.getConcentrationAtTime(LocalDateTime.parse("2017-10-29T08:45"));
        assertEquals(concentrationAtTime, (Double) 2.5263157894736845);
    }

    @Test
    void printConcentrationAtTime() {
        a.printConcentrationAtTime(LocalDateTime.parse("2017-10-29T08:45"));
    }

    @Test
    void printDetailedConcentrationAtTime() {
        a.printDetailedConcentrationAtTime(LocalDateTime.parse("2017-10-29T08:45"));
    }

    @Test
    void saveFile() {
        a.saveFile("medicineTestFile");
    }

    @Test
    void loadFile() {
        a.loadFile("medicineTestFile");
        a.printMedicine();
        a.printDoses();
    }

    @Test
    void newFile() {
        a.newFile("Oxycodone", LocalTime.of(8, 0), LocalTime.of(1, 30));
        a.printMedicine();
        assertTrue(a.getMedicine().getDoses().isEmpty());
    }

    @Test
    void removeTestDoses() {
        a.removeTestDoses();
        assertEquals(a.getMedicine().getDoses().size(), 1);
    }

}