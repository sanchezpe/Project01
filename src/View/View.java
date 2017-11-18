package View;

import Controller.Controller;
import Model.IDose;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

//User Interface
public class View {

    /**
     * Read from user input
     */
    public static Scanner userInput = new Scanner(System.in);
    private static Controller controller = new Controller();

    /**
     * Prints Dose index, time taken, and quantity.
     */
    private static void printDoses() {
        for (IDose dose : controller.getMedicine().getDoses()) {
            //Dose implements toString method
            System.out.println(controller.getMedicine().getDoses().indexOf(dose) + "  " + dose);
            //System.out.println(getPrintSingleDose(dose));
            //System.out.print(dose + "\n");
        }
    }

    /**
     * Prints Medicine name, time max, and half life.
     */
    private static void printMedicine() {
        System.out.println(controller.getMedicine());
    }

    /**
     * Prints concentration amount at specific time.
     *
     * @param dateTime Specific time to calculate concentration amount.
     */
    private static void printConcentrationAtTime(LocalDateTime dateTime) {
        for (IDose dose : controller.getMedicine().getDoses()) {
            System.out.println(dose + " is " + controller.getConcentrationAtTime(dose, dateTime));
        }
        System.out.println("Total concentration amount at " + dateTime + " is " +
                controller.getSumConcentrationsAtTime(dateTime));
    }

    /**
     * Creates LocalDate from user input.
     *
     * @return LocalDate instance.
     */
    private static LocalDate createLocalDate() {
        System.out.print("Enter a date with format 2007-12-03 ");
        String date = userInput.nextLine();
        return controller.createLocalDate(date);
    }

    /**
     * Creates LocalTime instance from user input.
     *
     * @return Localtime instance.
     */
    private static LocalTime createLocalTime() {
        System.out.print("Enter a time with format 10:15:30 ");
        String time = userInput.nextLine();
        return controller.createLocalTime(time);
    }

    /**
     * Creates a Medicine instance from user input.
     */
    private static void createNewFile() {
        System.out.print("Enter medicine name: ");
        String name = userInput.nextLine();

        System.out.println("Enter medicine TMax");

        LocalTime tMax = createLocalTime();

        System.out.println("Enter medicine half life");
        LocalTime halfLife = createLocalTime();

        controller.newFile(name, tMax, halfLife);
    }

    /**
     * Creates Dose from user input.
     *
     * @param isTest Defines whether is a test dose.
     */
    private static void addDose(boolean isTest) {
        System.out.println("Enter date dose is taken");
        LocalDate date = createLocalDate();

        System.out.println("Enter time dose is taken");
        LocalTime time = createLocalTime();

        System.out.print("Enter dose amount: ");
        double amount = Controller.parseValidAmount();
        userInput.nextLine();
        controller.addDose(LocalDateTime.of(date, time), amount, isTest);
    }

    /**
     * Removes dose using an index selected by the user.
     */
    private static void removeDose() {
        System.out.print("Enter dose index: ");
        int index = Controller.parseValidInt();
        userInput.nextLine();
        controller.removeDose(index);
    }


    /**
     * Prints peak level of concentration. The output can be test Doses and actual Doses, or actual Doses only.
     *
     * @param includeTestDoses Define whether to consider test doses.
     */
    private static void printPeakLevelAt(Boolean includeTestDoses) {
        for (IDose dose : controller.getMedicine().getDoses()) {
            if (!includeTestDoses) {
                if (!dose.getIsTestDose()) {
                    System.out.println(dose + " Peak level will occur at " + controller.getPeakLevel(dose));
                }
            } else {
                System.out.println(dose + " Peak level will occur at " + controller.getPeakLevel(dose));
            }
        }
    }

    /**
     * Prints time when to dose to obtain specified dose amount.
     *
     * @param amountDose Dose amount.
     */
    private static void printWhenToDose(double amountDose) {
        for (IDose dose : controller.getMedicine().getDoses()) {
            if (!dose.getIsTestDose()) {
                System.out.println(dose + " Dose amount " + amountDose + " will be at " + controller.getWhenToDose(dose, amountDose));
            }
        }
    }

    /**
     * Clears the console screen.
     */
    private static void clear() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    /**
     * Pause scrolling fot the console screen until user hits the Enter key.
     */
    public static void pause() {
        System.out.println("Press \"ENTER\" to continue...");
        userInput.nextLine();
        //userInput.nextLine();
    }


    /**
     * Display a welcome screen which allows the user to select whether to create a file or open an existing one.
     */
    private static void start() {
        System.out.println("Welcome! \n");
        System.out.println("What do you want to do?");
        System.out.println("1. Create new file");
        System.out.println("2. Load existing file");
        System.out.print("Enter a number: ");

        switch (userInput.nextLine()) {
            case "1":
                createNewFile();
                clear();
                break;
            case "2":
                System.out.print("Enter filename: ");
                controller.loadFile(userInput.nextLine());
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
     * The user must enter the corresponding number to execute the controller.
     */
    private static void selectAction() {
        //Executes this screen until the user exit the
        do {
            printMedicine();
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

            switch (userInput.nextLine()) {
                //List doses
                case "1":
                    clear();
                    printDoses();
                    pause();
                    clear();
                    break;

                //Add a dose
                case "2":
                    addDose(false);
                    clear();
                    break;

                //Remove a dose
                case "3":
                    removeDose();
                    //pause();
                    clear();
                    break;

                //Remove all doses
                case "4":
                    controller.removeAllDoses();
                    clear();
                    break;

                //Display concentration amount at current time
                case "5":
                    clear();
                    printConcentrationAtTime(LocalDateTime.now());
                    pause();
                    clear();
                    break;

                //Display concentration amount at specified time
                case "6":
                    //clear();
                    printConcentrationAtTime(LocalDateTime.of(createLocalDate(), createLocalTime()));
                    pause();
                    clear();
                    break;

                //Save Medicine to a file and closes the program
                case "7":
                    System.out.print("Enter filename: ");
                    controller.saveFile(userInput.nextLine());
                    System.exit(0);
                    break;

                //Advanced features
                //Add a test dose
                case "8":
                    addDose(true);
                    clear();
                    break;

                //Display peak level of doses. It can include test doses if desired.
                case "9":
                    System.out.println("1. Include test doses");
                    System.out.println("2. Do NOT include test doses");
                    System.out.print("Enter a number: ");

                    switch (userInput.nextLine()) {
                        case "1":
                            clear();
                            printPeakLevelAt(true);
                            pause();
                            clear();
                            break;
                        case "2":
                            clear();
                            printPeakLevelAt(false);
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
                    //necessary to print nicely
                    double dummyInput = Controller.parseValidAmount();
                    userInput.nextLine();
                    clear();
                    printWhenToDose(dummyInput);
                    pause();
                    clear();
                    break;

                //Remove all test doses
                case "11":
                    controller.removeTestDoses();
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