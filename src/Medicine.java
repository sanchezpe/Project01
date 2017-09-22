import java.time.LocalTime;

public class Medicine implements MedicineInterface {
    private String medicineName;
    private LocalTime tMax;
    private LocalTime halfLife;


    public Medicine(String medicineName, int tMax, LocalTime halfLife) {
        this.medicineName = medicineName;
        this.tMax = new LocalTime(tMax,0,0,0);
        this.halfLife = halfLife;

    }

    @Override
    public String getMedicineName() {
        return medicineName;
    }

    @Override
    public LocalTime getMedicineTMax() {
        return tMax;
    }

    @Override
    public LocalTime getHalfLife() {
        return halfLife;
    }

}
