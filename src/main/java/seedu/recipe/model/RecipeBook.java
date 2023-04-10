package seedu.recipe.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.UniqueRecipeList;
import seedu.recipe.model.recipe.ingredient.IngredientBuilder;
import seedu.recipe.model.util.SubstitutionsUtil;

/**
 * Wraps all data at the recipe-book level
 * Duplicates are not allowed (by .isSameRecipe comparison)
 */
public class RecipeBook implements ReadOnlyRecipeBook {

    private final UniqueRecipeList recipes;
    private final List<IngredientBuilder> substitutes;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */

    {
        recipes = new UniqueRecipeList();
        substitutes = SubstitutionsUtil.getPreloadedSubstitutions();
    }

    public RecipeBook() {
    }

    /**
     * Creates a RecipeBook using the Recipes in the {@code toBeCopied}
     */
    public RecipeBook(ReadOnlyRecipeBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the recipe list with {@code recipes}.
     * {@code recipes} must not contain duplicate recipes.
     */
    public void setRecipes(List<Recipe> recipes) {
        this.recipes.setRecipes(recipes);
    }

    /**
     * Resets the existing data of this {@code RecipeBook} with {@code newData}.
     */
    public void resetData(ReadOnlyRecipeBook newData) {
        requireNonNull(newData);

        setRecipes(newData.getRecipeList());
    }

    //// recipe-level operations

    /**
     * Returns true if a recipe with the same identity as {@code recipe} exists in the recipe book.
     */
    public boolean hasRecipe(Recipe recipe) {
        requireNonNull(recipe);
        return recipes.contains(recipe);
    }

    /**
     * Adds a recipe to the recipe book.
     * The recipe must not already exist in the recipe book.
     */
    public void addRecipe(Recipe p) {
        recipes.add(p);
    }

    /**
     * Adds a list of recipes to the recipe book.
     * The recipes must not already exist in the recipe book.
     */
    public void addRecipes(ObservableList<Recipe> recipeList) {
        for (Recipe recipe : recipeList) {
            if (!hasRecipe(recipe)) {
                recipes.add(recipe);
            }
        }
    }

    /**
     * Replaces the given recipe {@code target} in the list with {@code editedRecipe}.
     * {@code target} must exist in the recipe book.
     * The recipe identity of {@code editedRecipe} must not be the same as another existing recipe in the recipe book.
     */
    public void setRecipe(Recipe target, Recipe editedRecipe) {
        requireNonNull(editedRecipe);

        recipes.setRecipe(target, editedRecipe);
    }

    /**
     * Removes {@code key} from this {@code RecipeBook}.
     * {@code key} must exist in the recipe book.
     */
    public void removeRecipe(Recipe key) {
        recipes.remove(key);
    }


    /**
     * Returns the preloaded substitutes in this {@code RecipeBook}.
     */
    @Override
    public List<IngredientBuilder> getPreloadedSubstitutes() {
        return this.substitutes;
    }

    //// util methods

    @Override
    public String toString() {
        return recipes.asUnmodifiableObservableList().size() + " recipes";
    }

    @Override
    public ObservableList<Recipe> getRecipeList() {
        return recipes.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof RecipeBook)) {
            return false;
        }
        if (other == this) {
            return true;
        }
        ObservableList<Recipe> r = ((RecipeBook) other).getRecipeList();
        for (Recipe recipe : r) {
            if (!this.hasRecipe(recipe)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return recipes.hashCode();
    }
}
