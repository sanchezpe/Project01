import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Scanner;

public class Controller implements IController {
    @Override
    public void printAllDoses() {

    }

    @Override
    public void printSpecificDose(IDose dose) {

    }

    @Override
    public IDose getDoseBasedOnTime(int timeInSeconds) {
        return null;
    }

    @Override
    public IAction whatTheHellDoesTheUserWant(String userAnnoyance) {
        return null;
    }

    private static void newFile(Scanner userInput) {
        Medicine medicine = new Medicine();


        System.out.print("Enter medicine name: ");
        String name = userInput.next();


        System.out.print("Enter tmax: ");
        Double tmax = userInput.nextDouble();


        System.out.print("Enter half life: ");
        Double halfLife = userInput.nextDouble();


        medicine.createMedicine(name, tmax, halfLife);


    }

    private static void openFile(String filename) {

    }

    public void saveToFile(Medicine medicine) {

        Date date = new Date();

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(".\\medicine" + date.toString()))) {

            oos.writeObject(medicine);
            System.out.println("File saved successfully!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void start(Scanner userInput) {
        System.out.println("What do you wish to do?");
        System.out.println("New file (n)");
        System.out.println("Open file (o)");
        String response = userInput.next();

        if (response == "n") {
            newFile();
        } else if (response == "o") {
            System.out.println("Enter file name:");
            String filename = userInput.next();
            openFile(filename);
        } else {
            System.out.println("Invalid user input");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);


    }
}
}
