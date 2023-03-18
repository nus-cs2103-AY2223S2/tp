package seedu.socket.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.socket.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Project's deadline in SOCket.
 * Guarantees: immutable
 */
public class ProjectDeadline {
    public static final String DATE_TIME_FORMAT = "dd/MM/yy-HHmm";
    public static final String MESSAGE_CONSTRAINTS =
        "Date should be in format dd/MM/yy-HHmm";
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    public final String deadline;

    /**
     * Constructs a {@code ProjectDeadline}.
     * @param date A date.
     */
    public ProjectDeadline(String date) {
        requireNonNull(date);
        checkArgument(isValidProjectDeadline(date), MESSAGE_CONSTRAINTS);
        deadline = date;
    }

    /**
     * Checks if the given DateTime {@code String} is of a valid format.
     * @param test {@code String} form of DateTime to check.
     * @return true if {@code String} is of valid DateTime format, else false.
     */
    public static Boolean isValidProjectDeadline(String test) {
        if (test == "") {
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
     * Returns a LocalDateTime object if deadline is not empty.
     * @return LocalDateTime
     */
    public LocalDateTime toLocalDateTime() {
        if (deadline == "") {
            return null; // this is dangerous TODO consider rethinking implementation
        }
        return LocalDateTime.parse(deadline, FORMAT);
    }

    @Override
    public String toString() {
        return deadline;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectDeadline //instanceof handles nulls
                && deadline.equals(((ProjectDeadline) other).deadline)); // state check
    }

    public int hashCode() {
        return deadline.hashCode();
    }
}
