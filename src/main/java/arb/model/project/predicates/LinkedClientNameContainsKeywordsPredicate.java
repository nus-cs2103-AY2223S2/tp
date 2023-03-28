package arb.model.project.predicates;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import arb.commons.util.StringUtil;
import arb.model.project.Project;

/**
 * Tests that a {@code Project}'s linked client's name contains any of the keywords given.
 */
public class LinkedClientNameContainsKeywordsPredicate implements Predicate<Project> {
    private final Set<String> keywords;

    public LinkedClientNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = new HashSet<>(keywords);
    }

    @Override
    public boolean test(Project project) {
        return keywords.stream()
                .anyMatch(n -> project.isClientPresent()
                        && StringUtil.containsWordIgnoreCase(project.getClientName(), n));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LinkedClientNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((LinkedClientNameContainsKeywordsPredicate) other).keywords)); // state check
    }

    @Override
    public int hashCode() {
        return keywords.hashCode();
    }
}
