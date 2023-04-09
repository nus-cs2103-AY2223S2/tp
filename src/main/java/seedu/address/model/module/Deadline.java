package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.util.SampleDataUtil.EMPTY_INPUT;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Module's deadline.
 */
public class Deadline implements Comparable<Deadline> {
    public static final String MESSAGE_CONSTRAINTS_INVALID_DATE_FORMAT =
            "Deadline should be of format 'ddMMyy HH:mm' (Example: 230223 18:00). It cannot be blank.";
    public static final String MESSAGE_CONSTRAINTS_INVALID_DATE =
            "Deadline is invalid, please ensure that the date or time is valid.";
    public static final String VALIDATION_REGEX = "^[0-9]{6}\\s[0-9]{2}[:][0-9]{2}$";

    public final LocalDateTime value;

    /**
     * Constructs an {@code Deadline}.
     *
     * @param deadline A valid deadline.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim(); //remove trailing spaces
        checkArgument(isValidFormat(trimmedDeadline), MESSAGE_CONSTRAINTS_INVALID_DATE_FORMAT);
        checkArgument(isValidDate(trimmedDeadline), MESSAGE_CONSTRAINTS_INVALID_DATE);
        value = convertStringToDate(trimmedDeadline);
    }

    /**
     * Checks if a deadline has a valid format.
     * @param deadline a deadline string to check
     * @return a boolean
     */
    public static boolean isValidFormat(String deadline) {
        if (deadline.equals(EMPTY_INPUT)) {
            return true;
        }
        return deadline.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if a deadline has a valid date.
     * @param deadline a deadline string to check
     * @return a boolean
     */
    public static boolean isValidDate(String deadline) {
        if (deadline.equals(EMPTY_INPUT)) {
            return true;
        }
        try {
            DateFormat df = new SimpleDateFormat("ddMMyy HH:mm");
            df.setLenient(false);
            df.parse(deadline);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Converts the deadline String to a LocalDateTime.
     * @param deadline a deadline string
     * @return a localdatetime instance
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
