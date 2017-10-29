import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

//Serializable eases the process of saving file
class Medicine implements Serializable, IMedicine {
    /**
     * Medicine name
     */
    private String nameMedicine;

    /**
     * Medicine tMax.
     * Time when the concentration will be at its peak.
     */
    private LocalTime timeMaxMedicine;

    /**
     * Medicine half life.
     * Time it takes for the concentration to be reduced by half of its amount.
     */
    private LocalTime timeHalfLifeMedicine;
    private ArrayList<IDose> doses = new ArrayList<>();

    //default constructor
    public Medicine() {
    }

    //parameterized constructor
    public Medicine(String name, LocalTime tMax, LocalTime timeHalfLifeMedicine) {
        createMedicine(name, tMax, timeHalfLifeMedicine);

    }

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
    public void addDose(IDose dose) {
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
    public Double getConcentrationsAtTime(LocalDateTime dateTime) {
        Double concentrationsAtTime = 0.0;

        for (IDose d : doses) {
            concentrationsAtTime +=
                    d.getConcentrationAtTime(dateTime, this.timeMaxMedicine, this.timeHalfLifeMedicine);
        }
        return concentrationsAtTime;
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
