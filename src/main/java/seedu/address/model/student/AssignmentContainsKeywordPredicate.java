package seedu.address.model.student;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that an {@code Assignment}'s description or deadline matches any of the keywords given.
 */
public class AssignmentContainsKeywordPredicate implements Predicate<Assignment> {
    private final List<String> keywords;

    public AssignmentContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Assignment assignment) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(assignment.getDescription(), keyword)
                        || StringUtil.containsWordIgnoreCase(assignment.getDeadline().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignmentContainsKeywordPredicate // instanceof handles nulls
                && keywords.equals(((AssignmentContainsKeywordPredicate) other).keywords)); // state check
    }
}
