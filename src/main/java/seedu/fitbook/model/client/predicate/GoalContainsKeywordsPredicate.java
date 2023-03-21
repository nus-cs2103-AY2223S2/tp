package seedu.fitbook.model.client.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.fitbook.commons.util.StringUtil;
import seedu.fitbook.model.client.Client;

/**
 * Tests that a {@code Client}'s {@code Goal} matches any of the keywords given.
 */
public class GoalContainsKeywordsPredicate implements Predicate<Client> {

    private final List<String> keywords;

    public GoalContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Client client) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(client.getGoal().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GoalContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((GoalContainsKeywordsPredicate) other).keywords)); // state check
    }

}
