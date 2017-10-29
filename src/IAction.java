import java.time.LocalDateTime;
import java.time.LocalTime;

public interface IAction {
    /**
     * Retrieves the Medicine instance.
     *
     * @return IMedicine.Medicine instance
     */
    IMedicine getMedicine();

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
     * Remove a dose by its index.
     *
     * @param index index of dose
     */
    void removeDose(int index);

    /**
     * Delete all doses from the dosages array.
     */
    void removeAllDoses();

    /**
     * Calculate the total concentration amount of the medicine (sum of all doses amount )at a specific dateTime.
     *
     * @param localDateTime specified dateTime.
     * @return Concentration amount at specified time.
     */
    Double getConcentrationAtTime(LocalDateTime localDateTime);

    /**
     * Save Medicine to a file.
     * By default, save directory is user's home directory.
     * Dose and Medicine MUST implement Serializable for this feature to work.
     *
     * @param filename Name of save file to be saved.
     */
    void saveFile(String filename);

    /**
     * Load a saved Medicine file.
     * By default, load directory is user's home directory.
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

    //Advanced Features

    /**
     * Removes all test doses.
     */
    void removeTestDoses();
}