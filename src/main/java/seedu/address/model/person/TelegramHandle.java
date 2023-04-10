package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a User's telegram handle in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegramHandle(String)}
 */
public class TelegramHandle implements Comparable<TelegramHandle> {

    public static final String MESSAGE_CONSTRAINTS =
            "Telegram Handle should be at least 5 characters long start with @ symbol"
        + "\nAdditionally, it can contain any combination of letters, numbers and underscores only";

    /*
     * The first character of the station must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * The first character should be "@" followed by alphanumeric characters.
     */
    public static final String VALIDATION_REGEX = "^@[\\p{Alnum}]+";

    private final String value;

    /**
     * Constructs a {@code TelegramHandle}.
     *
     * @param telegramHandle A valid telegram handle.
     */
    public TelegramHandle(String telegramHandle) {
        requireNonNull(telegramHandle);
        checkArgument(isValidTelegramHandle(telegramHandle), MESSAGE_CONSTRAINTS);
        value = telegramHandle;
    }

    /**
     * Returns true if the telegram handle is valid.
     * @param test telegram handle
     * @return true if the telegram handle is valid.
     */
    public static boolean isValidTelegramHandle(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() >= 6;
    }

    /**
     * Gets the String value stored within the email.
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TelegramHandle // instanceof handles nulls
                && value.equals(((TelegramHandle) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(TelegramHandle otherTelegramHandle) {
        return value.compareTo(otherTelegramHandle.value);
    }
}
