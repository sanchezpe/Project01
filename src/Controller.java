import java.time.LocalTime;
import java.util.Scanner;

//User Interface
public class Controller implements IController {

    static Scanner userInput = new Scanner(System.in);
    static Action action = new Action();

    static IDose createDoseFromUserInput() {
        System.out.print("Enter a dose hour: ");
        int doseHour = userInput.nextInt();

        System.out.print("Enter a dose minute: ");
        int doseMinute = userInput.nextInt();

        System.out.print("Enter a dose amount: ");
        double doseAmount = userInput.nextDouble();

        return new Dose(LocalTime.of(doseHour, doseMinute), doseAmount);

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
                LocalTime tMax = LocalTime.of(tmaxHour, tmaxMinute);

                System.out.print("Enter half life hours: ");
                int halfLifeHour = userInput.nextInt();
                System.out.print("Enter half life minutes: ");
                int halfLifeMinute = userInput.nextInt();
                LocalTime halfLife = LocalTime.of(halfLifeHour, halfLifeMinute);
                action.newFile(name, tMax, halfLife);
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
        Integer doseHour;
        Integer doseMinute;
        //Double doseAmount;
        IDose d;

        do {
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
                    action.printDoses();
                    break;
                case "2":
                    action.addDose(createDoseFromUserInput());
                    break;
                case "3":
                    action.removeDose(createDoseFromUserInput());
                    break;
                case "4":
                    action.removeAllDoses();
                    break;
                case "5":
                    action.currentConcentration(LocalTime.now());
                    break;
                case "6":
                    System.out.print("Enter a dose hour: ");
                    doseHour = userInput.nextInt();
                    userInput.nextLine();

                    System.out.print("Enter a dose minute: ");
                    doseMinute = userInput.nextInt();
                    userInput.nextLine();

                    action.currentConcentration(LocalTime.of(doseHour, doseMinute));
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

    public static void main(String[] args) {
        start();
        selectAction();
    }
}