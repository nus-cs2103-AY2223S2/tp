package seedu.vms.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.Age;


/**
 * A JSON friendly representation of {@link Age}.
 */
public class JsonAdaptedAge {
    public final Integer value;


    /**
     * Constructs a {@code JsonAdaptedAge}.
     */
    @JsonCreator
    public JsonAdaptedAge(Integer value) {
        this.value = value;
    }


    /**
     * Converts an {@code Age} to a {@code JsonAdaptedAge}.
     *
     * @param age - the age to convert.
     * @return the {@code JsonAdaptedAge} representation of the specified
     *      {@code Age}.
     */
    public static JsonAdaptedAge fromModelType(Age age) {
        return new JsonAdaptedAge(age.getValue());
    }


    @JsonValue
    public Integer getValue() {
        return value;
    }


    /**
     * Returns the {@code Age} that this {@code JsonAdaptedAge} represents.
     *
     * @throws IllegalValueException if the age cannot be converted.
     */
    public Age toModelType() throws IllegalValueException {
        if (!Age.isValid(value)) {
            throw new IllegalValueException(Age.MESSAGE_CONSTRAINTS);
        }
        return new Age(value);
    }
}
