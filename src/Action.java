import java.io.*;
import java.time.LocalTime;

public class Action implements IAction {
    private IMedicine medicine = new Medicine();

    public Action() {
    }

    @Override
    public void printDoses() {
        for (IDose dose : medicine.getDoses()) {
            System.out.print(dose + "\n");
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

    @Override
    public void removeAllDoses() {
        medicine.getDoses().clear();
    }

    @Override
    public void currentConcentration(LocalTime time) {
        //ArrayList<Double> currentConcentrations = new ArrayList<>();

        for (IDose dose : medicine.getDoses()) {
            double concentration = dose.getAmount() * Math.pow(.5, ((double) time.toSecondOfDay() / medicine.getHalfLife().toSecondOfDay()));
            //currentConcentrations.add(concentration);
            System.out.println(concentration);
        }
        //return currentConcentrations;
    }

    public void saveFile(String filename) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(medicine);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFile(String filename) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(filename));
            this.medicine = (Medicine) ois.readObject();
            //System.out.println("The file has been loaded successfully");
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void newFile(String name, LocalTime tMax, LocalTime halfLife) {
        medicine.createMedicine(name, tMax, halfLife);
    }

    public void printMedicine() {
        System.out.println(medicine.toString());
    }

    //Advanced
    public void removeTestDoses() {
        //easiest way to remove array elements
        medicine.getDoses().removeIf(IDose::isTestDose);
    }

}
