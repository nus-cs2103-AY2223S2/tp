package seedu.recipe.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.testutil.TypicalRecipes.BLUEBERRY_PANCAKES;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_E_PEPE;
import static seedu.recipe.testutil.TypicalRecipes.FISH_AND_CHIPS;
import static seedu.recipe.testutil.TypicalRecipes.GRILLED_CHEESE;
import static seedu.recipe.testutil.TypicalRecipes.MASALA_DOSA;

import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.recipe.model.ReadOnlyRecipeBook;
import seedu.recipe.model.RecipeBook;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.tag.Tag;

public class SampleDataUtilTest {
    @Test
    public void getSampleRecipes() {
        //As the individual classes may have different ::equals
        Recipe[] expected = new Recipe[]{
            CACIO_E_PEPE, BLUEBERRY_PANCAKES, FISH_AND_CHIPS, GRILLED_CHEESE, MASALA_DOSA };
        Recipe[] actual = SampleDataUtil.getSampleRecipes();
        for (int i = 0; i < actual.length; i++) {
            assertTrue(expected[i].isSameRecipe(actual[i]));
        }
    }

    @Test
    public void getRecipeBook() {
        RecipeBook book = new RecipeBook();
        book.addRecipe(CACIO_E_PEPE);
        book.addRecipe(BLUEBERRY_PANCAKES);
        book.addRecipe(FISH_AND_CHIPS);
        book.addRecipe(GRILLED_CHEESE);
        book.addRecipe(MASALA_DOSA);

        ObservableList<Recipe> expected = book.getRecipeList();
        ReadOnlyRecipeBook r = SampleDataUtil.getSampleRecipeBook();
        ObservableList<Recipe> list = r.getRecipeList();
        for (int i = 0; i < list.size(); i++) {
            assertTrue(list.get(i).isSameRecipe(expected.get(i)));
        }
    }

    @Test
    public void getTagSet() {
        String[] tags = new String[]{"Tag 1", "Tag 2", "Tag 3", "Tag 4", "Tag 4"};
        Set<Tag> expected = Set.of(
                new Tag("Tag 1"),
                new Tag("Tag 2"),
                new Tag("Tag 3"),
                new Tag("Tag 4")
        );
        assertEquals(expected, SampleDataUtil.getTagSet(tags));
    }
}
