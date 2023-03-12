package seedu.address.model.person;

/**
 * To record the patient MedicalCondition
 */
public class MedicalCondition {
    public String value;
    public MedicalCondition(String tagName) {
        this.value = tagName;
    }

    @Override
    public String toString() {
        return value;
    }

    private String getValue() {
        return value;
    }

}
