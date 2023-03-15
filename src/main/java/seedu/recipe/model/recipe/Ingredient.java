package seedu.recipe.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.commons.util.AppUtil.checkArgument;

/**
 * Represents an Ingredient in a list of ingredients in a recipe.
 * Guarantees: immutable; is valid as declared in {@link #isValidIngredient(String)}
 */
public class Ingredient {

    public static final String MESSAGE_CONSTRAINTS =
            "An ingredient should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String ingredient;

    /**
     * Constructs an {@code Ingredient}.
     *
     * @param ingredient a valid ingredient.
     */
    public Ingredient(String ingredient) {
        requireNonNull(ingredient);
        checkArgument(isValidIngredient(ingredient), MESSAGE_CONSTRAINTS);
        this.ingredient = ingredient;
    }

    /**
     * Returns true if a given string is a valid ingredient.
     */
    public static boolean isValidIngredient(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return this.ingredient;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Ingredient // instanceof handles nulls
                && this.ingredient.equals(((Ingredient) other).ingredient)); // state check
    }

    @Override
    public int hashCode() {
        return this.ingredient.hashCode();
    }

}
