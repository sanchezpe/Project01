testDoseAmmount:
        input = an input integer from the user;
        if input <= 0,  assert dose.errors.contains("Dose ammount cannot be less than 0.");

        if not input <= 0, assert dose.amount.equals(input);

testDoseTakenTime:
        input = an input integer from the user;
        if input <= 0, assert dose.errors.contains("Dose time invalid. Setting current time instead");
        dose.time.now();

        if not input <= 0, assert dose.time.equals(input);

testDoseDisplayCurrentConcentrationAmount:
        if dose.time.equals  > dose.takentime && dose.time.equals < medicine.tmax, assert dose.concentration.lessThan.dose.amount

testDoseDisplayConcentrationAmountGivenTime:
        input = an input integer from the user;
        if input <= 0, assert dose.errors.contains("Dose time invalid");

        if not input <= 0, assert dose.time.equals(input);

//Advanced
dosePeakLevel:
        if medicine.tmax != dose.time, assert dose.errors.contains("Indalid peak level");

        if medicine.tmax == dose.time, assert dose.currentconcentrationamount.equals(doseamount);

whenToDoseLevel:
        assert dose.time.add(medicine.tmax)





