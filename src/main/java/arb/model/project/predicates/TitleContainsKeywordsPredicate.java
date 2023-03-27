package arb.model.project.predicates;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import arb.commons.util.StringUtil;
import arb.model.project.Project;

/**
 * Tests that a {@code Project}'s {@code Title} matches any of the keywords given.
 */
public class TitleContainsKeywordsPredicate implements Predicate<Project> {
    private final Set<String> keywords;

    public TitleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = new HashSet<>(keywords);
    }

    @Override
    public boolean test(Project project) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(project.getTitle().fullTitle, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TitleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TitleContainsKeywordsPredicate) other).keywords)); // state check
    }

    @Override
    public int hashCode() {
        return keywords.hashCode();
    }
}
