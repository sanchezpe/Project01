package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

//Serializable eases the process of saving file
public class Medicine implements Serializable, IMedicine {
    /**
     * Medicine name
     */
    private String nameMedicine = "NotSet";

    /**
     * Medicine tMax
     * Time when the concentration will be at its peak.
     */
    private LocalTime timeMaxMedicine = LocalTime.of(0, 0);

    /**
     * Medicine half life
     * Time it takes for the concentration to be reduced by half of its amount.
     */
    private LocalTime timeHalfLifeMedicine = LocalTime.of(0, 0);
    ;

    /**
     * Array of doses
     */
    private ArrayList<IDose> doses = new ArrayList<>();

    @Override
    public void createMedicine(String nameMedicine, LocalTime timeMaxMedicine, LocalTime timeHalfLifeMedicine) {
        this.nameMedicine = nameMedicine;
        this.timeMaxMedicine = timeMaxMedicine;
        this.timeHalfLifeMedicine = timeHalfLifeMedicine;
    }

    @Override
    public String getNameMedicine() {
        return nameMedicine;
    }

    @Override
    public LocalTime getTimeHalfLifeMedicine() {
        return timeHalfLifeMedicine;
    }

    @Override
    public ArrayList<IDose> getDoses() {
        return doses;
    }

    @Override
    public LocalTime getTimeMaxMedicine() {
        return timeMaxMedicine;
    }

    @Override
    public void addDose(LocalDateTime dateTimeTakeDose, double amount, Boolean isTestDose) {
        IDose dose = new Dose();
        dose.createDose(dateTimeTakeDose, amount, isTestDose);
        doses.add(dose);
    }

    @Override
    public void removeAllDoses() {
        doses.clear();
    }

    @Override
    public void removeDose(int index) {
        doses.remove(index);
    }


    @Override
    public String toString() {
        return "Medicine: " + this.nameMedicine + "   TMax: " + this.timeMaxMedicine + "   Half Life: " + this.timeHalfLifeMedicine;
    }

    //Advanced Features
    @Override
    public void removeTestDoses() {
        //easiest way to remove array elements
        doses.removeIf(IDose::getIsTestDose);
    }
}
