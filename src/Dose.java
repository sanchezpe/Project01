import java.time.LocalTime;

public class Dose implements DoseInterface {

    Medicine medicine;
    Double amountMedicine;
    LocalTime timePatientTakesMedicine;


    public void Dose(Double amountMedicine,LocalTime... timePatientTakesMedicine){
        this.amountMedicine= amountMedicine;
    }
    @Override
    public Double getDoseAmount() {
        return null;
    }

    @Override
    public LocalTime getDoseTakenTime() {
        return null;
    }

    @Override
    public Double getConcentrationAmount(LocalTime givenTime) {
        return null;
    }

    @Override
    public Double getConcentrationCurrentAmount() {
        return null;
    }

    @Override
    public Double whatIf(Double doseAmount, LocalTime... givenTime) {
        return null;
    }

    @Override
    public Double getConcentrationPeak() {
        return null;
    }

    @Override
    public LocalTime whenToDose() {
        return null;
    }

    @Override
    public Medicine getMedicine() {
        return medicine;
    }

    @Override
    public void parseMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

}
