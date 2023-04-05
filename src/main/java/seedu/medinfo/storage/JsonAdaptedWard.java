package seedu.medinfo.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.medinfo.commons.exceptions.IllegalValueException;
import seedu.medinfo.model.patient.Name;
import seedu.medinfo.model.ward.Capacity;
import seedu.medinfo.model.ward.Ward;
import seedu.medinfo.model.ward.WardName;


/**
 * Jackson-friendly version of {@link Ward}.
 */
class JsonAdaptedWard {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Ward %s field is missing!";

    private final String name;
    private final String capacity;


    /**
     * Constructs a {@code JsonAdaptedWard} with the given details.
     */
    @JsonCreator
    public JsonAdaptedWard(@JsonProperty("name") String name, @JsonProperty("capacity") String capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    /**
     * Converts a given {@code Ward} into this class for Jackson use.
     */
    public JsonAdaptedWard(Ward source) {
        name = source.getNameString();
        capacity = source.getCapacityString();

    }

    /**
     * Converts this Jackson-friendly adapted patient object into the model's
     * {@code Ward} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted patient.
     */
    public Ward toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final WardName modelName = new WardName(name);

        if (capacity == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Capacity.class.getSimpleName()));
        }
        if (!Capacity.isValidCapacity(capacity)) {
            throw new IllegalValueException(Capacity.MESSAGE_CONSTRAINTS);
        }
        final Capacity modelCapacity = new Capacity(capacity);

        return new Ward(modelName, modelCapacity);
    }

}
