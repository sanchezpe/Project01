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
            //N(t)=N(o)*(1/2)^(time/halfLife)
            double concentration = dose.getAmount() *
                    Math.pow(.5, ((double) time.toSecondOfDay() / medicine.getHalfLife().toSecondOfDay()));
            currentConcentrations.add(concentration);
        }
        return currentConcentrations;
    }

    @Override
    public void printCurrentConcentration(LocalTime time) {
        for (int i = 0; i < getCurrentConcentration(time).size(); i++) {
            System.out.println(medicine.getDoses().get(i) + "   Concentration at " + time
                    + " is " + getCurrentConcentration(time).get(i));
        }
    }

    @Override
    public ArrayList<LocalTime> getPeakConcentrationTime(Boolean includeTestDoses) {
        ArrayList<LocalTime> peakConcentrations = new ArrayList<>();
        //check if test doses are omitted
        if (!includeTestDoses) {
            for (IDose dose : medicine.getDoses()) {
                //check whether dose is a test dose
                if (!dose.isTestDose()) {
                    //formula
                    //peak=doseTakeTime+medicinePeakTime
                    LocalTime peakConcentration = dose.getTimeTake().plusSeconds(medicine.getTmax().toSecondOfDay());
                    peakConcentrations.add(peakConcentration);
                }
            }
            return peakConcentrations;
        }
        for (IDose dose : medicine.getDoses()) {
            LocalTime peakConcentration = dose.getTimeTake().plusSeconds(medicine.getTmax().toSecondOfDay());
            peakConcentrations.add(peakConcentration);
        }
        return peakConcentrations;
    }

    @Override
    public void printPeakConcentrationTime(Boolean includeTestDoses) {
        for (int i = 0; i < getPeakConcentrationTime(includeTestDoses).size(); i++) {
            System.out.println(medicine.getDoses().get(i) + "   Peak Concentration: "
                    + getPeakConcentrationTime(includeTestDoses).get(i));
        }
    }

    @Override
    public ArrayList<LocalTime> getWhenToDose(Double concentrationDesired) {
        ArrayList<LocalTime> timesToDose = new ArrayList<>();
        for (IDose dose : medicine.getDoses()) {
            //formula
            // t=(halfLife*ln(n(t)/n(o)))/-ln2
            double timeinSeconds = Math.abs((medicine.getHalfLife().toSecondOfDay()
                    * Math.log((concentrationDesired / dose.getAmount()))) / -Math.log(2));

            //convert double to long to parse value to LocalTime.of()
            long timeInSecondsLong = Double.doubleToLongBits(timeinSeconds);

            //LocalTime supports long value up to 86399 (number of seconds in one day)
            //if timeInSecondsLong > 86400, keep dividing by 86400
            while (timeInSecondsLong > 86400) {
                timeInSecondsLong = timeInSecondsLong / 86400;
            }
            timesToDose.add(LocalTime.ofSecondOfDay(timeInSecondsLong));
        }
        return timesToDose;
    }

    @Override
    public void printWhenToDose(Double amountDesired) {
        for (int i = 0; i < getWhenToDose(amountDesired).size(); i++) {
            System.out.println(medicine.getDoses().get(i) + "    Dose at: " + getWhenToDose(amountDesired).get(i));
        }
    }

    @Override
    public void saveFile(String filename) {
        //Create ObjectOutputStream
        ObjectOutputStream oos;

        try {
            //Create a FileOutputStream with name filename
            //The file will be stored on the current directory of the application
            oos = new ObjectOutputStream(new FileOutputStream(filename));

            //Save Medicine instance to filename file
            oos.writeObject(medicine);
            oos.close();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Invalid filename");
            System.exit(0);
        }
    }

    @Override
    public void loadFile(String filename) {
        //Creates ObjectInputStream
        ObjectInputStream ois;

        try {
            //Create a ObjectInputStream from file filename
            ois = new ObjectInputStream(new FileInputStream(filename));

            //loads Medicine from filename file to local variable
            this.medicine = (Medicine) ois.readObject();
            ois.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Invalid filename");
            System.exit(0);
        }
    }

    @Override
    public void newFile(String name, LocalTime tMax, LocalTime halfLife) {
        medicine = new Medicine(name, tMax, halfLife);
    }

    @Override
    public void printMedicine() {
        System.out.println(medicine);
    }

    @Override
    public void removeTestDoses() {
        //easiest way to remove array elements
        medicine.getDoses().removeIf(IDose::isTestDose);
    }

    @Override
    public IMedicine getMedicine() {
        return medicine;
    }
}
