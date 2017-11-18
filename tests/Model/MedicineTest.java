package Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class MedicineTest {
    //input validation done in Controller class
    private Medicine m = new Medicine();

    @BeforeEach
    void setUp() {
        m.createMedicine("Pimozide", LocalTime.of(1, 0), LocalTime.of(2, 0));
        m.addDose(LocalDateTime.parse("2017-11-18T08:00"), 56, false);
        m.addDose(LocalDateTime.parse("2017-11-18T22:30"), 22, true);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createMedicine() {
        System.out.println(m + "\n");

        m.createMedicine("Felodipine", LocalTime.of(0, 30), LocalTime.of(2, 30));
        System.out.println(m + "\n");

        //default values
        m = new Medicine();
        System.out.println(m + "\n");
    }

    @Test
    void getNameMedicine() {
        assertEquals(m.getNameMedicine(), "Pimozide");
    }

    @Test
    void getTimeHalfLifeMedicine() {
        assertEquals(m.getTimeHalfLifeMedicine(), LocalTime.parse("02:00"));
    }

    @Test
    void getDoses() {
        assertEquals(m.getDoses().size(), 2);

        m = new Medicine();
        assertTrue(m.getDoses().isEmpty());
    }

    @Test
    void getTimeMaxMedicine() {
        assertEquals(m.getTimeHalfLifeMedicine(), LocalTime.parse("02:00"));
    }

    @Test
    void addDose() {
        m.addDose(LocalDateTime.parse("2017-11-18T12:00"), 10, false);
        assertEquals(m.getDoses().size(), 3);

        System.out.print(m.getDoses() + "\n");
    }

    @Test
    void removeAllDoses() {
        assertEquals(m.getDoses().size(), 2);
        m.removeAllDoses();
        assertTrue(m.getDoses().isEmpty());
    }

    @Test
    void removeDose() {
        assertEquals(m.getDoses().size(), 2);
        m.removeDose(0);
        assertEquals(m.getDoses().size(), 1);
        System.out.print(m.getDoses() + "\n");
    }

    @Test
    void removeTestDoses() {
        assertEquals(m.getDoses().size(), 2);
        m.removeTestDoses();
        assertEquals(m.getDoses().size(), 1);
        System.out.print(m.getDoses() + "\n");
    }
}