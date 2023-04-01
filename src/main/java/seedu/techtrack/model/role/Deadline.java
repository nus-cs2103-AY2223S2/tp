package seedu.techtrack.model.role;

import static java.util.Objects.requireNonNull;
import static seedu.techtrack.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


/**
 * Represents a Dateline in the Tech track.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline {
    public static final String VALIDATION_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
    private static String messageConstraint = "";
    public final String deadline;

    /**
     * Constructs a {@code deadline}.
     *
     * @param deadline A valid date.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), getMessageConstraint());
        this.deadline = deadline;
    }

    /**
     * Returns true if a given string is a valid dateline.
     */
    public static boolean isValidDeadline(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            LocalDate lDate = LocalDate.parse(test);
            return true;
        } else {
            setMessageConstraint("Deadline should be in this format: {YYYY-MM-DD}");
            return false;
        }
    }

    /**
     * Returns true if a given string is a dateline that have not passed the current date.
     */
    public static boolean isNotPassed(String test) {
        LocalDate lDate = LocalDate.parse(test);
        if (lDate.isAfter(LocalDate.now()) || lDate.isEqual(LocalDate.now())) {
            return true;
        } else {
            setMessageConstraint("Deadline should not have passed. Please enter a date that has not passed.");
            return false;
        }
    }

    /**
     * Returns true if a given string is a dateline that does not exist.
     */
    public static boolean doesNotExist(String test) {
        try {
            LocalDate lDate = LocalDate.parse(test);
        } catch (DateTimeParseException e) {
            setMessageConstraint("Deadline does not exist. Please key in a date that exists.");
            return false;
        }
        return true;
    }

    /**
     * Sets the message constraint of deadline.
     *
     *  @param msg message constraint of the given deadline.
     */
    public static void setMessageConstraint(String msg) {
        messageConstraint = msg;
    }

    /**
     * Gets the message constraint of deadline.
     *
     * @return messageConstraint returns the message constraint of the given deadline.
     */
    public static String getMessageConstraint() {
        return messageConstraint;
    }

    @Override
    public String toString() {
        return deadline;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && deadline.equals(((Deadline) other).deadline)); // state check
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }
}
