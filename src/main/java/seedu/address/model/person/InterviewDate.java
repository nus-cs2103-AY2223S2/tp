package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an InternshipApplication's interviewDate in the tracker.
 */
public class InterviewDate {

    public static final String MESSAGE_CONSTRAINTS = "Interview dates should be of the format 'MM/dd/yyyy hh:mm a'.";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
    public final LocalDateTime value;

    /**
     * Constructs an {@code InterviewDate}.
     *
     * @param interviewDate A valid date-string.
     */
    public InterviewDate(String interviewDate) {
        requireNonNull(interviewDate);
        checkArgument(isValidInterviewDate(interviewDate), MESSAGE_CONSTRAINTS);
        value = LocalDateTime.parse(interviewDate, FORMATTER);
    }

    /**
     * returns if a given LocalDateTime is valid or null (ie interviewDate has not been added).
     */
    public static boolean isValidInterviewDate(String test) {
        if (test == null) {
            return true;
        }
        try {
            LocalDateTime.parse(test, FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return value.format(FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Email // instanceof handles nulls
                && value.equals(((Email) other).value)); // state check
    }
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
