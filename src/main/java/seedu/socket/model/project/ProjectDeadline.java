package seedu.socket.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.socket.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Project's deadline in SOCket.
 * Guarantees: immutable
 */
public class ProjectDeadline {
    public static final String DATE_TIME_FORMAT = "dd/MM/yy-HHmm";
    public static final String MESSAGE_CONSTRAINTS =
        "Date should be in format DD/MM/YY-HHmm";
    public static final String VALIDATION_REGEX = "";
    public final String deadline;
    public final DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    /**
     * Constructs a {@code ProjectDeadline}.
     * @param date A date.
     */
    public ProjectDeadline(String date) {
        requireNonNull(date);
        checkArgument(isValidProjectDeadline(date), MESSAGE_CONSTRAINTS);
        deadline = date;
    }

    private Boolean isValidProjectDeadline(String test) {
        //TODO add actual validation
        return !test.equals(" ");
    }

    /**
     * Returns a LocalDateTime object if deadline is not empty.
     * @return LocalDateTime
     */
    public LocalDateTime toLocalDateTime() {
        if (deadline == "") {
            return null;
        }
        return LocalDateTime.parse(deadline, format);
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
