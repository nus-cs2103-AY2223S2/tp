package seedu.recipe.model.recipe.exceptions;

public class RecipePortionNotPresentException extends RuntimeException {
    public RecipePortionNotPresentException() {
        super("A Recipe Portion was not specified for this recipe - please provide one");
    }
}
