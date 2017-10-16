import java.time.LocalTime;
import java.util.ArrayList;

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
     * Removes a dose from the dose from the doses array.
     *
     * @param index Index of the array to be erased
     */
    void removeDose(int index);

    /**
     * Delete all doses from the dosages array.
     */
    void removeAllDoses();

    /**
     * Time when the concentration is at its peak.
     * It allows the user to choose if test doses will be displayed or not.
     *
     * @param includeTestDoses Determines whether test doses will be displayed.
     * @return ArrayList containing peak concentration peak values of each dose.
     */
    ArrayList<LocalTime> getPeakConcentrationTime(Boolean includeTestDoses);

    /**
     * Print concentration peaks. Includes dose information and its concentration peak.
     *
     * @param includeTestDoses Determines whether test doses will be displayed.
     */
    void printPeakConcentrationTime(Boolean includeTestDoses);

    /**
     * Determines time when the patient should take next dose.
     *
     * @param concentrationDesired Dose amount desired sought.
     * @return Time when the patient have to take next dose.
     */
    ArrayList<LocalTime> getWhenToDose(Double concentrationDesired);

    /**
     * Prints when the patient should take next dose.
     * Includes dose information and time to take next dose.
     *
     * @param amountDesired Dose amount desired sought.
     */
    void printWhenToDose(Double amountDesired);

    /**
     * Save Medicine to a file.
     * By default, save directory is same directory as the application.
     * Dose and Medicine MUST implement Serializable for this feature to work.
     *
     * @param filename Name of save file to be saved.
     */
    void saveFile(String filename);

    /**
     * Load a saved Medicine file.
     * By default, load directory is same directory as the application.
     * Dose and Medicine MUST implement Serializable for this feature to work.
     *
     * @param filename Name of the file to be loaded
     */
    void loadFile(String filename);

    /**
     * Create a new Medicine instance.
     *
     * @param name     Name of medicine
     * @param tMax     TMax of medicine.
     * @param halfLife Half life of medicine
     */
    void newFile(String name, LocalTime tMax, LocalTime halfLife);

    /**
     * Print the name, tmax, and halfLife of the medicine.
     */
    void printMedicine();


    /**
     * Calculates concentration amount of doses at a specific time.
     *
     * @param time Specific time used to determine concentration amount.
     * @return ArrayList with the concentration amounts.
     */
    ArrayList<Double> getCurrentConcentration(LocalTime time);

    /**
     * Prints concentration amount of doses at a specific time.
     *
     * @param time Specific time used to determine concentration amount.
     */
    void printCurrentConcentration(LocalTime time);

    /**
     * Removes all test doses.
     */
    void removeTestDoses();

    /**
     * Retrieves the Medicine instance.
     *
     * @return Medicine instance
     */
    IMedicine getMedicine();
}