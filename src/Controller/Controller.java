package Controller;

import Model.IDose;
import Model.IMedicine;
import Model.Medicine;
import View.View;

import java.io.*;
import java.time.*;

public class Controller implements IController {
    /**
     * Defines time zone.
     * Default: -05:00
     */
    private ZoneOffset timezone = ZoneOffset.of("-05:00");

    /**
     * Medicine file
     */
    private IMedicine medicine = new Medicine();
    //private ArrayList<IDose> doses = new ArrayList<>();

    @Override
    public IMedicine getMedicine() {
        return medicine;
    }


    //Basic Features
    @Override
    public void addDose(LocalDateTime dateTimeTakeDose, double amount, boolean isTest) {
        //Dose amount must be a positive number
        if (amount > 0) {
            medicine.addDose(dateTimeTakeDose, amount, isTest);
        }
        //default
        else {
            medicine.addDose(LocalDateTime.now(), 1.0, false);
        }
    }

    @Override
    public void removeDose(int index) {
        if (index >= 0 && index < getMedicine().getDoses().size()) {
            medicine.removeDose(index);
        } else {
            System.out.println("Invalid index");
            View.pause();
        }
    }

    @Override
    public void removeAllDoses() {
        medicine.removeAllDoses();
    }


    @Override
    public Double getConcentrationAtTime(IDose doseIn, LocalDateTime dateTimeAtIn) {

        //Converts all LocalDateTime and LocalTime instances in seconds
        Long dateTimeAt = dateTimeAtIn.toEpochSecond(timezone);
        Long dateTimeTaken = doseIn.getDateTimeTakeDose().toEpochSecond(timezone);
        //timeMax is an interval
        Integer timeMax = medicine.getTimeMaxMedicine().toSecondOfDay();
        //DateTimeMax is an actual time
        Long dateTimeMax = timeMax + dateTimeTaken;
        Integer timeHalfLife = medicine.getTimeHalfLifeMedicine().toSecondOfDay();

        Double amountDose = doseIn.getAmountDose();

        Double concentrationAtTime;

        //(inputTime > dateTimeTaken) and (inputTime <= timeMax)
        if (dateTimeAt.compareTo(dateTimeTaken) > 0 && dateTimeAt.compareTo(dateTimeMax) <= 0) {
            concentrationAtTime = (amountDose * (dateTimeAt - dateTimeTaken)) / timeMax;
        }
        //inputTime > timeMax
        else if (dateTimeAt.compareTo(dateTimeMax) > 0) {
            concentrationAtTime = amountDose * Math.pow(.5, ((dateTimeAt - dateTimeMax) / timeHalfLife));
        }
        //invalid times return concentration of 0
        else {
            concentrationAtTime = 0.0;
        }
        return concentrationAtTime;
    }

    @Override
    public Double getSumConcentrationsAtTime(LocalDateTime dateTime) {
        Double concentrationsAtTime = 0.0;

        for (IDose d : medicine.getDoses()) {
            concentrationsAtTime += getConcentrationAtTime(d, dateTime);
        }
        return concentrationsAtTime;
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
            this.medicine = (IMedicine) ois.readObject();
            ois.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Invalid file");
            System.exit(0);
        }
    }

    @Override
    public void newFile(String nameMedicine, LocalTime timeMaxMedicine, LocalTime timeHalfLifeMedicine) {
        medicine.createMedicine(nameMedicine, timeMaxMedicine, timeHalfLifeMedicine);
    }


    @Override
    public LocalDate createLocalDate(String date) {
        /*
         LocalDate:
            Group 1 - year: match 4 digits.

            Group 2 - month: match one 0 followed by one digit from 1-9 OR
                            match one 1 followed by one digit 0-2

            Group 3 -  day: match one 0 followed by one digit from 1-9 OR
                            match one 1,2 followed by one digit
                            match one 3 followed by one digit from 0-1
         */
        if (date.matches("^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$")) {
            return LocalDate.parse(date);
        } else {
            System.out.println("Invalid format. Using current date instead.");
            View.pause();
            return LocalDate.now();
        }
    }

    @Override
    public LocalTime createLocalTime(String time) {

        /*
         LocalTime:
            Group 1 - hour: match one 0-1 followed by one digit OR
                            match one 2 followed by one digit from 0-3

            Group 2 - min:  match one 0-5 followed by one digit

            Group 3 - second: (optional) match one 0-5 followed by one digit.
         */
        if (time.matches("^([01]\\d|2[0-3]):([0-5]\\d)(:[0-5]\\d)?$")) {
            return LocalTime.parse(time);
        } else {
            System.out.println("Invalid format. Using current time instead.");
            View.pause();
            return LocalTime.now();
        }
    }

    /**
     * Verifies if parsed input is a valid number amount.
     *
     * @return valid amount.
     */
    public static Double parseValidAmount() {
        if (View.userInput.hasNextDouble() || View.userInput.hasNextInt()) {
            return View.userInput.nextDouble();
        } else {
            System.out.println("Invalid amount. Using dose amount of 1.0 instead.");
            View.pause();
            return 1.0;
        }
    }

    /**
     * Verifies if parsed input is a valid integer.
     *
     * @return valid integer.
     */
    public static Integer parseValidInt() {
        if (View.userInput.hasNextInt()) {
            return View.userInput.nextInt();
        } else {
            return -1;
        }
    }

    //Advanced Features
    @Override
    public void removeTestDoses() {
        medicine.removeTestDoses();
    }

    @Override
    public LocalDateTime getPeakLevel(IDose dose) {
        //Extract hours
        Integer hours = medicine.getTimeMaxMedicine().getHour();

        //Extract seconds
        Integer minutes = medicine.getTimeMaxMedicine().getMinute();

        return dose.getDateTimeTakeDose().plusHours(hours).plusMinutes(minutes);
    }

    @Override
    public LocalDateTime getWhenToDose(IDose dose, double amountDoseAt) {
        //Time when dose is taken
        LocalDateTime dateTimeTaken = dose.getDateTimeTakeDose();

        //Medicine timeMax in seconds. timeMax is an interval.
        Integer timeMax = medicine.getTimeMaxMedicine().toSecondOfDay();

        //Medicine timeMax. Actual time max (date and time).
        LocalDateTime dateTimeMax = dateTimeTaken.plusSeconds(timeMax);

        //Medicine half life time in seconds
        Integer timeHalfLife = medicine.getTimeHalfLifeMedicine().toSecondOfDay();

        //Dose original amount.
        Double amountDose = dose.getAmountDose();

        //input dose must be less than original dose
        if (amountDoseAt < dose.getAmountDose()) {
            //formula
            Double whenToDose = (timeHalfLife * Math.log((amountDoseAt / amountDose))) / (-Math.log(2));

            /*
            *whenToDose is time interval in seconds.
            *To obtain actual time whenToDose, whenToDose must be added to dateTimeMax;
            */
            return dateTimeMax.plusSeconds(Math.round(whenToDose));
        } else {
            return null;
        }
    }
}