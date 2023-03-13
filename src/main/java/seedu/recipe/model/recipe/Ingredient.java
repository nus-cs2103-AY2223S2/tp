package seedu.recipe.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.commons.util.AppUtil.checkArgument;

/**
 * Represents a recipe's ingredient in the recipe book.
 */
public class Ingredient {

    public static final String MESSAGE_CONSTRAINTS =
            "Ingredient contain alphanumeric characters and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alpha}][\\p{Alpha} ]*";
    public final String value;

    /**
     * Constructs a {@code Ingredient}.
     *
     * @param ingredient A valid ingredient number.
     */
    public Ingredient(String ingredient) {
        requireNonNull(ingredient);
        checkArgument(isValidIngredient(ingredient), MESSAGE_CONSTRAINTS);
        value = ingredient;
    }

    /**
     * Returns true if a given string is a valid phone number.
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
        return other == this // short circuit if same object
                || (other instanceof Ingredient // instanceof handles nulls
                && value.equals(((Ingredient) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
