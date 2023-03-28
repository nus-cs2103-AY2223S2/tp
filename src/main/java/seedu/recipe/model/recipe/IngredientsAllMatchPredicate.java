package seedu.recipe.model.recipe;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.recipe.commons.util.StringUtil;

/**
 * Tests that a {@code Recipe}'s {@code Ingredient} matches any of the keywords given.
 */
public class IngredientsAllMatchPredicate implements Predicate<Recipe> {
    private final List<String> keywords;

    public IngredientsAllMatchPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Recipe recipe) {
        Set<Ingredient> recipeIngredients = recipe.getIngredients();
        for (Ingredient i : recipeIngredients) {
            if (keywords.stream().noneMatch(keyword -> StringUtil.containsWordIgnoreCase(i.ingredient, keyword))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IngredientsAllMatchPredicate // instanceof handles nulls
                && keywords.equals(((IngredientsAllMatchPredicate) other).keywords)); // state check
    }

}
