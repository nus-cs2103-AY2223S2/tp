package seedu.ultron.model.opening;

import static java.util.Objects.requireNonNull;
import static seedu.ultron.commons.util.AppUtil.checkArgument;

/**
 * Represents an Opening's status in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public class Status {
    public static final String MESSAGE_CONSTRAINTS = "Statuses are case-insensitive and must match "
            + "only one of the predefined values.\n"
            + "Predefined values: FOUND, APPLIED, INTERVIEWING, OFFERED, REJECTED, ACCEPTED";

    /**
     * The enum of all possible statuses.
     */
    public static enum StatusEnum {
        FOUND, APPLIED, INTERVIEWING, OFFERED, REJECTED, ACCEPTED
    }

    public final String fullStatus;

    /**
     * Constructs a {@code Status}.
     *
     * @param status A valid status.
     */
    public Status(String status) {
        requireNonNull(status);
        status = status.toUpperCase();
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        fullStatus = status.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String test) {
        for (StatusEnum status : StatusEnum.values()) {
            if (status.name().equals(test.toUpperCase())) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return fullStatus;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && fullStatus.equals(((Status) other).fullStatus)); // state check
    }

    @Override
    public int hashCode() {
        return fullStatus.hashCode();
    }
}
