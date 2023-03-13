package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.opening.Date;

/**
 * Jackson-friendly version of {@link Date}.
 */
class JsonAdaptedDate {

    private final String name;
    private final String date;

    /**
     * Constructs a {@code JsonAdapteddate} with the given {@code dateName}.
     */
    @JsonCreator
    public JsonAdaptedDate(String name, String date) {
        this.name = name;
        this.date = date;
    }

    /**
     * Converts a given {@code date} into this class for Jackson use.
     */
    public JsonAdaptedDate(Date source) {
        name = source.fullName;
        date = source.fullDate;
    }

    @JsonValue
    public String getdateName() {
        return date;
    }

    /**
     * Converts this Jackson-friendly adapted date object into the model's {@code date} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted date.
     */
    public Date toModelType() throws IllegalValueException {
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(name, date);
    }

}
