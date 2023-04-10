package seedu.fitbook.model.client.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.fitbook.commons.util.StringUtil;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.tag.Tag;

/**
 * Tests that a {@code Client}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Client> {

    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Client client) {
        boolean testClient = false;
        for (Tag tag : client.getTags()) {
            if (keywords.stream().anyMatch(keyword -> StringUtil
                    .containsWordIgnoreCase(tag.tagName, keyword))) {
                testClient = true;
                break;
            }
        }
        return testClient;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
