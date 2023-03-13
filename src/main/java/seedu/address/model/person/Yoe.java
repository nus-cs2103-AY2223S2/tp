package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Doctor's Years of Experience (YOE) in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidYoe(String)}
 */
public class Yoe {

    public static final String MESSAGE_CONSTRAINTS =
            "Years of Experience (YOE) should only contain numbers, and it should be at most 2 digits long";
    public static final String VALIDATION_REGEX = "\\d{1,2}";
    public final String value;

    /**
     * Constructs a {@code YOE}.
     *
     * @param yoe A valid number of years.
     */
    public Yoe(String yoe) {
        requireNonNull(yoe);
        checkArgument(isValidYoe(yoe), MESSAGE_CONSTRAINTS);
        value = yoe;
    }

    /**
     * Returns true if a given string is a valid years of experience.
     */
    public static boolean isValidYoe(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Yoe // instanceof handles nulls
                && value.equals(((Yoe) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
