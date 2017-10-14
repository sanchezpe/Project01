import java.time.LocalTime;

//TestDose implements IDose and a Dose implements IDose
public interface IDose {

    /**
     * Identifies dose type.
     *
     * @return True = TestDose (what-if dose); False = Dose (actual dose);
     */
    boolean isTestDose();

    /**
     * Creates a Dose object.
     *
     * @param timeTake Time when the dose is taken by patient.
     * @param amount   Concentration amount of the dose taken by patient.
     */
    void createDose(LocalTime timeTake, double amount);

    /**
     * Time when the dose is taken by patient.
     *
     * @return LocalTime Time of Dose.
     */
    LocalTime getTimeTake();

    /**
     * Concentration amount of the dose taken by patient.
     *
     * @return Concentration amount of Dose.
     */
    double getAmount();

    /**
     * Sets dose to test dose. Used for the what-if feature of the program.
     */
    void setTestDose();
}