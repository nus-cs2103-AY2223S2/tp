package seedu.address.model.drug;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a drug's purpose in the drug inventory
 */
public class Purpose {
    public static final String MESSAGE_CONSTRAINTS = "Purposes should be in phrase form "
            + "and only contain alphabets or whitespace";

    /*
     * The first 3 consecutive characters are upper or lowercase alphabetical characters,
     * followed by 0 or more alphabetical or whitespace characters.
     */
    public static final String VALIDATION_REGEX = "[A-Za-z][A-Za-z\\s]*";

    public final String purpose;

    /**
     * Constructs an {@code Purpose}.
     *
     * @param purpose A valid purpose.
     */
    public Purpose(String purpose) {
        requireNonNull(purpose);
        checkArgument(isValidPurpose(purpose), MESSAGE_CONSTRAINTS);
        this.purpose = purpose;
    }

    /**
     * Returns true if a given string is a valid purpose.
     */
    public static boolean isValidPurpose(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return purpose;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Purpose // instanceof handles nulls
                && purpose.equals(((Purpose) other).purpose)); // state check
    }

    @Override
    public int hashCode() {
        return purpose.hashCode();
    }
}
