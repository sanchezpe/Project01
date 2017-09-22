public interface DosesInterface {
    /**
     * @return
     */
    public String listAllDoses();

    /**
     *
     */
    public void clearAllDoses();

    /**
     *
     */
    public void saveDosesToFile();

    /**
     *
     */
    public void loadDosesFromFile();


    /**
     * @return
     */
    public String toString();

}
