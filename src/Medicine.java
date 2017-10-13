import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

//Serializable eases the process of saving file
public class Medicine implements IMedicine, Serializable {
    private String name;
    private LocalTime tmax;
    private double halfLife;
    private ArrayList<IDose> doses = new ArrayList<>();

    @Override
    public void createMedicine(String name, LocalTime tMax, double halfLife) {
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

    @Override
    public void removeTestDoses() {
        for (IDose d : doses) {
            if (d.isTestDose()) {
                removeDose(d);
            }
        }
    }

    @Override
    public double getConcentrationAtTime(LocalTime time) {

        int timeInSeconds = time.toSecondOfDay();
        double concentrationAmountAtTime = getDoses().get(0).getAmount() * Math.pow((1 / 2), (timeInSeconds / halfLife));
        return concentrationAmountAtTime;
    }

    @Override
    public ArrayList<IDose> getDoses() {
        return doses;
    }

    @Override
    public String toString() {
        return "Medicine: " + this.name + "   TMax: " + this.tmax + "   Half Life: " + this.halfLife + "\n";
    }
}
