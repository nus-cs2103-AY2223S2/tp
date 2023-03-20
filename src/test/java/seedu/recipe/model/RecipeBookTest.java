package seedu.recipe.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_DESC_SOUP;
import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalRecipes.CORNDOGS;
import static seedu.recipe.testutil.TypicalRecipes.getTypicalRecipeBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.exceptions.DuplicateRecipeException;
import seedu.recipe.testutil.RecipeBuilder;

public class RecipeBookTest {

    private final RecipeBook recipeBook = new RecipeBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), recipeBook.getRecipeList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recipeBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyRecipeBook_replacesData() {
        RecipeBook newData = getTypicalRecipeBook();
        recipeBook.resetData(newData);
        assertEquals(newData, recipeBook);
    }

    @Test
    public void resetData_withDuplicateRecipes_throwsDuplicateRecipeException() {
        // Two recipes with the same identity fields
        Recipe editedCorndogs = new RecipeBuilder(CORNDOGS).withDesc(VALID_DESC_SOUP).build();
        List<Recipe> newRecipes = Arrays.asList(CORNDOGS, editedCorndogs);
        RecipeBookStub newData = new RecipeBookStub(newRecipes);

        assertThrows(DuplicateRecipeException.class, () -> recipeBook.resetData(newData));
    }

    @Test
    public void hasRecipe_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recipeBook.hasRecipe(null));
    }

    @Test
    public void hasRecipe_personNotInRecipeBook_returnsFalse() {
        assertFalse(recipeBook.hasRecipe(CORNDOGS));
    }

    @Test
    public void hasRecipe_personInRecipeBook_returnsTrue() {
        recipeBook.addRecipe(CORNDOGS);
        assertTrue(recipeBook.hasRecipe(CORNDOGS));
    }

    @Test
    public void hasRecipe_personWithSameIdentityFieldsInRecipeBook_returnsTrue() {
        recipeBook.addRecipe(CORNDOGS);
        Recipe editedCorndogs = new RecipeBuilder(CORNDOGS).withDesc(VALID_DESC_SOUP).build();
        assertTrue(recipeBook.hasRecipe(editedCorndogs));
    }

    @Test
    public void getRecipeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> recipeBook.getRecipeList().remove(0));
    }

    /**
     * A stub ReadOnlyRecipeBook whose recipes list can violate interface constraints.
     */
    private static class RecipeBookStub implements ReadOnlyRecipeBook {
        private final ObservableList<Recipe> recipes = FXCollections.observableArrayList();

        RecipeBookStub(Collection<Recipe> recipes) {
            this.recipes.setAll(recipes);
        }

        @Override
        public ObservableList<Recipe> getRecipeList() {
            return recipes;
        }
    }

}
