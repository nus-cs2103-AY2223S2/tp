package seedu.vms.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.GroupName;

/**
 * Jackson-friendly version of {@link GroupName}.
 */
class JsonAdaptedAllergy {

    private final String allergyName;

    /**
     * Constructs a {@code JsonAdaptedAllergy} with the given {@code allergyName}.
     */
    @JsonCreator
    public JsonAdaptedAllergy(String allergyName) {
        this.allergyName = allergyName;
    }

    /**
     * Converts a given {@code Allergy} into this class for Jackson use.
     */
    public JsonAdaptedAllergy(GroupName source) {
        allergyName = source.getName();
    }

    @JsonValue
    public String getAllergyName() {
        return allergyName;
    }

    /**
     * Converts this Jackson-friendly adapted allergy object into the model's {@code Allergy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted allergy.
     */
    public GroupName toModelType() throws IllegalValueException {
        if (!GroupName.isValidName(allergyName)) {
            throw new IllegalValueException(GroupName.MESSAGE_CONSTRAINTS);
        }
        return new GroupName(allergyName);
    }

}
