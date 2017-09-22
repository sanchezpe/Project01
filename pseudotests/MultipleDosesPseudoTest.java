testDosesRemove:
        input=an input integer from the user;
        expected=doses.lenght-1;
        doses.remove(input);

        if input<=0,assert doses.errors.contains("Invallid index, cannot be less than 0.");

        if not input<=0,assert doses.lenght.equals(expected);

testDosesRemoveAll:
        if doses.length>0,assert doses.error.contains("Doses were not removed");

        assert doses.lenght.equals(0);

testDosesSaveToFile:
        expected = doses.toString;

        if not doses.writeto.file.content == expected, assert doses.error.contains("Error saving the file")

        assert doses.toString(expected);

testDosesLoadFromFile:
        expected = file.contents;

        if not doses.toString == expected, assert doses.error.contains()

        assert doses.toString(expected);

//Advanced
whatIfDose:


