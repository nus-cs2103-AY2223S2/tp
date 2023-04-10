package seedu.recipe.testutil;

import static seedu.recipe.logic.commands.CommandTestUtil.VALID_DESC_CORNDOGS;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_DESC_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_INGREDIENTS_CORNDOGS;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_INGREDIENTS_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_STEPS_CORNDOGS;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_STEPS_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_TAGS_CORNDOGS;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_TAGS_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_TITLE_CORNDOGS;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_TITLE_SOUP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.recipe.model.RecipeBook;
import seedu.recipe.model.recipe.Recipe;




/**
 * A utility class containing a list of {@code Recipe} objects to be used in tests.
 */
public class TypicalRecipes {

    public static final Recipe CORNDOGS = new RecipeBuilder().withTitle(VALID_TITLE_CORNDOGS)
            .withDesc(VALID_DESC_CORNDOGS).withIngredients(VALID_INGREDIENTS_CORNDOGS)
            .withSteps(VALID_STEPS_CORNDOGS).withTags(VALID_TAGS_CORNDOGS).build();
    public static final Recipe SOUP = new RecipeBuilder().withTitle(VALID_TITLE_SOUP)
            .withDesc(VALID_DESC_SOUP).withIngredients(VALID_INGREDIENTS_SOUP)
            .withSteps(VALID_STEPS_SOUP).withTags(VALID_TAGS_SOUP).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalRecipes() {} // prevents instantiation

    /**
     * Returns an {@code RecipeBook} with all the typical recipes.
     */
    public static RecipeBook getTypicalRecipeBook() {
        RecipeBook ab = new RecipeBook();
        for (Recipe recipe : getTypicalRecipes()) {
            ab.addRecipe(recipe);
        }
        return ab;
    }

    public static List<Recipe> getTypicalRecipes() {
        return new ArrayList<>(Arrays.asList(CORNDOGS, SOUP));
    }
}
