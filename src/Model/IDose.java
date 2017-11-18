package Model;

import java.time.LocalDateTime;

//TestDose implements IDose and a Dose implements IDose
public interface IDose {
    /**
     * Creates a Dose instance
     *
     * @param dateTimeTakeDose Date and time when dose is taken.
     * @param amount           Amount of dose taken.
     * @param isTestDose       Defines whether the dose is a test dose.
     */
    void createDose(LocalDateTime dateTimeTakeDose, double amount, Boolean isTestDose);

    /**
     * Time when the dose is taken by patient.
     *
     * @return LocalTime Time of Dose.
     */
    LocalDateTime getDateTimeTakeDose();

    /**
     * Concentration amount of the dose taken by patient.
     *
     * @return Concentration amount of Dose.
     */
    Double getAmountDose();

    /**
     * String representation of Dose.
     *
     * @return Dose type, dose amount, and dose taken time.
     */
    String toString();

    //Advanced Features

    /**
     * Identifies dose type.
     *
     * @return True = TestDose (what-if dose); False = Dose (actual dose);
     */
    boolean getIsTestDose();
}