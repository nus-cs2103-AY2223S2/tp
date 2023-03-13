package seedu.address.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Recipe's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Ingredient {


    public static final String MESSAGE_CONSTRAINTS =
            "Ingredient numbers should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code Ingredient}.
     *
     * @param ingredient A valid ingredient number.
     */
    public Ingredient(String ingredient) {
        requireNonNull(ingredient);
        checkArgument(isValidPhone(ingredient), MESSAGE_CONSTRAINTS);
        value = ingredient;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Ingredient // instanceof handles nulls
                && value.equals(((Ingredient) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
