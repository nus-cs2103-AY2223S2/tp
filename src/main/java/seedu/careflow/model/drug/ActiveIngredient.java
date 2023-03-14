package seedu.careflow.model.drug;

import static java.util.Objects.requireNonNull;
import static seedu.careflow.commons.util.AppUtil.checkArgument;

/**
 * Represents a drug's active ingredient in the drug inventory
 */
public class ActiveIngredient {
    public static final String MESSAGE_CONSTRAINTS =
            "Active ingredient should only contain alphanumeric characters, spaces and dashes, and it should not be"
                    + " blank but less than 200 characters long";
    public static final String VALIDATION_REGEX = "[a-zA-Z0-9][a-zA-Z0-9 -]{0,200}";
    public final String value;

    /**
     * Constructs a {@code ActiveIngredient}.
     *
     * @param ingredient A valid active ingredient.
     */
    public ActiveIngredient(String ingredient) {
        requireNonNull(ingredient);
        checkArgument(isValidIngredient(ingredient), MESSAGE_CONSTRAINTS);
        value = ingredient;
    }

    /**
     * Returns true if a given string is a valid active ingredient.
     */
    public static boolean isValidIngredient(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ActiveIngredient
                && value.equals(((ActiveIngredient) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
