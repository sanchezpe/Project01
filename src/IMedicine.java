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
    void createMedicine(String name, LocalTime tMax, double halfLife);

    /**
     * Corresponds to all existing doses. Includes type Dose and TestDose.
     * This method can be used to list all doses since Dose implements toString() method.
     *
     * @return ArrayList<IDose> An array containing all doses.
     */
    ArrayList<IDose> getDoses();

    /**
     * Adds a new dose. Added doses can be type Dose (actual dose) or TestDose (what-if dose)
     *
     * @param dose Dose object.
     */
    void addDose(IDose dose);

    /**
     * Remove an specified dose.
     *
     * @param dose Dose object.
     */
    void removeDose(IDose dose);

    /**
     * Remove all doses of type TestDose.
     */
    void removeTestDoses();

    /**
     * Get the concentration amount at a specific time.
     *
     * @param time LocalTime object. Time in hours and minutes.
     * @return Concentration amount at specific time.
     */
    double getConcentrationAtTime(LocalTime time);

}
