testNameOfMedicineInput: 
        input = an input string from the user;
        if input.empty() or input == null, assert medicine.errors.contains("Medicine name cannot be empty.");

        if not input.empty(), assert medicine.name.equals(input);

testMedicineTMax:
        input = an input integer from the user;
        if input <= 0, assert medicine.errors.contains("Tmax cannot be less than 0.");

        if not input <= 0, assert medicine.tmax.equals(input);

testMedicineHalfLIfe:
        input = an input integer from the user;
        if input <= 0, assert medicine.errors.contains("Half life cannot be less than 0.");

        if not input <= 0, assert medicine.halflife.equals(input);