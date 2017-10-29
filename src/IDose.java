import java.time.LocalDateTime;
import java.time.LocalTime;

//TestDose implements IDose and a Dose implements IDose
public interface IDose {
    /**
     * Creates a Dose object.
     *
     * @param dateTimeTakeDose Time when the dose is taken by patient.
     * @param amount           Concentration amount of the dose taken by patient.
     */
    void createDose(LocalDateTime dateTimeTakeDose, double amount);

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
     * Calculate dose concentration amount at a specified time.
     *
     * @param timeIn Time when dose is taken.
     * @param timeMaxIn tMax of medicine.
     * @param timeHalfLifeIn half life of medicine.
     * @return Concentration amount of dose amount at a specified time.
     */
    Double getConcentrationAtTime(LocalDateTime timeIn, LocalTime timeMaxIn, LocalTime timeHalfLifeIn);

    //Advanced Features

    /**
     * Identifies dose type.
     *
     * @return True = TestDose (what-if dose); False = Dose (actual dose);
     */
    boolean getIsTestDose();

    /**
     * Sets dose to test dose. Used for the what-if feature of the
     */
    void setTestDose();
}