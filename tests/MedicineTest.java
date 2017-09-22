import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MedicineTest {

    Medicine testMedicine;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testMedicineName() throws Exception {

        testMedicine = new Medicine("", 0, 0);
        assertTrue(testMedicine.getMedicineName().isEmpty());

        testMedicine = new Medicine(null, 0, 0);
        assertEquals(testMedicine.getMedicineName(), null);

        testMedicine = new Medicine("Aspirin", 0, 0);
        assertEquals(testMedicine.getMedicineName(), "Aspirin");
    }


    @Test
    public void testMedicineTmax() throws Exception {

        testMedicine = new Medicine("", 0, 0);
        assertTrue(testMedicine.getMedicineTMax() <= 0);

        testMedicine = new Medicine("", 8, 0);
        assertTrue(testMedicine.getMedicineTMax() > 0);
        assertEquals(testMedicine.getMedicineTMax(), 8);

    }

    @Test
    public void testMedicineHalfLife() throws Exception {

        testMedicine = new Medicine("", 0, 0);
        assertTrue(testMedicine.getHalfLife() <= 0);

        testMedicine = new Medicine("", 0, 16);
        assertTrue(testMedicine.getHalfLife() > 0);
        assertEquals(testMedicine.getHalfLife(), 16);
    }

}