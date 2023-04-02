package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.util.SampleDataUtil.EMPTY_INPUT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Module's deadline.
 */
public class Deadline implements Comparable<Deadline> {
    public static final String MESSAGE_CONSTRAINTS =
            "Deadline should be of format \"ddMMyyyy HH:mm\" (Example: 230223 18:00)";
    public static final String VALIDATION_REGEX = "^[0-9]{6}\\s[0-9]{2}[:][0-9]{2}$";

    public final LocalDateTime value;

    /**
     * Constructs an {@code Deadline}.
     *
     * @param deadline A valid deadline.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        checkArgument(isValidDeadline(trimmedDeadline), MESSAGE_CONSTRAINTS);
        value = convertStringToDate(trimmedDeadline);
    }

    /**
     * Returns if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {
        if (test.equals(EMPTY_INPUT)) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the conversion of String to a LocalDateTime
     * @return LocalDateTime instance
     */
    private LocalDateTime convertStringToDate(String deadline) {
        if (deadline.equals(EMPTY_INPUT)) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyy HH:mm");
        return LocalDateTime.parse(deadline, dateTimeFormatter);
    }

    @Override
    public String toString() {
        if (value == null) {
            return EMPTY_INPUT;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyy HH:mm");
        return dateTimeFormatter.format(value);
    }

    public String displayInUI() {
        if (value == null) {
            return EMPTY_INPUT;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
        return dateTimeFormatter.format(value);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.module.Deadline // instanceof handles nulls
                && value.equals(((seedu.address.model.module.Deadline) other).value)); // state check
    }

    @Override
    public int hashCode() {
        if (value == null) {
            return EMPTY_INPUT.hashCode();
        }
        return value.hashCode();
    }


    @Override
    public int compareTo(Deadline otherDeadline) {
        return value.compareTo(otherDeadline.value);
    }
}
