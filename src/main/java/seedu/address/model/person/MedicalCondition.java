package seedu.address.model.person;

/**
 * To record the patient MedicalCondition
 */
public class MedicalCondition {
    private String value;
    public MedicalCondition(String tagName) {
        this.value = tagName;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

}
