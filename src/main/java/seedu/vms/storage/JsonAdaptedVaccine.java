package seedu.vms.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.GroupName;

/**
 * Jackson-friendly version of {@link GroupName}.
 */
class JsonAdaptedVaccine {

    private final String vaccineName;

    /**
     * Constructs a {@code JsonAdaptedVaccine} with the given {@code vaccineName}.
     */
    @JsonCreator
    public JsonAdaptedVaccine(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    /**
     * Converts a given {@code Vaccine} into this class for Jackson use.
     */
    public JsonAdaptedVaccine(GroupName source) {
        vaccineName = source.getName();
    }

    @JsonValue
    public String getVaccineName() {
        return vaccineName;
    }

    /**
     * Converts this Jackson-friendly adapted vaccine object into the model's {@code Vaccine} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted vaccine.
     */
    public GroupName toModelType() throws IllegalValueException {
        if (!GroupName.isValidName(vaccineName)) {
            throw new IllegalValueException(GroupName.MESSAGE_CONSTRAINTS);
        }
        return new GroupName(vaccineName);
    }

}
