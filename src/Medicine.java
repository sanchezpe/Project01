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
    /*
    DO NOT USE FOR loop if you plan to remove doses as you traverse.
    Use an LIST ITERATOR to remove dose as you traverse the array.
    Use a FOR loop to first collect doses AND then remove them.
     */
    public void removeTestDoses() {
        //easiest way to remove array elements
        getDoses().removeIf(IDose::isTestDose);
    }

    @Override
    public double getConcentrationAtTime(LocalTime time) {

        return getDoses().get(0).getAmount() * Math.pow(.5, ((double) time.toSecondOfDay() / halfLife));
    }

    @Override
    public ArrayList<IDose> getDoses() {
        return doses;
    }

    public void printDoses() {
        for (IDose dose : doses) {
            System.out.print(dose + "\n");
        }
    }

    @Override
    public String toString() {
        return "Medicine: " + this.name + "   TMax: " + this.tmax + "   Half Life: " + this.halfLife;
    }
}
