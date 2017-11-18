package Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DoseTest {
    //input validation done in Controller class
    private Dose d = new Dose();

    @BeforeEach
    void setUp() {
        d.createDose(LocalDateTime.parse("2017-11-18T12:00"), 10, false);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createDose() {
        System.out.println(d + "\n");

        d.createDose(LocalDateTime.parse("2017-11-17T21:00"), 20, false);
        System.out.println(d);

        //default values
        d = new Dose();
        System.out.println(d + "\n");
    }

    @Test
    void getAmountDose() {
        assertEquals(d.getAmountDose(), (Double) 10.0);
    }

    @Test
    void getDateTimeTakeDose() {
        assertEquals(d.getDateTimeTakeDose(), LocalDateTime.parse("2017-11-18T12:00"));
    }

    @Test
    void getIsTestDose() {
        assertFalse(d.getIsTestDose());

        d.createDose(LocalDateTime.parse("2017-11-17T21:00"), 20, true);
        assertTrue(d.getIsTestDose());
    }

}