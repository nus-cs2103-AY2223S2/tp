package seedu.ultron.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.ultron.commons.exceptions.IllegalValueException;
import seedu.ultron.model.opening.Keydate;

/**
 * Jackson-friendly version of {@link Keydate}.
 */
class JsonAdaptedKeydate {

    private final String name;
    private final String date;

    /**
     * Constructs a {@code JsonAdaptedKeydate} with the given {@code keydate}.
     */
    @JsonCreator
    public JsonAdaptedKeydate(@JsonProperty("name") String name, @JsonProperty("date") String date) {
        this.name = name;
        this.date = date;
    }

    /**
     * Converts a given {@code keydate} into this class for Jackson use.
     */
    public JsonAdaptedKeydate(Keydate source) {
        name = source.fullKey;
        date = source.fullDate;
    }

    /**
     * Converts this Jackson-friendly adapted date object into the model's {@code keydate} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted date.
     */
    public Keydate toModelType() throws IllegalValueException {
        if (!Keydate.isValidKeydate(new String[]{name, date})) {
            throw new IllegalValueException(Keydate.MESSAGE_CONSTRAINTS);
        }
        return new Keydate(name, date);
    }

}
