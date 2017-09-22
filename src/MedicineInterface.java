import java.time.LocalTime;

public interface MedicineInterface {

    /**
     * @return
     */
    public String getMedicineName();

    /**
     * @return
     */
    public LocalTime getMedicineTMax();

    /**
     * @return
     */
    public LocalTime getHalfLife();


    /**
     * @return
     */
    public String toString();


}
