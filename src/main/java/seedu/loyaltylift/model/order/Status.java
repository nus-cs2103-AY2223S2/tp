package seedu.loyaltylift.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.commons.util.AppUtil.checkArgument;
/**
 * Represents an Order's status in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(StatusValue)}
 */
public class Status {

    public static final String MESSAGE_CONSTRAINTS =
            "Status can be any one of the predetermined values as defined in StatusValues, and it should not be null";

    public final StatusValue value;

    /**
     * Constructs a {@code Status}.
     *
     * @param status A valid status.
     */
    public Status(StatusValue status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        value = status;
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(StatusValue test) {
        return test != null;
    }

    @Override
    public String toString() {
        return value.toString();
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
