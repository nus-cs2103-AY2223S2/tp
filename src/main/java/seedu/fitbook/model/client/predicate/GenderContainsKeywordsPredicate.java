package seedu.fitbook.model.client.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.fitbook.commons.util.StringUtil;
import seedu.fitbook.model.client.Client;

/**
 * Tests that a {@code Client}'s {@code Gender} matches any of the keywords given.
 */
public class GenderContainsKeywordsPredicate implements Predicate<Client> {
    public static final char PREFIX_PREDICATE = 'g';
    private final List<String> keywords;

    public GenderContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Client client) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(client.getGender().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GenderContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((GenderContainsKeywordsPredicate) other).keywords)); // state check
    }

}
