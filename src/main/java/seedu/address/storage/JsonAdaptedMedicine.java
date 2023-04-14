package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.medicine.Medicine;

/**
 * Jackson-friendly version of {@link Medicine}.
 */
class JsonAdaptedMedicine {

    private final String medicineName;

    /**
     * Constructs a {@code JsonAdaptedMedicine} with the given {@code medicineName}.
     */
    @JsonCreator
    public JsonAdaptedMedicine(String medicineName) {
        this.medicineName = medicineName;
    }

    /**
     * Converts a given {@code Medicine} into this class for Jackson use.
     */
    public JsonAdaptedMedicine(Medicine source) {
        medicineName = source.medicineName;
    }

    @JsonValue
    public String getMedicineName() {
        return medicineName;
    }

    /**
     * Converts this Jackson-friendly adapted medicine object into the model's {@code Medicine} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted medicine.
     */
    public Medicine toModelType() throws IllegalValueException {
        if (!Medicine.isValidMedicineName(medicineName)) {
            throw new IllegalValueException(Medicine.MESSAGE_CONSTRAINTS);
        }
        return new Medicine(medicineName);
    }

}
