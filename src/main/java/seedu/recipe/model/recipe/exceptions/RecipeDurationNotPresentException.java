package seedu.recipe.model.recipe.exceptions;

public class RecipeDurationNotPresentException extends RuntimeException {
    public RecipeDurationNotPresentException() {
        super("A recipe duration has not yet been specified for this recipe - Please populate one");
    }
}
