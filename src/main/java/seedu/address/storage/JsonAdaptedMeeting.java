package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Meeting;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
class JsonAdaptedMeeting {
    private final String description;
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given {@code start @code name}.
     */
    @JsonCreator
    public JsonAdaptedMeeting(String dateTime) {
        String[] dateTimeStrings = dateTime.split("\\?");
        this.description = dateTimeStrings[0];
        this.start = LocalDateTime.parse(dateTimeStrings[1].trim());
        this.end = LocalDateTime.parse(dateTimeStrings[2].trim());
    }

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        this.description = source.getDescription();
        this.start = source.getStart();
        this.end = source.getEnd();
    }

    @JsonValue
    public String getString() {
        return this.description + "?" + this.start.toString() + "?" + this.end.toString();
    }

    /**
     * Converts this Jackson-friendly adapted Meeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if start is after end
     */
    public Meeting toModelType() throws IllegalValueException {
        if (this.end.isBefore(this.start)) {
            throw new IllegalValueException("End time is after start!");
        }
        return new Meeting(this.description, this.start, this.end);
    }

}
