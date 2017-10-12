import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Medicine implements IMedicine, Serializable {
    private String name;
    private double tmax;
    private double halfLife;
    private ArrayList<IDose> doses = new ArrayList<>();

    @Override
    public void createMedicine(String name, double tMax, double halfLife) {
        this.name = name;
        this.tmax = tMax;
        this.halfLife = halfLife;
    }

    @Override
    public void addDose(IDose dose) {
        doses.add(dose);

    }

    @Override
    public void removeDose(IDose dose) {
        doses.remove(dose);
    }

    //not needed (?)
    @Override
    public void addTestDose(IDose dose) {
    }

    @Override
    public void removeTestDoses() {
        for (IDose d : doses) {
            if (d.areYouATestDose()) {
                removeDose(d);
            }
        }
    }

    @Override
    public double getConcentrationAtTime(int timeInSeconds) {
        double concentrationAmountAtTime = getDoses().get(0).getAmount() * Math.pow((1 / 2), (timeInSeconds / halfLife));
        return concentrationAmountAtTime;
    }

    @Override
    public ArrayList<IDose> getDoses() {
        return doses;
    }


}
