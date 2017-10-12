import java.time.LocalTime;

//TestDose implements IDose and a Dose implements IDose
public interface IDose {

    /**
     *
     * @return
     */
    boolean areYouATestDose();

    /**
     *
     * @param timeTake
     * @param amount
     */
    void createDose(int timeTake, float amount);

    /**
     *
     * @param timeInSeconds
     * @return
     */
    //float getConcentrationAtTime(int timeInSeconds);

    /**
     *
     * @return
     */
    String getDisplayVersion();

    //needed(?)
    double getAmount();
}
