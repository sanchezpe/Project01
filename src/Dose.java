import java.time.LocalTime;

public class Dose implements IDose {
    private LocalTime timeTake = LocalTime.now();
    private double amount = 0;
    private boolean isTestDose = false;

    @Override
    public boolean isTestDose() {
        return isTestDose;
    }

    @Override
    public void createDose(LocalTime timeTake, double amount) {
        this.timeTake = timeTake;

        if (amount >= 0) {
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
        return "Test Dose: " + this.isTestDose() + "   Time taken: " + this.getTimeTake()
                + "   Amount: " + this.getAmount();
    }
}
