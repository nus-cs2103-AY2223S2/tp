package seedu.sprint.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.sprint.commons.util.AppUtil.checkArgument;

/**
 * Represents a status for an Application in the internship book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}.
 */
public class Status {
    /**
     * Represents permitted values for the Status parameter.
     */
    public enum StatusType {
        INTERESTED, APPLIED, OFFERED, REJECTED;

        @Override
        public String toString() {
            return super.toString().charAt(0) + super.toString().substring(1).toLowerCase();
        }
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Statuses should only be one of the following: interested, applied, offered or rejected, "
                    + "and it should not be left blank.";

    public final StatusType value;

    /**
     * Constructs a {@code Status}.
     *
     * @param status A status inputted by user.
     */
    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        value = StatusType.valueOf(status.toUpperCase());
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String test) {
        for (StatusType s : StatusType.values()) {
            if (s.name().equalsIgnoreCase(test)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return value.name().charAt(0) + value.name().substring(1).toLowerCase();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && value.equals(((Status) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
