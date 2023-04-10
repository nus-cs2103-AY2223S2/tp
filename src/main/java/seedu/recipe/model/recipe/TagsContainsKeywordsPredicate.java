package seedu.recipe.model.recipe;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.recipe.commons.util.StringUtil;
import seedu.recipe.model.tag.Tag;

/**
 * Tests that a {@code Recipe}'s {@code Tags} matches any of the keywords given.
 */
public class TagsContainsKeywordsPredicate extends ContainsKeywordsPredicate implements Predicate<Recipe> {
    private final List<String> keywords;

    /**
     * Checks that at least one of the tags contains the keywords.
     *
     * @param keywords list of the keywords
     */
    public TagsContainsKeywordsPredicate(List<String> keywords) {
        super();
        this.keywords = keywords;
    }

    @Override
    public boolean test(Recipe recipe) {
        Set<Tag> recipeTag = recipe.getTags();
        for (Tag t : recipeTag) {
            if (keywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(t.tagName, keyword))) {
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagsContainsKeywordsPredicate) other).keywords)); // state check
    }

}
