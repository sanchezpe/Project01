import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public interface IMedicine {
    /**
     * Name of medicine.
     *
     * @return Return name of the medicine.
     */
    String getNameMedicine();

    /**
     * Half life time of the medicine.
     *
     * @return Return the half life time of the medicine.
     */
    LocalTime getTimeHalfLifeMedicine();

    /**
     * Tmax of medicine
     *
     * @return Return TMax of medicine.
     */
    LocalTime getTimeMaxMedicine();

    /**
     * Corresponds to all existing doses. Includes type Dose and TestDose.
     *
     * @return An array containing all doses.
     */
    ArrayList<IDose> getDoses();

    /**
     * Creates a medicine object.
     *
     * @param nameMedicine         Name of medicine.
     * @param timeMaxMedicine      Time when medicine is at its peak concentration.
     * @param timeHalfLifeMedicine Time required for medicine to decrease by half.
     */
    void createMedicine(String nameMedicine, LocalTime timeMaxMedicine, LocalTime timeHalfLifeMedicine);

    /**
     * Calculate the total concentration amount of the medicine (sum of all doses amount )at a specific dateTime.
     *
     * @param dateTime specified dateTame
     * @return Concentration amount of dose at a specified time.
     */
    Double getConcentrationsAtTime(LocalDateTime dateTime);

    /**
     * Create an IDose Object
     *
     * @param dose IDose Object
     */
    void addDose(IDose dose);

    /**
     * Removes all doses from the doses array in the medicine
     */
    void removeAllDoses();

    /**
     * Remove a dose by its index.
     *
     * @param index index of dose
     */
    void removeDose(int index);

    /**
     * Remove all test doses from the doses array
     */
    void removeTestDoses();
}
