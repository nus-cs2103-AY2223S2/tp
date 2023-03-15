package seedu.recipe.model.recipe.exceptions;

public class RecipeIngredientUnitMissingException extends RuntimeException {
    public RecipeIngredientUnitMissingException() {
        super("A measurement unit for this recipe ingredient was not provided. Please set one first");
    }
}
