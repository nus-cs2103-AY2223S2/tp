package seedu.connectus.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.connectus.commons.exceptions.IllegalValueException;
import seedu.connectus.model.tag.Major;

/**
 * Jackson-friendly version of {@link Major}.
 */
class JsonAdaptedCcaPosition {

    private final String ccaPositionName;

    /**
     * Constructs a {@code JsonAdaptedCcaPosition} with the given {@code ccaPositionName}.
     */
    @JsonCreator
    public JsonAdaptedCcaPosition(String ccaPositionName) {
        this.ccaPositionName = ccaPositionName;
    }

    /**
     * Converts a given {@code CcaPosition} into this class for Jackson use.
     */
    public JsonAdaptedCcaPosition(Major source) {
        this.ccaPositionName = source.major;
    }

    @JsonValue
    public String getCcaPositionName() {
        return ccaPositionName;
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code CcaPosition} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted ccaPosition.
     */
    public Major toModelType() throws IllegalValueException {
        if (!Major.isValidMajorName(ccaPositionName)) {
            throw new IllegalValueException(Major.MESSAGE_CONSTRAINTS);
        }
        return new Major(ccaPositionName);
    }

}
