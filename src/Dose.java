import java.sql.Time;
import org.joda.time.LocalTime;



public class Dose implements DoseInterface {

    Medicine medicine;
    Double amountMedicine;
    LocalTime timePatientTakesMedicine = new LocalTime();


    public void Dose(Double amountMedicine,Time... timePatientTakesMedicine){
        this.amountMedicine= amountMedicine;
    }
    @Override
    public Double getDoseAmount() {
        return null;
    }

    @Override
    public Time getDoseTakenTime() {
        return null;
    }

    @Override
    public Double getConcentrationAmount(Time givenTime) {
        return null;
    }

    @Override
    public Double getConcentrationCurrentAmount() {
        return null;
    }

    @Override
    public Double whatIf(Double doseAmount, Time... givenTime) {
        return null;
    }

    @Override
    public Double getConcentrationPeak() {
        return null;
    }

    @Override
    public Time whenToDose() {
        return null;
    }

    @Override
    public Medicine getMedicine() {
        return medicine;
    }

    @Override
    public void parseMedicine(Medicine medicine) {

    }

}
