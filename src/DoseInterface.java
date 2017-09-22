import java.sql.Time;

public interface DoseInterface {

    /**
     * @return
     */
    public Double getDoseAmount();

    /**
     * @return
     */
    public Time getDoseTakenTime();

    /**
     * @param givenTime
     * @return
     */
    public Double getConcentrationAmount(Time givenTime);

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
    public Double whatIf(Double doseAmount, Time... givenTime);

    /**
     * @return
     */
    public Double getConcentrationPeak();

    /**
     * @return
     */
    public Time whenToDose();

    /**
     * @return
     */
    public Medicine getMedicine();

    /**
     *
     */
    public void parseMedicine(Medicine medicine);
}
