package seedu.socket.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.socket.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Project's deadline in SOCket.
 * Guarantees: immutable
 */
public class ProjectMeeting {
    public static final String DATE_TIME_FORMAT = "dd/MM/uu-HHmm";
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be a valid date in the format dd/MM/yy-HHmm";
    public static final DateTimeFormatter FORMAT = DateTimeFormatter
        .ofPattern(DATE_TIME_FORMAT)
        .withResolverStyle(ResolverStyle.STRICT);
    public final String meeting;

    /**
     * Constructs a {@code ProjectMeeting}.
     * @param date A date.
     */
    public ProjectMeeting(String date) {
        requireNonNull(date);
        checkArgument(isValidProjectMeeting(date), MESSAGE_CONSTRAINTS);
        meeting = date;
    }

    /**
     * Checks if the given DateTime {@code String} is of a valid format.
     * @param test {@code String} form of DateTime to check.
     * @return true if {@code String} is of valid DateTime format, else false.
     */
    public static Boolean isValidProjectMeeting(String test) {
        if (test.equals("")) {
            return true;
        }
        try {
            LocalDateTime.parse(test, FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if meeting is empty.
     *
     * @return {@code true} if meeting is empty.
     */
    public boolean isEmptyMeeting() {
        return meeting.equals("");
    }
    /**
     * Returns a {@code LocalDateTime} object if {@code meeting} is not empty.
     *
     * @return {@code LocalDateTime} object parsed from the stored {@code String meeting}.
     * @throws DateTimeParseException if {@code meeting} is empty.
     */
    public LocalDateTime toLocalDateTime() throws DateTimeParseException {
        return LocalDateTime.parse(meeting, FORMAT);
    }

    @Override
    public String toString() {
        return meeting;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectMeeting //instanceof handles nulls
                && meeting.equals(((ProjectMeeting) other).meeting)); // state check
    }

    public int hashCode() {
        return meeting.hashCode();
    }
}
