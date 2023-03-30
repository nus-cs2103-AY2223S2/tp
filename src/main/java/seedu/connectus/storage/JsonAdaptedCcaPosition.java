package seedu.connectus.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.connectus.commons.exceptions.IllegalValueException;
import seedu.connectus.model.tag.CcaPosition;

/**
 * Jackson-friendly version of {@link CcaPosition}.
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
    public JsonAdaptedCcaPosition(CcaPosition source) {
        this.ccaPositionName = source.ccaPositionName;
    }

    @JsonValue
    public String getCcaPositionName() {
        return ccaPositionName;
    }

    /**
     * Converts this Jackson-friendly adapted CCA Position object into the model's {@code CcaPosition} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted ccaPosition.
     */
    public CcaPosition toModelType() throws IllegalValueException {
        if (!CcaPosition.isValidCcaPositionName(ccaPositionName)) {
            throw new IllegalValueException(CcaPosition.MESSAGE_CONSTRAINTS);
        }
        return new CcaPosition(ccaPositionName);
    }

}
