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
     *
     * @return An array containing all doses.
     */
    ArrayList<IDose> getDoses();

    /**
     * Name of medicine.
     *
     * @return Return name of the medicine.
     */
    String getName();

    /**
     * Half life time of the medicine.
     *
     * @return Return the half life time of the medicine.
     */
    LocalTime getHalfLife();

    /**
     * Tmax of medicine
     *
     * @return Return TMax of medicine.
     */
    LocalTime getTmax();
}
