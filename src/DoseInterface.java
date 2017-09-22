import java.time.LocalTime;

public interface DoseInterface {

    /**
     * @return
     */
    public Double getDoseAmount();

    /**
     * @return
     */
    public LocalTime getDoseTakenTime();

    /**
     * @param givenTime
     * @return
     */
    public Double getConcentrationAmount(LocalTime givenTime);

    /**
     * @return
     */
    public Double getConcentrationCurrentAmount();

    /**
     * @return
     */
    public String toString();

    // Advanced

    /**
     * @param doseAmount
     * @param givenTime
     * @return
     */
    public Double whatIf(Double doseAmount, LocalTime... givenTime);

    /**
     * @return
     */
    public Double getConcentrationPeak();

    /**
     * @return
     */
    public LocalTime whenToDose();

    /**
     * @return
     */
    public Medicine getMedicine();

    /**
     *
     */
    public void parseMedicine(Medicine medicine);
}
