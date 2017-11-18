package Model;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    //Advanced Feature
    /**
     * Defines whether dose is a test dose
     * Default: false.
     */
    private Boolean isTestDose = false;

    @Override
    public void createDose(LocalDateTime dateTimeTakeDose, double amountDose, Boolean isTestDose) {
        this.dateTimeTakeDose = dateTimeTakeDose;
        this.amountDose = amountDose;
        this.isTestDose = isTestDose;
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
    public String toString() {
        return "TestDose: " + this.isTestDose + "   Time taken: " + this.dateTimeTakeDose
                + "   Amount: " + this.amountDose;
    }

    //Advanced Features
    @Override
    public boolean getIsTestDose() {
        return isTestDose;
    }

}
