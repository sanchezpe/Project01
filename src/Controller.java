import java.time.LocalTime;
import java.util.Scanner;

//User Interface
public class Controller implements IController {

    static Scanner userInput = new Scanner(System.in);
    static Action action = new Action();

    static LocalTime createLocalTimeFromUserInput(int hour, int minute) {
        //checks if hour and minute is within valid range
        if (hour >= 0 && hour < 24 && minute >= 0 && minute < 60) {
            return LocalTime.of(hour, minute);
        }

        //if range is incorrect, use local time instead
        return LocalTime.now();
    }

    static LocalTime createLocalTimeFromUserInput() {
        System.out.print("Enter hour: ");
        int hour = userInput.nextInt();

        System.out.print("Enter minute: ");
        int minute = userInput.nextInt();

        //checks if hour and minute is within valid range
        if (hour >= 0 && hour < 24 && minute >= 0 && minute < 60) {
            return LocalTime.of(hour, minute);
        }

        //if range is incorrect, use local time instead
        return LocalTime.now();
    }

    static IDose createDoseFromUserInput(int hour, int minute, double amount) {
        /*System.out.print("Enter a dose hour: ");
        int doseHour = userInput.nextInt();

        System.out.print("Enter a dose minute: ");
        int doseMinute = userInput.nextInt();

        System.out.print("Enter a dose amount: ");
        double doseAmount = userInput.nextDouble();*/

        if (amount > 0) {
            return new Dose(LocalTime.of(hour, minute), amount);
        }

        return new Dose(LocalTime.of(hour, minute), 1);

    }

    static IDose createDoseFromUserInput() {
        System.out.print("Enter dose hour: ");
        int hour = userInput.nextInt();

        System.out.print("Enter dose minute: ");
        int minute = userInput.nextInt();

        System.out.print("Enter dose amount: ");
        double amount = userInput.nextDouble();

        if (amount > 0) {
            return new Dose(LocalTime.of(hour, minute), amount);
        }

        return new Dose(LocalTime.of(hour, minute), 1);
    }

    static void start() {
        System.out.println("Welcome! \n");
        System.out.println("What do you want to do?");
        System.out.println("1. Create new file");
        System.out.println("2. Load existing file");
        System.out.print("Enter a number: ");

        switch (userInput.next()) {
            case "1":
                System.out.print("Enter medicine name: ");
                String name = userInput.next();

                System.out.print("Enter TMax hours: ");
                int tmaxHour = userInput.nextInt();
                System.out.print("Enter TMax minutes: ");
                int tmaxMinute = userInput.nextInt();
                //LocalTime tMax = LocalTime.of(tmaxHour, tmaxMinute);

                System.out.print("Enter half life hours: ");
                int halfLifeHour = userInput.nextInt();
                System.out.print("Enter half life minutes: ");
                int halfLifeMinute = userInput.nextInt();
                //LocalTime halfLife = LocalTime.of(halfLifeHour, halfLifeMinute);
                action.newFile(name,
                        createLocalTimeFromUserInput(tmaxHour, tmaxMinute),
                        createLocalTimeFromUserInput(halfLifeHour, halfLifeMinute));
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

    static void selectAction() {
        do {
            /*int doseHour;
            int doseMinute;
            Double doseAmount;*/
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
            System.out.println("8. Add test dose");
            //System.out.println(. Remove all test doses");
            System.out.println("9. Highest peak");
            System.out.println("10. When to dose");
            System.out.print("Enter a number: ");

            switch (userInput.next()) {
                case "1":
                    clear();
                    action.printDoses();
                    pause();
                    clear();
                    break;
                case "2":
                    action.addDose(createDoseFromUserInput());
                    break;
                case "3":
                    System.out.print("Enter dose index");
                    if (userInput.nextInt() < action.getMedicine().getDoses().size()) {
                        action.removeDose(userInput.nextInt());
                        clear();
                    }
                    System.out.println("Invalid index");
                    pause();
                    clear();
                    break;
                case "4":
                    action.removeAllDoses();
                    break;
                case "5":
                    clear();
                    //action.printCurrentConcentration(action.getCurrentConcentration(LocalTime.now()));
                    action.printCurrentConcentration(LocalTime.now());
                    pause();
                    clear();
                    break;
                case "6":
                    clear();
                    //action.printCurrentConcentration(action.getCurrentConcentration(createLocalTimeFromUserInput()));
                    action.printCurrentConcentration(createLocalTimeFromUserInput());
                    pause();
                    clear();
                    break;
                case "7":
                    System.out.print("Enter filename: ");
                    action.saveFile(userInput.next());
                    System.exit(0);
                    break;

                //Advanced features
                case "8":
                    d = createDoseFromUserInput();
                    d.setTestDose();
                    action.addDose(d);
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid user input");
                    System.exit(0);
                    break;
            }
        } while (true);
    }

    static void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    static void pause() {
        System.out.println("Press \"ENTER\" to continue...");
        userInput.nextLine();
        userInput.nextLine();
    }

    public static void main(String[] args) {
        start();
        selectAction();
    }
}