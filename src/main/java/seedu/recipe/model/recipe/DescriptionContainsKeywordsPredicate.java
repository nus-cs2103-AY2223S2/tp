package seedu.recipe.model.recipe;

import java.util.List;
import java.util.function.Predicate;

import seedu.recipe.commons.util.StringUtil;

/**
 * Tests that a {@code Recipe}'s {@code Title} matches any of the keywords given.
 */
public class DescriptionContainsKeywordsPredicate extends ContainsKeywordsPredicate implements Predicate<Recipe> {
    private final List<String> keywords;

    /**
     * Checks that the description contains the keywords.
     *
     * @param keywords list of the keywords
     */
    public DescriptionContainsKeywordsPredicate(List<String> keywords) {
        super();
        this.keywords = keywords;
    }

    @Override
    public boolean test(Recipe recipe) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(recipe.getDesc().description, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DescriptionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
