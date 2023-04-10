package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's value in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender {

    public static final String MESSAGE_CONSTRAINTS =
            "Gender should only be male or female, and it should not be blank";

    /* The input should be "male" or "female" */
    public static final String VALIDATION_REGEX = ".*\\bmale\\b|.*\\bfemale\\b";
    public static final String SYMBOL_MALE = "(M)";
    public static final String SYMBOL_FEMALE = "(F)";

    public final String value;
    public final String symbol;

    /**
     * Constructs a {@code Gender}.
     *
     * @param gender A valid gender type.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        this.value = gender;
        this.symbol = gender.equals("male") ? SYMBOL_MALE : SYMBOL_FEMALE;
    }

    /**
     * Returns true if a given string is a valid value.
     */
    public static boolean isValidGender(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                && value.equals(((Gender) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
