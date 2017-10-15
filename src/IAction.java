import java.time.LocalTime;

public interface IAction {
    /**
     * Prints all doses from Medicine's Dose array.
     */
    void printDoses();

    /**
     * Adds a new dose. Added doses can be type Dose (actual dose) or TestDose (what-if dose)
     *
     * @param dose Dose object.
     */
    void addDose(IDose dose);

    /**
     * Remove an specified dose.
     *
     * @param dose Dose object.
     */
    //void removeDose(IDose dose);

    /**
     * Delete all doses from the dosages array.
     */
    void removeAllDoses();

    /**
     * Save Medicine to a file.
     * Dose and Medicine MUST implement Serializable for this feature to work.
     *
     * @param filename Name of save file to be saved.
     */
    void saveFile(String filename);

    /**
     * Load a saved Medicine file.
     * Dose and Medicine MUST implement Serializable for this feature to work.
     *
     * @param filename Name of the file to be loaded
     */
    void loadFile(String filename);

    /**
     * Create a new Medicine file.
     *
     * @param name     Name of medicine
     * @param tMax     TMax of medicine.
     * @param halfLife Half life of medicine
     */
    void newFile(String name, LocalTime tMax, LocalTime halfLife);

    /**
     * @return
     */
    //IMedicine getMedicine();

    /**
     * Print the name, tmax, and halfLife of the medicine using Medicine toString() method.
     */
    void printMedicine();

    /**
     * Calculate concentration amount at an specified time.
     *
     * @param time Specified time to calculate concentration amount.
     * @return ArrayList with the current concentration amount.
     */
    //ArrayList<Double> getCurrentConcentration(LocalTime time);

    /**
     * @param concentrations
     */
    //void printCurrentConcentration(ArrayList<Double> concentrations);

    /**
     * @param time
     */
    void printCurrentConcentration(LocalTime time);

    //void removeTestDoses();


    //ArrayList<Double> getPeakConcentrationTime(Boolean includeTestDoses);

    //void printPeakConcentration(Boolean includeTestDoses);

    /**
     * @param amountDesired
     * @return
     */
    //ArrayList<Double> whenToDose(Double amountDesired);

}