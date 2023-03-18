package seedu.recipe.model;

import javafx.collections.ObservableList;
import seedu.recipe.model.recipe.Recipe;

/**
 * Unmodifiable view of a recipe book
 */
public interface ReadOnlyRecipeBook {

    /**
     * Returns an unmodifiable view of the recipes list.
     * This list will not contain any duplicate recipes.
     */
    ObservableList<Recipe> getRecipeList();

}
