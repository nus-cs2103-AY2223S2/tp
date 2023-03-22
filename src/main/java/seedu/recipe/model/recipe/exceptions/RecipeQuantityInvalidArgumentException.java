package seedu.recipe.model.recipe.exceptions;

import seedu.recipe.model.recipe.recipefield.RecipeIngredientQuantity;

public class RecipeQuantityInvalidArgumentException extends RuntimeException {
    public RecipeQuantityInvalidArgumentException(String s) {
        super(
            String.format(
                "An invalid quantity expression %s was passed for this ingredient. "
                + "Please follow this format: \n%s", s, RecipeIngredientQuantity.MESSAGE_CONSTRAINTS
            )
        );
    }
}
