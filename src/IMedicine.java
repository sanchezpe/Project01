import java.time.LocalTime;
import java.util.ArrayList;

public interface IMedicine {
    /**
     * Creates a medicine object.
     *
     * @param name     Name of medicine.
     * @param tMax     Time when medicine is at its peak concentration.
     * @param halfLife Time required for medicine to decrease by half.
     */
    void createMedicine(String name, LocalTime tMax, LocalTime halfLife);

    /**
     * Corresponds to all existing doses. Includes type Dose and TestDose.
     * This method can be used to list all doses since Dose implements toString() method.
     *
     * @return An array containing all doses.
     */
    ArrayList<IDose> getDoses();

    String getName();

    /**
     * Half life time of the medicine.
     *
     * @return Return the half life time of the medicine
     */
    LocalTime getHalfLife();

    LocalTime getTmax();
}
