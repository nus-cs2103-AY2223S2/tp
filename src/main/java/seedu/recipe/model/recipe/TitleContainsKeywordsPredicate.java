package seedu.recipe.model.recipe;

import java.util.List;
import java.util.function.Predicate;

import seedu.recipe.commons.util.StringUtil;

/**
 * Tests that a {@code Recipe}'s {@code Title} matches any of the keywords given.
 */
public class TitleContainsKeywordsPredicate extends ContainsKeywordsPredicate implements Predicate<Recipe> {
    private final List<String> keywords;

    /**
     * Checks that the title contains the keywords.
     *
     * @param keywords list of the keywords
     */
    public TitleContainsKeywordsPredicate(List<String> keywords) {
        super();
        this.keywords = keywords;
    }

    @Override
    public boolean test(Recipe recipe) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(recipe.getTitle().title, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TitleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TitleContainsKeywordsPredicate) other).keywords)); // state check
    }

}
