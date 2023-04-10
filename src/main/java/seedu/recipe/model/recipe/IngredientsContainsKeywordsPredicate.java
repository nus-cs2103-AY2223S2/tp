package seedu.recipe.model.recipe;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.recipe.commons.util.StringUtil;

/**
 * Tests that a {@code Recipe}'s {@code Ingredients} matches any of the keywords given.
 */
public class IngredientsContainsKeywordsPredicate extends ContainsKeywordsPredicate implements Predicate<Recipe> {
    private final List<String> keywords;

    /**
     * Checks that at least one ingredient contains the keywords
     *
     * @param keywords list of keywords
     */
    public IngredientsContainsKeywordsPredicate(List<String> keywords) {
        super();
        this.keywords = keywords;
    }

    @Override
    public boolean test(Recipe recipe) {
        Set<Ingredient> recipeIngredient = recipe.getIngredients();
        for (Ingredient i : recipeIngredient) {
            if (keywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(i.name, keyword))) {
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IngredientsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((IngredientsContainsKeywordsPredicate) other).keywords)); // state check
    }

}
