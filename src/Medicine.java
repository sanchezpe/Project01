import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

//Serializable eases the process of saving file
public class Medicine implements IMedicine, Serializable {
    private String name;
    private LocalTime tmax;
    private LocalTime halfLife;
    private ArrayList<IDose> doses = new ArrayList<>();

    //default constructor
    public Medicine() {
    }

    //parameterized constructor
    public Medicine(String name, LocalTime tMax, LocalTime halfLife) {
        createMedicine(name, tMax, halfLife);

    }

    @Override
    public void createMedicine(String name, LocalTime tMax, LocalTime halfLife) {
        this.name = name;
        this.tmax = tMax;
        this.halfLife = halfLife;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalTime getHalfLife() {
        return halfLife;
    }

    @Override
    public ArrayList<IDose> getDoses() {
        return doses;
    }

    @Override
    public LocalTime getTmax() {
        return tmax;
    }

    @Override
    public String toString() {
        return "Medicine: " + this.name + "   TMax: " + this.tmax + "   Half Life: " + this.halfLife;
    }
}
