package Model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public interface IMedicine {
    /**
     * Name of medicine.
     *
     * @return Name of the medicine.
     */
    String getNameMedicine();

    /**
     * Half life time of the medicine.
     *
     * @return Half life time of the medicine.
     */
    LocalTime getTimeHalfLifeMedicine();

    /**
     * Tmax of medicine
     *
     * @return TMax of the medicine.
     */
    LocalTime getTimeMaxMedicine();

    /**
     * Corresponds to all existing doses. Includes type Dose and TestDose.
     *
     * @return An array containing all doses.
     */
    ArrayList<IDose> getDoses();

    /**
     * Creates a Medicine instance.
     *
     * @param nameMedicine         Name of medicine.
     * @param timeMaxMedicine      Time required for medicine to be at its peak concentration.
     * @param timeHalfLifeMedicine Time required for medicine to decrease by half.
     */
    void createMedicine(String nameMedicine, LocalTime timeMaxMedicine, LocalTime timeHalfLifeMedicine);

    /**
     * Add a Dose to doses array.
     *
     * @param dateTimeTakeDose Time dose is taken.
     * @param amount           Amount of dose.
     * @param isTestDose       defines whether is test dose.
     */
    void addDose(LocalDateTime dateTimeTakeDose, double amount, Boolean isTestDose);

    /**
     * Removes all doses from the doses array in the medicine.
     */
    void removeAllDoses();

    /**
     * Remove a dose by its index.
     *
     * @param index index of dose.
     */
    void removeDose(int index);

    /**
     * Remove all test doses from the doses array.
     */
    void removeTestDoses();

    /**
     * String representation of Medicine.
     *
     * @return String medicine name, time max, and half life.
     */
    String toString();
}
