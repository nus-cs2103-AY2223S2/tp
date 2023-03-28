package seedu.recipe.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_DESC_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_INGREDIENTS_SOUP;
import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalRecipes.CORNDOGS;
import static seedu.recipe.testutil.TypicalRecipes.SOUP;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.recipe.model.recipe.exceptions.DuplicateRecipeException;
import seedu.recipe.model.recipe.exceptions.RecipeNotFoundException;
import seedu.recipe.testutil.RecipeBuilder;

public class UniqueRecipeListTest {

    private final UniqueRecipeList uniqueRecipeList = new UniqueRecipeList();

    @Test
    public void contains_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.contains(null));
    }

    @Test
    public void contains_recipeNotInList_returnsFalse() {
        assertFalse(uniqueRecipeList.contains(CORNDOGS));
    }

    @Test
    public void contains_recipeInList_returnsTrue() {
        uniqueRecipeList.add(CORNDOGS);
        assertTrue(uniqueRecipeList.contains(CORNDOGS));
    }

    @Test
    public void contains_recipeWithSameIdentityFieldsInList_returnsTrue() {
        uniqueRecipeList.add(CORNDOGS);
        Recipe editedAlice = new RecipeBuilder(CORNDOGS).withIngredients(VALID_INGREDIENTS_SOUP).build();
        assertTrue(uniqueRecipeList.contains(editedAlice));
    }

    @Test
    public void add_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.add(null));
    }

    @Test
    public void add_duplicateRecipe_throwsDuplicateRecipeException() {
        uniqueRecipeList.add(CORNDOGS);
        assertThrows(DuplicateRecipeException.class, () -> uniqueRecipeList.add(CORNDOGS));
    }

    @Test
    public void setRecipe_nullTargetRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.setRecipe(null, CORNDOGS));
    }

    @Test
    public void setRecipe_nullEditedRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.setRecipe(CORNDOGS, null));
    }

    @Test
    public void setRecipe_targetRecipeNotInList_throwsRecipeNotFoundException() {
        assertThrows(RecipeNotFoundException.class, () -> uniqueRecipeList.setRecipe(CORNDOGS, CORNDOGS));
    }

    @Test
    public void setRecipe_editedRecipeIsSameRecipe_success() {
        uniqueRecipeList.add(CORNDOGS);
        uniqueRecipeList.setRecipe(CORNDOGS, CORNDOGS);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        expectedUniqueRecipeList.add(CORNDOGS);
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);
    }

    @Test
    public void setRecipe_editedRecipeHasSameIdentity_success() {
        uniqueRecipeList.add(CORNDOGS);
        Recipe editedAlice = new RecipeBuilder(CORNDOGS).withDesc(VALID_DESC_SOUP).build();
        uniqueRecipeList.setRecipe(CORNDOGS, editedAlice);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        expectedUniqueRecipeList.add(editedAlice);
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);
    }

    @Test
    public void setRecipe_editedRecipeHasDifferentIdentity_success() {
        uniqueRecipeList.add(CORNDOGS);
        uniqueRecipeList.setRecipe(CORNDOGS, SOUP);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        expectedUniqueRecipeList.add(SOUP);
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);
    }

    @Test
    public void setRecipe_editedRecipeHasNonUniqueIdentity_throwsDuplicateRecipeException() {
        uniqueRecipeList.add(CORNDOGS);
        uniqueRecipeList.add(SOUP);
        assertThrows(DuplicateRecipeException.class, () -> uniqueRecipeList.setRecipe(CORNDOGS, SOUP));
    }

    @Test
    public void remove_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.remove(null));
    }

    @Test
    public void remove_recipeDoesNotExist_throwsRecipeNotFoundException() {
        assertThrows(RecipeNotFoundException.class, () -> uniqueRecipeList.remove(CORNDOGS));
    }

    @Test
    public void remove_existingRecipe_removesRecipe() {
        uniqueRecipeList.add(CORNDOGS);
        uniqueRecipeList.remove(CORNDOGS);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);
    }

    @Test
    public void setRecipes_nullUniqueRecipeList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.setRecipes((UniqueRecipeList) null));
    }

    @Test
    public void setRecipes_uniqueRecipeList_replacesOwnListWithProvidedUniqueRecipeList() {
        uniqueRecipeList.add(CORNDOGS);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        expectedUniqueRecipeList.add(SOUP);
        uniqueRecipeList.setRecipes(expectedUniqueRecipeList);
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);
    }

    @Test
    public void setRecipes_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.setRecipes((List<Recipe>) null));
    }

    @Test
    public void setRecipes_list_replacesOwnListWithProvidedList() {
        uniqueRecipeList.add(CORNDOGS);
        List<Recipe> recipeList = Collections.singletonList(SOUP);
        uniqueRecipeList.setRecipes(recipeList);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        expectedUniqueRecipeList.add(SOUP);
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);
    }

    @Test
    public void setRecipes_listWithDuplicateRecipes_throwsDuplicateRecipeException() {
        List<Recipe> listWithDuplicateRecipes = Arrays.asList(CORNDOGS, CORNDOGS);
        assertThrows(DuplicateRecipeException.class, () -> uniqueRecipeList.setRecipes(listWithDuplicateRecipes));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueRecipeList.asUnmodifiableObservableList().remove(0));
    }
}
