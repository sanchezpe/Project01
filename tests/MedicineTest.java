import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class MedicineTest {
    Medicine m = new Medicine();

    @BeforeEach
    void setUp() {
        m.createMedicine("Aspirin", LocalTime.of(9, 30), LocalTime.of(0, 30));
    }

    @AfterEach
    void tearDown() {
        m = new Medicine();
    }

    @Test
    void createMedicine() {
        System.out.println(m.toString());

        m.createMedicine("Oxycodone", LocalTime.of(8, 0), LocalTime.of(1, 30));
        System.out.println(m.toString());

    }

    @Test
    void getDosess() {
        assertTrue(m.getDoses().isEmpty());
    }

    @Test
    void getHalfLife() {
        assertEquals(m.getHalfLife(), LocalTime.of(0, 30));
    }
}