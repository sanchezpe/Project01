import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DoseTest {

    Dose d = new Dose();

    @BeforeEach
    void setUp() {
        d.createDose(LocalTime.now(), 1);
    }

    @AfterEach
    void tearDown() {
        d = new Dose();
    }

    @Test
    void isTestDose() {
        assertFalse(d.isTestDose());

    }

    @Test
    void createDose() {
        LocalTime mytime = LocalTime.now();
        d.createDose(mytime, 20.3);
        assertTrue(d.getAmount() == 20.3);
        assertTrue(d.getTimeTake() == mytime);
        System.out.print(d.toString());

        LocalTime othertime = LocalTime.of(21, 53);
        d.createDose(othertime, 2);
        assertTrue(d.getAmount() == 2);
        assertTrue(d.getTimeTake() == othertime);
        System.out.print(d.toString());
    }

    @Test
    void getAmount() {
        assertTrue(d.getAmount() == 1);

        d.createDose(LocalTime.now(), 2.5);
        assertTrue(d.getAmount() == 2.5);

        tearDown();
        d.createDose(LocalTime.of(2,30), -5);
        assertEquals(d.getAmount(), 0);

    }

    @Test
    void getTimeTake() {
        LocalTime time = LocalTime.of(11, 00);
        d.createDose(time, 4.2);
        assertEquals(d.getTimeTake(), time);
    }

    @Test
    void setTestDose() {
        assertFalse(d.isTestDose());
        System.out.print(d.toString());

        d.setTestDose();
        assertTrue(d.isTestDose());
        System.out.print(d.toString());
    }

}