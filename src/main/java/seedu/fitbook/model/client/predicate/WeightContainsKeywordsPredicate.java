package seedu.fitbook.model.client.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.fitbook.commons.util.StringUtil;
import seedu.fitbook.model.client.Client;

/**
 * Tests that a {@code Client}'s {@code Weight} matches any of the keywords given.
 */
public class WeightContainsKeywordsPredicate implements Predicate<Client> {
    public static final char PREFIX_PREDICATE = 'w';
    private final List<String> keywords;

    public WeightContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Client client) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(client.getWeight().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WeightContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((WeightContainsKeywordsPredicate) other).keywords)); // state check
    }

}
