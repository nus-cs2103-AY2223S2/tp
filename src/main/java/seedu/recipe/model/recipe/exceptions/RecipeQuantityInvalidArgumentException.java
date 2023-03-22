package seedu.recipe.model.recipe.exceptions;

import seedu.recipe.model.recipe.field.RecipeIngredientQuantity;

/**
 * Represents the exception that arises when a parameter string not containing a portion is passed into the
 * factory method {@code ::of} of the RecipeIngredientQuantity class.
 */
public class RecipeQuantityInvalidArgumentException extends RuntimeException {
    /**
     * Generates and returns an instance of this Exception, with the provided argument string.
     * @param s The invalid argument that was entered into the RecipeIngredientQuantity {@code ::of} method.
     */
    public RecipeQuantityInvalidArgumentException(String s) {
        super(
            String.format(
                "An invalid quantity expression %s was passed for this ingredient. "
                + "Please follow this format: \n%s", s, RecipeIngredientQuantity.MESSAGE_CONSTRAINTS
            )
        );
    }
}
