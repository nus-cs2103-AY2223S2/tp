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

    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given {@code start @code name}.
     */
    @JsonCreator
    public JsonAdaptedMeeting(String start, String end) throws Exception {
        this.start = LocalDateTime.parse(start);
        this.end = LocalDateTime.parse(end);
        if (this.end.isBefore(this.start)) {
            throw new Exception("End time is after start!");
        }
    }

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        this.start = source.getStart();
        this.end = source.getEnd();
    }

    @JsonValue
    public String getString() {
        return this.start.toString() + this.end.toString();
    }

    /**
     * Converts this Jackson-friendly adapted Meeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if start is after end
     */
    public Meeting toModelType() throws IllegalValueException {
        // if (this.end.isBefore(this.start)) {
        //     throw new IllegalValueException("End time is after start!");
        // }
        return new Meeting(this.start, this.end);
    }

}
