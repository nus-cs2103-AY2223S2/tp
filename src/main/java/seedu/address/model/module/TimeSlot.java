package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's timeSlot in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimeSlot(String)}
 */
public class TimeSlot {

    public static final String MESSAGE_CONSTRAINTS =
            "Module type can be Lecture/Tutorial/Lab/Assignment/Project/Exam/Quiz";
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code TimeSlot}.
     *
     * @param timeSlot A valid timeSlot address.
     */
    public TimeSlot(String timeSlot) {
        requireNonNull(timeSlot);
        checkArgument(isValidTimeSlot(timeSlot), MESSAGE_CONSTRAINTS);
        value = timeSlot;
    }

    /**
     * Returns if a given string is a valid timeSlot.
     */
    public static boolean isValidTimeSlot(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TimeSlot // instanceof handles nulls
                && value.equals(((TimeSlot) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
