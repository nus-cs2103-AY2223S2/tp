package seedu.address.storage.academics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.student.Attendance;
import seedu.address.storage.JsonAdapted;


/**
 * Jackson-friendly version of {@link Attendance}.
 */
public class JsonAdaptedAttendance implements JsonAdapted<Attendance> {
    private final String attendance;

    /**
     * Constructs a {@code JsonAdaptedAttendance} with the given {@code attendance}.
     */
    @JsonCreator
    public JsonAdaptedAttendance(String attendance) {
        this.attendance = attendance;
    }

    /**
     * Converts a given {@code Attendance} into this class for Jackson use.
     * @param source future changes to this will not affect the created JsonAdaptedAttendance
     */
    public JsonAdaptedAttendance(Attendance source) {
        this.attendance = source.toString();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Attendance} object.
     * @return the model's Attendance object
     * @throws IllegalValueException if there were any data constraints violated in the adapted Attendance.
     */
    @Override
    public Attendance toModelType() throws IllegalValueException {
        return new Attendance(attendance);
    }

    /**
     * Returns the attendance of the student.
     * @return the attendance of the student
     */
    @JsonValue
    public String getAttendance() {
        return attendance;
    }

    @Override
    public String toString() {
        return attendance;
    }
}
