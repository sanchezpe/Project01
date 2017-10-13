//main likely uses the class that implements IController to do the work
public interface IController {
    /**
     *
     */
    void printAllDoses();

    /**
     * @param dose
     */
    //void printSpecificDose(IDose dose);

    /**
     * @param timeInSeconds
     * @return
     */
    IDose getDoseBasedOnTime(int timeInSeconds);

    /**
     * @param userAnnoyance
     * @return
     */
    //IAction whatTheHellDoesTheUserWant(String userAnnoyance);

}

