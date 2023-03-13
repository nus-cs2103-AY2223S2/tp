package arb.model.project;

import java.util.List;
import java.util.function.Predicate;

import arb.commons.util.StringUtil;
import arb.model.client.Client;

/**
 * Tests that a {@code Project}'s {@code Title} matches any of the keywords given.
 */
public class TitleContainsKeywordsPredicate implements Predicate<Project> {
    private final List<String> keywords;

    public TitleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Project project) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(project.getTitle().title, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof arb.model.project.TitleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((arb.model.project.TitleContainsKeywordsPredicate) other).keywords)); // state check
    }

}