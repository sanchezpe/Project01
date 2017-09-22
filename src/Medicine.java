import java.sql.Time;

public class Medicine implements MedicineInterface {
    String medicineName;
    Time tMax;
    Time halfLife;


    public void Medicine(String medicineName,Time tMax, Time halfLife ){
        this.medicineName = medicineName;
        this.tMax = tMax;
        this.halfLife = halfLife;

    }

    @Override
    public String getMedicineName() {
        return medicineName;
    }

    @Override
    public Time getMedicineTMax() {
        return null;
    }

    @Override
    public Time getHalfLife() {
        return null;
    }

}
