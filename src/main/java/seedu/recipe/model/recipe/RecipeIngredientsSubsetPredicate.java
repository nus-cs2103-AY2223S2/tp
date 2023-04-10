package seedu.recipe.model.recipe;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.recipe.commons.util.StringUtil;

/**
 * Tests that a {@code Recipe}'s {@code Recipe} matches any of the keywords given.
 */
public class RecipeIngredientsSubsetPredicate extends ContainsKeywordsPredicate implements Predicate<Recipe> {
    private final List<String> keywords;

    /**
     * Checks that the ingredients is a subset of all the keywords.
     *
     * @param keywords list of keywords
     */
    public RecipeIngredientsSubsetPredicate(List<String> keywords) {
        super();
        this.keywords = keywords;
    }

    @Override
    public boolean test(Recipe recipe) {
        Set<Ingredient> recipeIngredients = recipe.getIngredients();
        for (Ingredient i : recipeIngredients) {
            if (keywords.stream().noneMatch(keyword -> StringUtil.containsWordIgnoreCase(i.name, keyword))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecipeIngredientsSubsetPredicate // instanceof handles nulls
                && keywords.equals(((RecipeIngredientsSubsetPredicate) other).keywords)); // state check
    }

}
