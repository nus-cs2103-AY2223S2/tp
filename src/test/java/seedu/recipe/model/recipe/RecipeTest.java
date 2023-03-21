package seedu.recipe.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_DESC_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_INGREDIENTS_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_STEPS_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_TITLE_SOUP;

import static seedu.recipe.testutil.TypicalRecipes.CORNDOGS;
import static seedu.recipe.testutil.TypicalRecipes.SOUP;


import org.junit.jupiter.api.Test;

import seedu.recipe.testutil.RecipeBuilder;

public class RecipeTest {

    @Test
    public void isSameRecipe() {
        // same object -> returns true
        assertTrue(CORNDOGS.isSameRecipe(CORNDOGS));

        // null -> returns false
        assertFalse(CORNDOGS.isSameRecipe(null));

        // same name, all other attributes different -> returns true
        Recipe editedCorndogs = new RecipeBuilder(CORNDOGS).withDesc(VALID_DESC_SOUP).withIngredients(VALID_INGREDIENTS_SOUP)
                .withSteps(VALID_STEPS_SOUP).build();
        assertTrue(CORNDOGS.isSameRecipe(editedCorndogs));

        // different name, all other attributes same -> returns false
        editedCorndogs = new RecipeBuilder(CORNDOGS).withTitle(VALID_TITLE_SOUP).build();
        assertFalse(CORNDOGS.isSameRecipe(editedCorndogs));

        // name differs in case, all other attributes same -> returns false
        Recipe editedSoup = new RecipeBuilder(SOUP).withTitle(VALID_TITLE_SOUP.toLowerCase()).build();
        assertFalse(SOUP.isSameRecipe(editedSoup));

        // name has trailing spaces, all other attributes same -> returns false
        String titleWithTrailingSpaces = VALID_TITLE_SOUP + " ";
        editedSoup = new RecipeBuilder(SOUP).withTitle(titleWithTrailingSpaces).build();
        assertFalse(SOUP.isSameRecipe(editedSoup));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Recipe aliceCopy = new RecipeBuilder(CORNDOGS).build();
        assertTrue(CORNDOGS.equals(aliceCopy));

        // same object -> returns true
        assertTrue(CORNDOGS.equals(CORNDOGS));

        // null -> returns false
        assertFalse(CORNDOGS.equals(null));

        // different type -> returns false
        assertFalse(CORNDOGS.equals(5));

        // different recipe-> returns false
        assertFalse(CORNDOGS.equals(SOUP));

        // different name -> returns false
        Recipe editedCorndogs = new RecipeBuilder(CORNDOGS).withTitle(VALID_TITLE_SOUP).build();
        assertFalse(CORNDOGS.equals(editedCorndogs));

        // different phone -> returns false
        editedCorndogs = new RecipeBuilder(CORNDOGS).withDesc(VALID_DESC_SOUP).build();
        assertFalse(CORNDOGS.equals(editedCorndogs));

        // different email -> returns false
        editedCorndogs = new RecipeBuilder(CORNDOGS).withIngredients(VALID_INGREDIENTS_SOUP).build();
        assertFalse(CORNDOGS.equals(editedCorndogs));

        // different address -> returns false
        editedCorndogs = new RecipeBuilder(CORNDOGS).withSteps(VALID_STEPS_SOUP).build();
        assertFalse(CORNDOGS.equals(editedCorndogs));

    }
}