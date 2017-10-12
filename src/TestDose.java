public class TestDose implements IDose {
    private int timeTake;
    private float amount;

    @Override
    public boolean areYouATestDose() {
        return true;
    }

    @Override
    public void createDose(int timeTake, float amount) {
        this.timeTake = timeTake;
        this.amount = amount;
    }

    @Override
    public String getDisplayVersion() {
        return null;
    }

    @Override
    public double getAmount() {
        return amount;
    }
}
