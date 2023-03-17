package seedu.recipe.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.recipe.model.RecipeBook;
import seedu.recipe.model.recipe.Recipe;

/**
 * A utility class containing a list of {@code Recipe} objects to be used in tests.
 */
public class TypicalRecipes {

    public static final Recipe CORNDOGS = new RecipeBuilder().withTitle("Corndogs")
            .withDesc("Awesome cheap corndogs").withIngredients("eggs", "flour")
            .withSteps("Test1", "Test2").build();
    public static final Recipe SOUP = new RecipeBuilder().withTitle("Soup")
            .withDesc("Tasty delicious soupy goodness").withIngredients("water", "salt")
            .withSteps("Test1", "Test2").build();

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
