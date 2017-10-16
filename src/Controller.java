import java.time.LocalTime;
import java.util.Scanner;

//User Interface
public class Controller {

    static Scanner userInput = new Scanner(System.in);
    static Action action = new Action();

    /**
     * Generates LocalTime instance from user input.
     * Validates the user input to minimize application crash due to unexpected errors.
     *
     * @return LocalTime instance.
     */
    private static LocalTime createLocalTime() {
        int hour;
        int minute;

        System.out.print("Enter hour: ");
        checkIfInteger();
        hour = userInput.nextInt();

        System.out.print("Enter minute: ");
        checkIfInteger();
        minute = userInput.nextInt();

        //checks if hour and minute is within valid range
        if ((hour >= 0 && hour < 24) && (minute >= 0 && minute < 60)) {
            return LocalTime.of(hour, minute);
        }

        //if range is incorrect, use local time instead
        System.out.println("Invalid time. Using current time instead.");
        pause();
        return LocalTime.now();
    }

    /**
     * Creates a Medicine instance from user input.
     */
    private static void createMedicine() {
        System.out.print("Enter medicine name: ");
        String name = userInput.next();

        System.out.println("Set up medicine TMax");
        LocalTime tMax = createLocalTime();

        System.out.println("Setup medicine half life");
        LocalTime halfLife = createLocalTime();

        action.newFile(name, tMax, halfLife);
    }

    /**
     * Creates Dose from user input.
     *
     * @return A Dose instance
     */
    private static IDose createDose() {
        System.out.println("Create a dose");
        LocalTime takeTime = createLocalTime();

        System.out.print("Enter dose amount: ");
        checkIfValidNumber();

        double amount = userInput.nextDouble();
        return new Dose(takeTime, amount);

    }

    /**
     * Removes dose using an index selected by the user.
     */
    private static void removeDose() {
        System.out.print("Enter dose index");
        if (userInput.nextInt() < action.getMedicine().getDoses().size()) {
            action.removeDose(userInput.nextInt());
        }
        System.out.println("Invalid index");
    }

    /**
     * Validates if user input is an integer only.
     * If input is not an integer, the program stops.
     */
    private static void checkIfInteger() {
        if (!userInput.hasNextInt()) {
            System.out.println("Error: Input is not an integer number");
            System.exit(1);
        }
    }

    /**
     * Validates if user input is a number, either Integer or Double.
     * If input is not an number, the program stops.
     */
    private static void checkIfValidNumber() {
        if (!(userInput.hasNextInt() || userInput.hasNextDouble())) {
            System.out.println("Error: Input is not a valid number");
            System.exit(1);
        }
    }

    /**
     * Clears the console screen.
     */
    private static void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    /**
     * Pause scrolling fot the console screen until user hits the Enter key.
     */
    private static void pause() {
        System.out.println("Press \"ENTER\" to continue...");
        userInput.nextLine();
        userInput.nextLine();
    }

    /**
     * Starts the program.
     * Display a welcome screen which allows the user to select whether to create a file or open an existing one.
     */
    private static void start() {
        System.out.println("Welcome! \n");
        System.out.println("What do you want to do?");
        System.out.println("1. Create new file");
        System.out.println("2. Load existing file");
        System.out.print("Enter a number: ");

        switch (userInput.next()) {
            case "1":
                createMedicine();
                clear();
                break;
            case "2":
                System.out.print("Enter filename: ");
                action.loadFile(userInput.next());
                clear();
                break;

            case "exit":
                System.exit(0);
                break;

            default:
                System.out.println("Invalid user input");
                System.exit(0);
                break;
        }
    }

    /**
     * Display the list of actions that can be performed by the application.
     * The user must enter the corresponding number to execute the action.
     */
    private static void selectAction() {
        //Executes this screen until the user exit the program.
        do {
            IDose d;

            action.printMedicine();
            System.out.println("What do you want to do?");
            System.out.println("1. List all doses");
            System.out.println("2. Add a dose");
            System.out.println("3. Remove a dose");
            System.out.println("4. Remove all doses");
            System.out.println("5. Display current concentration amount");
            System.out.println("6. Display concentration amount at a specific time");
            System.out.println("7. Save file and exit");
            System.out.println("\nAdvanced Features");
            System.out.println("8. Add a test dose");
            System.out.println("9. Time of highest peak");
            System.out.println("10. When to dose");
            System.out.println("11. Remove all test doses");
            System.out.print("Enter a number: ");

            switch (userInput.next()) {
                //List doses
                case "1":
                    clear();
                    action.printDoses();
                    pause();
                    clear();
                    break;

                //Add a dose
                case "2":
                    action.addDose(createDose());
                    clear();
                    break;

                //Remove a dose
                case "3":
                    removeDose();
                    pause();
                    clear();
                    break;

                //Remove all doses
                case "4":
                    action.removeAllDoses();
                    clear();
                    break;

                //Display concentration amount at current time
                case "5":
                    clear();
                    action.printCurrentConcentration(LocalTime.now());
                    pause();
                    clear();
                    break;

                //Display concentration amount at specified time
                case "6":
                    clear();
                    action.printCurrentConcentration(createLocalTime());
                    pause();
                    clear();
                    break;

                //Save Medicine to a file and closes the program
                case "7":
                    System.out.print("Enter filename: ");
                    action.saveFile(userInput.next());
                    System.exit(0);
                    break;

                //Advanced features
                //Add a test dose
                case "8":
                    d = createDose();
                    d.setTestDose();
                    action.addDose(d);
                    clear();
                    break;

                //Display peak level of doses. It can include test doses if desired.
                case "9":
                    System.out.println("1. Include test doses");
                    System.out.println("2. Do NOT include test doses");
                    System.out.print("Enter a number: ");

                    switch (userInput.next()) {
                        case "1":
                            clear();
                            action.printPeakConcentrationTime(true);
                            pause();
                            clear();
                            break;
                        case "2":
                            clear();
                            action.printPeakConcentrationTime(false);
                            pause();
                            clear();
                            break;
                        default:
                            System.out.println("Invalid user input");
                            pause();
                            clear();
                            break;
                    }
                    break;

                //Display when to dose
                case "10":
                    System.out.print("Enter dose amount: ");
                    checkIfValidNumber();

                    clear();
                    action.printWhenToDose(userInput.nextDouble());
                    pause();
                    clear();
                    break;

                //Remove all test doses
                case "11":
                    action.removeTestDoses();
                    clear();
                    break;

                //Closes the application
                case "exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid user input");
                    pause();
                    clear();
                    //System.exit(0);
                    //return;
                    break;
            }
        } while (true);
    }

    public static void main(String[] args) {
        start();
        selectAction();
    }
}