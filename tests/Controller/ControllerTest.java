package Controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller c = new Controller();

    @BeforeEach
    void setUp() {
        c.newFile("Rivastigmine", LocalTime.parse("01:00"), LocalTime.parse("02:00"));
        c.addDose(LocalDateTime.parse("2017-11-18T12:00"), 10, false);
        c.addDose(LocalDateTime.parse("2017-11-18T14:00"), 12, true);
        c.addDose(LocalDateTime.parse("2017-11-18T13:00"), 20, false);
        c.addDose(LocalDateTime.parse("2017-11-18T15:00"), 16, true);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getMedicine() {
        System.out.println(c.getMedicine() + "\n");
    }

    @Test
    void addDose() {
        //valid
        c.addDose(LocalDateTime.parse("2017-11-18T18:30"), 66, false);

        //invalid
        c.addDose(LocalDateTime.parse("2017-11-18T18:30"), -1, false);
    }

    @Test
    void removeDose() {
        //valid index
        c.removeDose(3);
        assertEquals(c.getMedicine().getDoses().size(), 3);

        //invalid index
        c.removeDose(-1);
        c.removeDose(6);
        assertEquals(c.getMedicine().getDoses().size(), 3);
    }

    @Test
    void removeAllDoses() {
        assertEquals(c.getMedicine().getDoses().size(), 4);
        c.removeAllDoses();
        assertTrue(c.getMedicine().getDoses().isEmpty());
    }

    @Test
    void getConcentrationAtTime() {

        //valid
        assertEquals(c.getConcentrationAtTime(c.getMedicine().getDoses().get(0), LocalDateTime.parse("2017-11-18T12:30")), (Double) 5.0);
        assertEquals(c.getConcentrationAtTime(c.getMedicine().getDoses().get(0), LocalDateTime.parse("2017-11-18T13:00")), (Double) 10.0);
        assertEquals(c.getConcentrationAtTime(c.getMedicine().getDoses().get(0), LocalDateTime.parse("2017-11-18T17:30")), (Double) 2.5);

        //invalid
        assertEquals(c.getConcentrationAtTime(c.getMedicine().getDoses().get(0), LocalDateTime.parse("2017-11-18T11:59")), (Double) 0.0);
    }

    @Test
    void getSumConcentrationsAtTime() {
        System.out.println(c.getConcentrationAtTime(c.getMedicine().getDoses().get(0), LocalDateTime.parse("2017-11-18T16:00")));
        System.out.println(c.getConcentrationAtTime(c.getMedicine().getDoses().get(1), LocalDateTime.parse("2017-11-18T16:00")));
        System.out.println(c.getConcentrationAtTime(c.getMedicine().getDoses().get(2), LocalDateTime.parse("2017-11-18T16:00")));
        System.out.println(c.getConcentrationAtTime(c.getMedicine().getDoses().get(3), LocalDateTime.parse("2017-11-18T16:00")));

        System.out.println("\n" + c.getSumConcentrationsAtTime(LocalDateTime.parse("2017-11-18T16:00")));
        assertEquals(c.getSumConcentrationsAtTime(LocalDateTime.parse("2017-11-18T16:00")), (Double) 43.0);
    }

    @Test
    void saveFile() {
        c.saveFile("test");
    }

    @Test
    void loadFile() {
        c.newFile("Aspirin", LocalTime.parse("00:30"), LocalTime.parse("01:00"));
        System.out.println(c.getMedicine());

        c.loadFile("test");
        System.out.println(c.getMedicine());
    }

    @Test
    void newFile() {
        c.newFile("Dicyclomine", LocalTime.parse("02:30"), LocalTime.parse("06:00"));
        System.out.println(c.getMedicine());
    }

    @Test
    void createLocalDate() {

        //valid
        assertEquals(c.createLocalDate("2017-11-18"), LocalDate.parse("2017-11-18"));

        //Invalid dates samples
        //used in View
        //LocalDate date = c.createLocalDate("2017-11");
        //date = c.createLocalDate("2017-18-11");
        //date = c.createLocalDate("invalidDate");
    }

    @Test
    void createLocalTime() {
        assertEquals(c.createLocalTime("22:00"), LocalTime.parse("22:00"));
    }

    @Test
    void parseValidAmount() {
        //used in View
    }

    @Test
    void parseValidInt() {
        //used in View
    }

    @Test
    void removeTestDoses() {
        c.removeTestDoses();
        assertEquals(c.getMedicine().getDoses().size(), 2);
        System.out.println(c.getMedicine().getDoses() + "\n");
    }

    @Test
    void getPeakLevel() {
        assertEquals(c.getPeakLevel(c.getMedicine().getDoses().get(0)), LocalDateTime.parse("2017-11-18T13:00"));
    }

    @Test
    void getWhenToDose() {
        assertEquals(c.getWhenToDose(c.getMedicine().getDoses().get(0), 5),
                LocalDateTime.parse("2017-11-18T15:00"));

        assertEquals(c.getWhenToDose(c.getMedicine().getDoses().get(0), 2.5),
                LocalDateTime.parse("2017-11-18T17:00"));
    }
}