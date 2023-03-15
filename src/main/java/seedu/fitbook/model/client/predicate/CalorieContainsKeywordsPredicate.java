package seedu.fitbook.model.client.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.fitbook.commons.util.StringUtil;
import seedu.fitbook.model.client.Client;

/**
 * Tests that a {@code Client}'s {@code Calorie} matches any of the keywords given.
 */
public class CalorieContainsKeywordsPredicate implements Predicate<Client> {
    public static final String PREFIX_PREDICATE = "cal";
    private final List<String> keywords;

    public CalorieContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Client client) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(client.getCalorie().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CalorieContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((CalorieContainsKeywordsPredicate) other).keywords)); // state check
    }

}
