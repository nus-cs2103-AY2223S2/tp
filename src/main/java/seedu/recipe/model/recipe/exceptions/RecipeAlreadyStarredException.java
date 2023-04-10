package seedu.recipe.model.recipe.exceptions;

/**
 * Signals that the recipe is already starred.
 */
public class RecipeAlreadyStarredException extends RuntimeException {
    public RecipeAlreadyStarredException() {
        super("The recipe is already starred.");
    }
}
