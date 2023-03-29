package seedu.recipe.model.recipe;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.recipe.commons.util.StringUtil;

/**
 * Tests that a {@code Recipe}'s {@code Recipe} matches any of the keywords given.
 */
public class RecipeContainsKeywordsPredicate extends ContainsKeywordsPredicate implements Predicate<Recipe> {
    private final List<String> keywords;

    /**
     * Checks that at least one recipe contains the keyword.
     *
     * @param keywords the list of keywords
     */
    public RecipeContainsKeywordsPredicate(List<String> keywords) {
        super();
        this.keywords = keywords;
    }

    @Override
    public boolean test(Recipe recipe) {
        List<Step> recipeStep = recipe.getSteps();
        boolean doesStepExist = false;
        for (Step s : recipeStep) {
            if (keywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(s.step, keyword))) {
                doesStepExist = true;
            }
        }
        Set<Ingredient> recipeIngredient = recipe.getIngredients();
        boolean doesIngredientExist = false;
        for (Ingredient i : recipeIngredient) {
            if (keywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(i.name, keyword))) {
                doesIngredientExist = true;
            }
        }
        boolean doesDescExist = keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(recipe.getDesc().description, keyword));
        boolean doesTitleExist = keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(recipe.getTitle().title, keyword));
        return doesTitleExist || doesDescExist || doesIngredientExist || doesStepExist;

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecipeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((RecipeContainsKeywordsPredicate) other).keywords)); // state check
    }

}
