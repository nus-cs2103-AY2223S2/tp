package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * To record the patient MedicalCondition
 */
public class MedicalCondition {
    public static final String MESSAGE_CONSTRAINTS = "Patient's medical condition should "
            + "not be empty or contains number";
    private String value;
    /**
     * @param value represents the medical condition of the patient
     */
    public MedicalCondition(String value) {
        requireNonNull(value);
        checkArgument(isValidCondition(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if the value is valid medical condition
     */
    public static boolean isValidCondition(String value) {
        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        for (int i = 0; i < numbers.length; i++) {
            if (value.indexOf(numbers[i]) != -1) {
                return false;
            }
        }
        if (value.equals("")) {
            return true;
        } else if (value.trim().equals("")) {
            return false;
        } else if (value.length() >= 1) {
            return true;
        }
        return false;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) { // short circuit if same object
            return true;
        }
        if (!(obj instanceof MedicalCondition)) { // instanceof handles nulls and other classes
            return false;
        }
        MedicalCondition other = (MedicalCondition) obj; // cast to the same class
        return ((MedicalCondition) obj).value == this.value;
    }


    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

}
