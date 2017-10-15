import java.io.Serializable;
import java.time.LocalTime;

//Serializable eases the process of saving file
public class Dose implements IDose, Serializable {
    private LocalTime timeTake = LocalTime.now();
    private double amount = 1;
    private boolean isTestDose = false;

    //default constructor
    public Dose() {
    }

    //parameterized constructor
    public Dose(LocalTime timeTake, double amount) {
        createDose(timeTake, amount);
    }

    public Dose(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean isTestDose() {
        return isTestDose;
    }

    @Override
    public void createDose(LocalTime timeTake, double amount) {
        this.timeTake = timeTake;

        if (amount > 0) {
            this.amount = amount;
        }
        this.isTestDose = false;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public LocalTime getTimeTake() {
        return timeTake;
    }

    @Override
    public void setTestDose() {
        this.isTestDose = true;
    }

    @Override
    public String toString() {
        return "TestDose: " + this.isTestDose() + "   Time taken: " + this.getTimeTake()
                + "   Amount: " + this.getAmount();
    }
}
