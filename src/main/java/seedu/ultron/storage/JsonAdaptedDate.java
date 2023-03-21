package seedu.ultron.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.ultron.commons.exceptions.IllegalValueException;
import seedu.ultron.model.opening.Keydate;

/**
 * Jackson-friendly version of {@link Keydate}.
 */
class JsonAdaptedDate {

    private final String name;
    private final String date;

    /**
     * Constructs a {@code JsonAdapteddate} with the given {@code dateName}.
     */
    @JsonCreator
    public JsonAdaptedDate(@JsonProperty("name") String name, @JsonProperty("date") String date) {
        this.name = name;
        this.date = date;
    }

    /**
     * Converts a given {@code date} into this class for Jackson use.
     */
    public JsonAdaptedDate(Keydate source) {
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
    public Keydate toModelType() throws IllegalValueException {
        if (!Keydate.isValidDate(date)) {
            throw new IllegalValueException(Keydate.MESSAGE_CONSTRAINTS);
        }
        return new Keydate(name, date);
    }

}
