package seedu.recipe.model.util;

import seedu.recipe.model.ReadOnlyRecipeBook;
import seedu.recipe.model.RecipeBook;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.RecipeDuration;
import seedu.recipe.model.recipe.RecipePortion;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.tag.Tag;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Contains utility methods for populating {@code RecipeBook} with sample data.
 */
public class SampleDataUtil {
    public static Recipe[] getSampleRecipes() {
        Recipe cacioEPepePasta = new Recipe(new Name("Cacio E Pepe Pasta"));
        cacioEPepePasta.setDuration(RecipeDuration.of("15 minutes"));
        cacioEPepePasta.setPortion(RecipePortion.of("1 - 3 person"));
        cacioEPepePasta.setIngredients(
                new Ingredient("2 whole eggs"),
                new Ingredient("100g spaghetti noodles")
                                      );
        cacioEPepePasta.setSteps(
                new Step("Crack the egg and separate the yolks and whites"),
                new Step("Raise a pot of water to the boil and add the spaghetti")
                                );

        return new Recipe[]{
                cacioEPepePasta
        };
    }

    public static ReadOnlyRecipeBook getSampleRecipeBook() {
        RecipeBook sampleAb = new RecipeBook();
        for (Recipe sampleRecipe : getSampleRecipes()) {
            sampleAb.addRecipe(sampleRecipe);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
