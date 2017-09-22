testDoseAmmount:
        input = an input integer from the user;
        if input <= 0,  assert dose.errors.contains("Dose ammount cannot be less than 0.");

        if not input <= 0, assert dose.amount.equals(input);

testDoseTakenTime:
        input = an input integer from the user;
        if input <= 0, assert dose.errors.contains("Dose time invalid. Setting current time instead");
        dose.time.now();

        if not input <= 0, assert dose.time.equals(input);

testDoseDsiplayCurrentConcentrationAmount:
        assert doses.time.equals.time.now();


testDoseDisplayConcentrationAmountGivenTime:
        input = an input integer from the user;
        if input <= 0, assert dose.errors.contains("Dose time invalid");

        if not input <= 0, assert dose.time.equals(input);

//Advanced
dosePeakLevel:

whenToDoseLevel:





