package seedu.address.model.person;

import seedu.address.model.tag.Tag;

public class MedicalCondition {
    public String value;
    public MedicalCondition(String tagName) {
        this.value = tagName;
    }

    @Override
    public String toString() {
      return  value;
    }

    private String getValue() {
        return value;
    }

}
