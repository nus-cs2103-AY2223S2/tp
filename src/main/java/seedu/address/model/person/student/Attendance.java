package seedu.address.model.person.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the Attendance of a Student object.
 */
public class Attendance {
    public static final String MESSAGE_CONSTRAINTS = "Attendance must be T or F";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[TF]";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param attendance A valid student image file destination.
     */
    public Attendance(String attendance) {
        requireNonNull(attendance);
        checkArgument(isValidAttendance(attendance), MESSAGE_CONSTRAINTS);
        value = attendance;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidAttendance(String test) {
        if (test.equals("Insert student attendance here!")) {
            return true;
        }

        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is the default string given when the attendance isn't given by user.
     *
     * @param test String value to test.
     * @return Boolean value true if the string given is the default string by the system.
     */
    public static boolean isDefaultAttendance(String test) {
        if (test.equals("Insert student attendance here!")) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Attendance// instanceof handles nulls
                && value.equals(((Attendance) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
