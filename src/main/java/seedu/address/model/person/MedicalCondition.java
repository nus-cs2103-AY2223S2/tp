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
        return obj == this || (obj instanceof MedicalCondition
                && obj.equals(((MedicalCondition) obj).value));
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

}
