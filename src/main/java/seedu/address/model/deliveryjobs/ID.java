package seedu.address.model.deliveryjobs;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Delivery's ID in the delivery jobs book.
 * Guarantees: immutable; is valid as declared in {@link #isValidID(String)}
 */
public class ID {

    public static final String MESSAGE_CONSTRAINTS = "ID should only contain number, and it should not be blank";

    public static final String VALIDATION_REGEX = "\\d";

    public final String value;

    /**
     * Constructs an {@code ID}.
     *
     * @param id A valid ID.
     */
    public ID(String id) {
        requireNonNull(id);
        checkArgument(isValidID(id), MESSAGE_CONSTRAINTS);
        value = id;
    }

    /**
     * Returns true if a given string is a valid ID.
     */
    public static boolean isValidID(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.deliveryjobs.ID // instanceof handles nulls
                && value.equals(((seedu.address.model.deliveryjobs.ID) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
