package seedu.address.storage.academics;

import com.fasterxml.jackson.annotation.JsonCreator;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.student.Assignment;
import seedu.address.model.person.student.Attendance;
import seedu.address.storage.JsonAdapted;

import java.time.format.DateTimeFormatter;

/**
 * Jackson-friendly version of {@link Assignment}.
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

    public JsonAdaptedAttendance(Attendance source) {
        this.attendance = source.toString();
    }

    @Override
    public Attendance toModelType() throws IllegalValueException {
        return new Attendance(attendance);
    }

    public String getAttendance() {
        return attendance;
    }

    @Override
    public String toString() {
        return attendance;
    }
}
