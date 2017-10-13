import java.io.*;
import java.time.LocalTime;
import java.util.Scanner;

//User Interface
public class Controller implements IController {

    static Medicine medicine = new Medicine();
    static Scanner userInput = new Scanner(System.in);

    @Override
    public void printAllDoses() {
        medicine.toString();
        medicine.getDoses().toString();
    }

    /*@Override
    public void printSpecificDose(IDose dose) {

    }*/

    @Override
    public IDose getDoseBasedOnTime(int timeInSeconds) {
        return null;
    }

    /*@Override
    public IAction whatTheHellDoesTheUserWant(String userAnnoyance) {
        return null;
    }*/

    void start() {

        System.out.println("1. New file");
        System.out.println("2. Open file");
        System.out.print("What do you want to do? Enter a number ");
        String response = userInput.next();

        if (response == "1") {
            newFile();
        } else if (response == "2") {
            System.out.println("Enter file name:");
            String filename = userInput.next();
            loadFile(filename);
        } else {
            System.out.println("Invalid user input");
            System.exit(0);
        }
    }

    static void newFile() {

        System.out.print("Enter medicine name: ");
        String name = userInput.next();


        System.out.print("Enter TMax hour: ");
        int tmaxHour = userInput.nextInt();

        System.out.print("Enter TMax hour: ");
        int tmaxMinute = userInput.nextInt();

        LocalTime tmax = LocalTime.of(tmaxHour, tmaxMinute);


        System.out.print("Enter half life: ");
        Double halfLife = userInput.nextDouble();


        medicine.createMedicine(name, tmax, halfLife);
    }

    void saveFile(String filename) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(".\\" + filename));
            oos.writeObject(medicine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void loadFile(String filename) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(".\\" + filename));
            medicine = (Medicine) ois.readObject();
            System.out.println("The file has been load successfully");
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void actions(){
        System.out.println("What do you want to do?");
        System.out.println("1. List all doses");
        System.out.println("2. Remove a dose");
        System.out.println("3. Clear all doses");
        System.out.println("4. Display current concentration amount");
        System.out.println("5. Display concentration amount at a specific time");
        System.out.println("6. Save file and exit");
        System.out.println("7. Add test dose");
        System.out.println("8. Remove all test doses");
        System.out.println("9. Advanced");
        System.out.println("10. Advanced");
    }


    public static void main(String[] args) {
        System.out.print("Hey!");
    }
}
