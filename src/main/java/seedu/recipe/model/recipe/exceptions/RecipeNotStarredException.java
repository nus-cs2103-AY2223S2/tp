package seedu.recipe.model.recipe.exceptions;

/**
 * Signals that the recipe is not starred.
 */
public class RecipeNotStarredException extends RuntimeException {
    public RecipeNotStarredException() {
        super("The recipe is not starred.");
    }
}
