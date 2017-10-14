import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class MedicineTest {
    Medicine m = new Medicine();
    Dose d = new Dose();

    @BeforeEach
    void setUp() {
        m.createMedicine("Aspirin", LocalTime.of(9, 30), 1.5);
    }

    @AfterEach
    void tearDown() {
        m = new Medicine();
        d = new Dose();
    }

    @Test
    void createMedicine() {

        m.createMedicine("Oxycodone", LocalTime.of(8, 0), .5);
        assertTrue(m.getDoses().isEmpty());
        System.out.println(m.toString());
    }

    @Test
    void addDose() {
        d.createDose(LocalTime.of(3, 50), 3);
        m.addDose(d);

        d.createDose(LocalTime.of(6, 32), .5);
        d.setTestDose();
        m.addDose(d);

        d.createDose(LocalTime.of(9, 21), 1.5);
        d.setTestDose();
        m.addDose(d);

        assertFalse(m.getDoses().isEmpty());
        m.printDoses();

    }

    @Test
    void removeDose() {
        addDose();

        m.removeDose(m.getDoses().get(0));

        m.printDoses();
    }

    @Test
    void removeTestDoses() {


        d.createDose(LocalTime.of(9, 21), 1.5);
        d.setTestDose();
        m.addDose(d);

        d.createDose(LocalTime.of(3, 50), 3);
        //d.setTestDose();
        m.addDose(d);

        d.createDose(LocalTime.of(9, 25), 1.5);
        d.setTestDose();
        m.addDose(d);

        //Method1
        /*ArrayList<IDose> testDoses = new ArrayList<>();
        for (IDose dose : m.getDoses()) {
            if (dose.isTestDose() ) {
                testDoses.add(dose);
            }
        }
        m.getDoses().removeAll(testDoses);*/

        //Method2
        /*ListIterator<IDose> iterator = m.getDoses().listIterator();
        while(iterator.hasNext()){
            if(iterator.next().isTestDose()){
                iterator.remove();
            }
        }*/

        /*for (ListIterator<IDose> iterator = m.getDoses().listIterator(); iterator.hasNext(); ) {
            if (iterator.next().isTestDose()) {
                iterator.remove();
            }
        }*/

        //Method3
        //m.getDoses().removeIf(IDose::isTestDose);
        m.removeTestDoses();

        m.printDoses();
    }

    @Test
    void getConcentrationAtTime() {

        d.createDose(LocalTime.of(7, 0), 3);
        m.addDose(d);

        //System.out.println(LocalTime.of(8, 0).toSecondOfDay());

        System.out.println(m.getConcentrationAtTime(LocalTime.of(8, 0)));

        System.out.println(Math.pow(.5, ((double)  28800 / 1.5)));
    }

    @Test
    void getDoses() {
        assertEquals(m.getDoses().size(), 0);
        assertTrue(m.getDoses().isEmpty());
        m.printDoses();
    }
}