import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

//Serializable eases the process of saving file
public class Dose implements Serializable, IDose {
    /**
     * Keep time when dose is taken.
     * Default: current time.
     */
    private LocalDateTime dateTimeTakeDose = LocalDateTime.now();

    /**
     * Amount of dose.
     * Default: 1.
     */
    private Double amountDose = 1.0;

    /**
     * Defines whether dose is a test dose
     * Default: false.
     */
    //Advanced Feature
    private Boolean isTestDose = false;

    /**
     * Defines time zone.
     * Default: -05:00
     */
    private ZoneOffset timezone = ZoneOffset.of("-05:00");

    //default constructor
    public Dose() {
    }

    //parameterized constructor
    public Dose(LocalDateTime timeTake, double amountDose) {
        createDose(timeTake, amountDose);
    }

    public Dose(LocalDateTime timeTake, double amountDose, boolean isTestDose) {
        createDose(timeTake, amountDose);
        this.isTestDose = isTestDose;
    }

    @Override
    public void createDose(LocalDateTime dateTimeTakeDose, double amount) {

        //Dose amount must be a positive number
        if (amount > 0) {
            this.amountDose = amount;
        }
        this.dateTimeTakeDose = dateTimeTakeDose;
        this.isTestDose = false;
    }

    @Override
    public Double getAmountDose() {
        return amountDose;
    }

    @Override
    public LocalDateTime getDateTimeTakeDose() {
        return dateTimeTakeDose;
    }


    @Override
    public Double getConcentrationAtTime(LocalDateTime dateTimeAtIn, LocalTime timeMaxIn, LocalTime timeHalfLifeIn) {
        Double concentrationAtTimeN;

        //Convert times to seconds. Used for calculations
        Long dateTimeTake = dateTimeTakeDose.toEpochSecond(timezone);
        Long dateTimeAt = dateTimeAtIn.toEpochSecond(timezone);
        Long dateTimeMax = dateTimeTake + timeMaxIn.toSecondOfDay();
        int timeHalfLife = timeHalfLifeIn.toSecondOfDay();

        //dateTimeAt = dateTimeTake.
        //When the user just takes the dose, the concentration amount is 0.
        // The rise of dose concentration starts after timeTake
        /*if (dateTimeAt.equals(dateTimeTake)) {
            return 0.0;
        }*/

        //dateTimeAt is in range (dateTimeTake, dateTimeMax)
        //concentrationAtTimeN = (amountDose / (dateTimeMax - dateTimeTake)) * (dateTimeAt - dateTimeTake);
        if (dateTimeAt.compareTo(dateTimeTake) > 0 && dateTimeAt.compareTo(dateTimeMax) < 0) {
            concentrationAtTimeN = (amountDose / (dateTimeMax - dateTimeTake)) * (dateTimeAt - dateTimeTake);
            return concentrationAtTimeN;
        }

        //dateTimeAt = dateTimeMax
        //At dateTimeMax the concentration equals its original amount.
        else if (dateTimeAt.equals(dateTimeMax)) {
            return amountDose;
        }

        //dateTimeAt > dateTimeMax.
        // At this point, the fall of the concentration begins.
        //concentrationAtTimeN = amountDose * (1/2)^(timeHoursAt/timeHalfLIfe)
        else if (dateTimeAt.compareTo(dateTimeMax) > 0) {
            concentrationAtTimeN = amountDose * Math.pow(.5, (double) (dateTimeAt - dateTimeMax) / timeHalfLife);
            return concentrationAtTimeN;
        }

        //return 0 if dateTimeAt does not belong to any valid range
        else {
            return 0.0;
        }
    }

    @Override
    public String toString() {
        return "TestDose: " + this.isTestDose + "   Time taken: " + this.dateTimeTakeDose
                + "   Amount: " + this.amountDose;
    }

    //Advanced Features
    @Override
    public boolean getIsTestDose() {
        return isTestDose;
    }

    @Override
    public void setTestDose() {
        this.isTestDose = true;
    }
}