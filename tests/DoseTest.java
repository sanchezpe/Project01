import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class DoseTest {
    private Dose d = new Dose();
    private ZoneOffset timezone = ZoneOffset.of("-05:00");
    private LocalTime timeMax = LocalTime.of(1, 0);
    private LocalTime timeHalfLife = LocalTime.of(2, 0);

    @BeforeEach
    void setUp() {
        LocalDateTime dateTimeTake = LocalDateTime.parse("2017-10-29T12:00");
        d.createDose(dateTimeTake, 20);
    }

    @AfterEach
    void tearDown() {
        d = new Dose();
    }

    @Test
    void isTestDose() {
        assertFalse(d.getIsTestDose());
    }

    @Test
    void createDose() {
        d.createDose(LocalDateTime.parse("2099-12-31T23:59"), 50);

        System.out.println(d + "\n");
        assertEquals(d.getDateTimeTakeDose().toString(), "2099-12-31T23:59");
        assertEquals(d.getAmountDose(), (Double) 50.0);
    }

    @Test
    void getAmountDose() {
        assertEquals(d.getAmountDose(), (Double) 20.0);
    }

    @Test
    void getTimeTakeDose() {
        assertEquals(d.getDateTimeTakeDose().toString(), "2017-10-29T12:00");

    }

    @Test
    void setTestDose() {
        d.setTestDose();
        assertTrue(d.getIsTestDose());
    }

    @Test
    void getConcentrationAtTime() {

        LocalDateTime localDateTime = LocalDateTime.parse("2017-10-29T12:00");
        Double amountDose = d.getConcentrationAtTime(localDateTime, timeMax, timeHalfLife);
        /*System.out.println(amountDose);
        System.out.println(localDateTime.toEpochSecond(timezone));
        System.out.println(timeMax.toSecondOfDay());
        System.out.println();*/
        assertEquals(amountDose, (Double) 0.0);

        localDateTime = LocalDateTime.parse("2017-10-29T11:30");
        amountDose = d.getConcentrationAtTime(localDateTime, timeMax, timeHalfLife);
        /*System.out.println(amountDose);
        System.out.println(localDateTime.toEpochSecond(timezone));
        System.out.println(timeMax.toSecondOfDay());
        System.out.println();*/
        assertEquals(amountDose, (Double) 0.0);

        localDateTime = LocalDateTime.parse("2017-10-29T13:00");
        amountDose = d.getConcentrationAtTime(localDateTime, timeMax, timeHalfLife);
        /*System.out.println(amountDose);
        System.out.println(localDateTime.toEpochSecond(timezone));
        System.out.println(timeMax.toSecondOfDay());
        System.out.println();*/
        assertEquals(amountDose, (Double) 20.0);

        localDateTime = LocalDateTime.parse("2017-10-29T12:15");
        amountDose = d.getConcentrationAtTime(localDateTime, timeMax, timeHalfLife);
        /*System.out.println(amountDose);
        System.out.println(localDateTime.toEpochSecond(timezone));
        System.out.println(timeMax.toSecondOfDay());
        System.out.println();*/
        assertEquals(amountDose, (Double) 5.0);

        localDateTime = LocalDateTime.parse("2017-10-29T15:00");
        amountDose = d.getConcentrationAtTime(localDateTime, timeMax, timeHalfLife);
        /*System.out.println(amountDose);
        System.out.println(localDateTime.toEpochSecond(timezone));
        System.out.println(d.getDateTimeTakeDose().toEpochSecond(timezone));
        System.out.println(timeHalfLife.toSecondOfDay());
        System.out.println();*/
        assertEquals(amountDose, (Double) 10.0);
    }
}