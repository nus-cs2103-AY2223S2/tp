package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's telegram handle in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidHandle(String)}
 */
public class Telegram {

    public static final String MESSAGE_CONSTRAINTS =
            "Telegram handle can take any valid Telegram handle, and it should not be blank";

    /*
     * Matches a valid telegram handle. Currently, asserts (or tries to) the following constraints:
     *   Begins with an alphabetic character
     *   Has a minimum length of 5 and a maximum length of 32
     *   Consists of only alphanumeric characters and underscores
     *   Does not have consecutive underscores
     *   Does not end on an underscore.
     */
    public static final String VALIDATION_REGEX = "^@(?=[a-zA-Z]\\w{4,31}\\b)[a-zA-Z0-9]+(?:_[a-zA-Z0-9]+)*$";

    public final String value;

    /**
     * Constructs a {@code Telegram}.
     *
     * @param telegram A valid telegram handle.
     */
    public Telegram(String telegram) {
        requireNonNull(telegram);
        checkArgument(isValidHandle(telegram), MESSAGE_CONSTRAINTS);
        value = telegram;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidHandle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && value.equals(((Telegram) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
