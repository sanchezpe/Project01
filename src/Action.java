import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Action implements IAction {
    /**
     * Medicine file
     */
    private IMedicine medicine = new Medicine();

    public Action() {
    }

    public Action(IMedicine medicine) {
        this.medicine = medicine;
    }

    @Override
    public IMedicine getMedicine() {
        return medicine;
    }

    @Override
    public void printMedicine() {
        System.out.println(medicine);
    }

    //Basic Features
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
        medicine.addDose(dose);
    }

    @Override
    public void removeDose(int index) {
        medicine.removeDose(index);
    }

    @Override
    public void removeAllDoses() {
        medicine.removeAllDoses();
    }

    @Override
    public Double getConcentrationAtTime(LocalDateTime localDateTime) {
        return medicine.getConcentrationsAtTime(localDateTime);
    }

    public void printConcentrationAtTime(LocalDateTime dateTime) {
        System.out.println("Total concentration amount at " + dateTime + " is " + getConcentrationAtTime(dateTime));
    }

    public void printDetailedConcentrationAtTime(LocalDateTime dateTime) {
        for (IDose d : medicine.getDoses()) {
            System.out.println(d + "   Concentration at " + dateTime + " is " +
                    d.getConcentrationAtTime(dateTime, medicine.getTimeMaxMedicine(), medicine.getTimeHalfLifeMedicine()));
        }
        System.out.print("Total concentration amount at " + dateTime + " is " + getConcentrationAtTime(dateTime));
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
    public void newFile(String nameMedicine, LocalTime timeMaxMedicine, LocalTime timeHalfLifeMedicine) {
        medicine = new Medicine(nameMedicine, timeMaxMedicine, timeHalfLifeMedicine);
    }


    //Advanced Features
    @Override
    public void removeTestDoses() {
        medicine.removeTestDoses();
    }

    /*public ArrayList<LocalTime> getPeakConcentrationTime(Boolean includeTestDoses) {
        ArrayList<LocalTime> peakConcentrations = new ArrayList<>();
        //check if test doses are omitted
        if (!includeTestDoses) {
            for (IDose dose : medicine.getDoses()) {
                //check whether dose is a test dose
                if (!dose.getIsTestDose()) {
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

    public void printPeakConcentrationTime(Boolean includeTestDoses) {
        for (int i = 0; i < getPeakConcentrationTime(includeTestDoses).size(); i++) {
            System.out.println(medicine.getDoses().get(i) + "   Peak Concentration: "
                    + getPeakConcentrationTime(includeTestDoses).get(i));
        }
    }

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

    public void printWhenToDose(Double amountDesired) {
        for (int i = 0; i < getWhenToDose(amountDesired).size(); i++) {
            System.out.println(medicine.getDoses().get(i) + "    Dose at: " + getWhenToDose(amountDesired).get(i));
        }
    }*/

}
