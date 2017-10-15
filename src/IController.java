import java.time.LocalTime;

public interface IController {

    /**
     * Creates an instance of LocalTime from user input.
     *
     * @return
     */
    static LocalTime createLocalTimeFromUserInput(int hour, int minute) {
        return null;
    }

    /**
     * Creates a Dose instance from user input.
     *
     * @return IDose. A Dose created from user input
     */
    static IDose createDoseFromUserInput(int hour, int minute, double amount) {
        return null;
    }

    /**
     * Starts the welcome screen of the program.
     */
    static void start() {
    }

    /**
     * Starts the action menu of the program.
     */
    static void selectAction() {
    }

    /**
     * Clears the text from the console.
     */
    static void clear() {
    }
}

