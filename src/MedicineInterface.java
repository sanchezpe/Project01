import java.sql.Time;

public interface MedicineInterface {

    /**
     * @return
     */
    public String getMedicineName();

    /**
     * @return
     */
    public Time getMedicineTMax();

    /**
     * @return
     */
    public Time getHalfLife();


    /**
     * @return
     */
    public String toString();


}
