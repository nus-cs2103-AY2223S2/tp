package seedu.recipe.model;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.ingredient.IngredientBuilder;



/**
 * Unmodifiable view of a recipe book
 */
public interface ReadOnlyRecipeBook {

    /**
     * Returns an unmodifiable view of the recipes list.
     * This list will not contain any duplicate recipes.
     */
    ObservableList<Recipe> getRecipeList();

    /**
     * Returns the preloaded substitutions list.
     *
     */
    List<IngredientBuilder> getPreloadedSubstitutes();

}
