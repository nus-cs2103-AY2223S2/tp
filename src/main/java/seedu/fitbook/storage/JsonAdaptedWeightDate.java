package seedu.fitbook.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.fitbook.commons.exceptions.IllegalValueException;
import seedu.fitbook.model.client.Date;

/**
 * Jackson-friendly version of {@link Date}.
 */
class JsonAdaptedWeightDate {

    private final String date;

    /**
     * Constructs a {@code JsonAdaptedWeightHistory} with the given {@code date}.
     */
    @JsonCreator
    public JsonAdaptedWeightDate(String date) {
        this.date = date;
    }

    /**
     * Converts a given {@code Weight} into this class for Jackson use.
     */
    public JsonAdaptedWeightDate(Date source) {
        date = source.dateTime;
    }

    @JsonValue
    public String getDate() {
        return date;
    }

    /**
     * Converts this Jackson-friendly adapted date object into the model's {@code Date} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted date.
     */
    public Date toFitBookModelType() throws IllegalValueException {
        if (!Date.isValidDateFormat(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(date);
    }

}
