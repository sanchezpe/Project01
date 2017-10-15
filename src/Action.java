import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class Action implements IAction {
    private IMedicine medicine = new Medicine();

    public Action() {
    }

    @Override
    public void printDoses() {
        for (IDose dose : medicine.getDoses()) {
            //Dose implements toString method
            System.out.println(medicine.getDoses().indexOf(dose) + "  " + dose);
            //System.out.print(dose + "\n");
        }
    }

    @Override
    public void addDose(IDose dose) {
        medicine.getDoses().add(dose);
    }

    @Override
    public void removeDose(IDose dose) {
        medicine.getDoses().remove(dose);
    }

    public void removeDose(int index) {
        medicine.getDoses().remove(index);
    }

    @Override
    public void removeAllDoses() {
        medicine.getDoses().clear();
    }

    @Override
    public ArrayList<Double> getCurrentConcentration(LocalTime time) {
        ArrayList<Double> currentConcentrations = new ArrayList<>();

        for (IDose dose : medicine.getDoses()) {
            //Formula to determine concentration at specified time
            double concentration = dose.getAmount() * Math.pow(.5, ((double) time.toSecondOfDay() / medicine.getHalfLife().toSecondOfDay()));
            currentConcentrations.add(concentration);
        }
        return currentConcentrations;
    }

    @Override
    public void printCurrentConcentration(ArrayList<Double> concentrations) {
        for (int i = 0; i < medicine.getDoses().size(); i++) {
            System.out.println(medicine.getDoses().get(i) + "   Concentration: " + concentrations.get(i));
        }
    }

    @Override
    public void printCurrentConcentration(LocalTime time) {
        for (int i = 0; i < getCurrentConcentration(time).size(); i++) {
            System.out.println(medicine.getDoses().get(i) + "   Concentration: " + getCurrentConcentration(time).get(i));
        }
    }

    @Override
    public ArrayList<Double> getPeakConcentration(Boolean useTests) {
        return null;
    }

    @Override
    public void printPeakConcentration(Boolean useTests) {

    }

    @Override
    public ArrayList<Double> whenToDose(Double amountDesired) {
        return null;
    }

    @Override
    public void saveFile(String filename) {
        //Create ObjectOutputStream
        ObjectOutputStream oos = null;

        try {
            //Create a FileOutputStream with name filename
            //The file will be stored on the current directory of the application
            oos = new ObjectOutputStream(new FileOutputStream(filename));

            //Save Medicine instance to filename file
            oos.writeObject(medicine);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadFile(String filename) {
        //Creates ObjectInputStream
        ObjectInputStream ois = null;

        try {
            //Create a ObjectInputStream from file filename
            ois = new ObjectInputStream(new FileInputStream(filename));

            //loads Medicine from filename file to local variable
            this.medicine = (Medicine) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void newFile(String name, LocalTime tMax, LocalTime halfLife) {
        medicine = new Medicine(name, tMax, halfLife);
    }


    @Override
    public IMedicine getMedicine() {
        return this.medicine;
    }

    @Override
    public void printMedicine() {
        System.out.println(getMedicine());
    }

    /*//Advanced
    public void removeTestDoses() {
        //easiest way to remove array elements
        medicine.getDoses().removeIf(IDose::isTestDose);
    }*/
}
