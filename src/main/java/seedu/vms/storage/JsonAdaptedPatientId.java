package seedu.vms.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.vms.commons.core.index.Index;


/**
 * A JSON friendly representation of {@link Index}.
 */
public class JsonAdaptedPatientId {
    public final Integer value;


    /**
     * Constructs a {@code JsonAdaptedAge}.
     */
    @JsonCreator
    public JsonAdaptedPatientId(Integer value) {
        this.value = value;
    }


    /**
     * Converts an {@code Index} to a {@code JsonAdaptedPatientId}.
     *
     * @param index - the patient id to convert.
     * @return the {@code JsonAdaptedPatientId} representation of the specified
     *      {@code Index}.
     */
    public static JsonAdaptedPatientId fromModelType(Index index) {
        return new JsonAdaptedPatientId(index.getOneBased());
    }


    @JsonValue
    public Integer getValue() {
        return value;
    }


    /** Returns the {@code Index} that this {@code JsonAdaptedPatientId} represents. */
    public Index toModelType() {
        return Index.fromOneBased(value);
    }
}
