package seedu.recipe.model.recipe;

import java.util.List;
import java.util.function.Predicate;

import seedu.recipe.commons.util.StringUtil;

/**
 * Tests that a {@code Recipe}'s {@code Steps} matches any of the keywords given.
 */
public class StepsContainsKeywordsPredicate extends ContainsKeywordsPredicate implements Predicate<Recipe> {
    private final List<String> keywords;

    /**
     * Checks that at least one step contains the keywords.
     *
     * @param keywords a list of keywords
     */
    public StepsContainsKeywordsPredicate(List<String> keywords) {
        super();
        this.keywords = keywords;
    }

    @Override
    public boolean test(Recipe recipe) {
        List<Step> recipeStep = recipe.getSteps();
        for (Step s : recipeStep) {
            if (keywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(s.step, keyword))) {
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StepsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((StepsContainsKeywordsPredicate) other).keywords)); // state check
    }

}
