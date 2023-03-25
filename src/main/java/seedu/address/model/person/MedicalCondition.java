package seedu.address.model.person;

/**
 * To record the patient MedicalCondition
 */
public class MedicalCondition {
    private String value;
    public MedicalCondition(String tagName) {
        this.value = tagName;
    }

    /**
     * Returns true if the value is valid medical condition
     */
    public boolean isValidCondition(String value) {
        if (value == "") {
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
