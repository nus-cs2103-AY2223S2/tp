package seedu.address.model.student;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that an {@code Homework}'s description or deadline matches any of the keywords given.
 */
public class HomeworkContainsKeywordPredicate implements Predicate<Homework> {
    private final List<String> keywords;

    public HomeworkContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Homework homework) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(homework.getDescription(), keyword)
                        || StringUtil.containsWordIgnoreCase(homework.getDeadline().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HomeworkContainsKeywordPredicate // instanceof handles nulls
                && keywords.equals(((HomeworkContainsKeywordPredicate) other).keywords)); // state check
    }
}
