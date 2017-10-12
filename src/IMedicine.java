import java.util.ArrayList;

public interface IMedicine {
    /**
     *
     * @param name
     * @param tMax
     * @param halfLife
     */
    void createMedicine(String name, double tMax, double halfLife);

    //actual doses

    /**
     *
     * @param dose
     */
    void addDose(IDose dose);

    /**
     *
     * @param dose
     */
    void removeDose(IDose dose);

    //test doses

    /**
     *
     * @param dose
     */
    void addTestDose(IDose dose);

    /**
     *
     *
     */
    void removeTestDoses();

    //get Concentrations;

    /**
     *
     * @param timeInSeconds
     * @return
     */
    double getConcentrationAtTime(int timeInSeconds);

    //list doses

    /**
     *
     * @return
     */
    ArrayList<IDose> getDoses();
}
