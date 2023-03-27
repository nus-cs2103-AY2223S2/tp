package seedu.recipe.model.recipe;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.recipe.commons.util.StringUtil;

/**
 * Tests that a {@code Recipe}'s {@code Title} matches any of the keywords given.
 */
public class AnythingContainsKeywordsPredicate implements Predicate<Recipe> {
    private final List<String> keywords;

    public AnythingContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Recipe recipe) {
        boolean doesTitle = keywords.stream()
                .anyMatch(keyword -> StringUtil
                        .containsWordIgnoreCase(recipe.getTitle().title, keyword));
        Set<Ingredient> recipeIngredient = recipe.getIngredients();
        boolean doesIngredientExist = false;
        for (Ingredient i : recipeIngredient) {
            if (keywords.stream()
                    .anyMatch(keyword -> StringUtil
                            .containsWordIgnoreCase(i.ingredient, keyword))) {
                doesIngredientExist = true;
            }
        }
        return doesTitle || doesIngredientExist;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AnythingContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AnythingContainsKeywordsPredicate) other).keywords)); // state check
    }

}
