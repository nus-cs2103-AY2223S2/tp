package seedu.recipe.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.UniqueRecipeList;

/**
 * Wraps all data at the recipe-book level
 * Duplicates are not allowed (by .isSameRecipe comparison)
 */
public class RecipeBook implements ReadOnlyRecipeBook {

    private final UniqueRecipeList recipes;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        recipes = new UniqueRecipeList();
    }

    public RecipeBook() {}

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
     * Stars {@code recipe} in the {@code RecipeBook}.
     * {@code recipe} must exist in the recipe book.
     */
    public void starRecipe(Recipe recipe) {
        recipes.star(recipe);
    }

    /**
     * Unstars {@code recipe} in the {@code RecipeBook}.
     * {@code recipe} must exist in the recipe book.
     */
    public void unstarRecipe(Recipe recipe) {
        recipes.unstar(recipe);
    }


    //// util methods

    @Override
    public String toString() {
        return recipes.asUnmodifiableObservableList().size() + " recipes";
        // TODO: refine later
    }

    @Override
    public ObservableList<Recipe> getRecipeList() {
        return recipes.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecipeBook // instanceof handles nulls
                && recipes.equals(((RecipeBook) other).recipes));
    }

    @Override
    public int hashCode() {
        return recipes.hashCode();
    }
}

