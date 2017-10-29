import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class MedicineTest {
    private Medicine m = new Medicine();

    @BeforeEach
    void setUp() {
        m.createMedicine("Aspirin", LocalTime.of(9, 30), LocalTime.of(0, 30));

        m.addDose(new Dose(LocalDateTime.parse("2017-10-29T08:00"), 32));
        m.addDose(new Dose(LocalDateTime.parse("2017-10-29T09:00"), 10, true));
    }

    @AfterEach
    void tearDown() {
        m = new Medicine();
    }

    @Test
    void createMedicine() {
        m.createMedicine("Oxycodone", LocalTime.of(8, 0), LocalTime.of(1, 30));
        assertEquals(m.getNameMedicine(), "Oxycodone");
        assertEquals(m.getTimeMaxMedicine().toString(), "08:00");
        assertEquals(m.getTimeHalfLifeMedicine().toString(), "01:30");
    }

    @Test
    void getNameMedicine() {
        assertEquals(m.getNameMedicine(), "Aspirin");
    }

    @Test
    void getTimeMaxMedicine() {
        assertEquals(m.getTimeMaxMedicine().toString(), "09:30");
    }

    @Test
    void getTimeHalfLifeMedicine() {
        assertEquals(m.getTimeHalfLifeMedicine().toString(), "00:30");
    }

    @Test
    void getDoses() {
        assertFalse(m.getDoses().isEmpty());
        System.out.println(m.getDoses() + "\n");
    }

    @Test
    void addDose() {
        assertFalse(m.getDoses().isEmpty());
        System.out.println(m.getDoses() + "\n");

        m.addDose(new Dose(LocalDateTime.parse("2017-10-29T09:30"), 18));
        m.addDose(new Dose(LocalDateTime.parse("2017-10-29T09:30"), 18, true));
        assertEquals(m.getDoses().size(), 4);
        System.out.println(m.getDoses() + "\n");
    }

    @Test
    void removeAlldoses() {
        m.removeAllDoses();
        assertTrue(m.getDoses().isEmpty());
    }

    @Test
    void removeDose() {
        assertEquals(m.getDoses().size(), 2);

        m.removeDose(0);
        assertEquals(m.getDoses().size(), 1);
        System.out.println(m.getDoses() + "\n");
    }

    @Test
    void getConcentrationsAtTime() {

        double concentrationAtTime = m.getConcentrationsAtTime(LocalDateTime.parse("2017-10-29T09:00"));

        assertEquals(concentrationAtTime, 3.368421052631579);
        System.out.println(concentrationAtTime + "\n");
    }

    @Test
    void removeTestDoses() {
        m.removeTestDoses();
        assertEquals(m.getDoses().size(), 1);
        System.out.println(m.getDoses() + "\n");
    }

}