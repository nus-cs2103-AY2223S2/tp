package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests if any of a {@code Person}'s fields match any of the keywords given.
 * Keyword matching is case-insensitive and need not match the whole word.
 */
public class AnyFieldContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public AnyFieldContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        boolean isAnyPersonDetailsMatched = keywords.stream().anyMatch(
                keyword -> StringUtil.containsStringIgnoreCase(person.getRank().value, keyword)
                        || StringUtil.containsStringIgnoreCase(person.getName().fullName, keyword)
                        || StringUtil.containsStringIgnoreCase(person.getUnit().value, keyword)
                        || StringUtil.containsStringIgnoreCase(person.getCompany().value, keyword)
                        || StringUtil.containsStringIgnoreCase(person.getPlatoon().value, keyword)
                        || StringUtil.containsStringIgnoreCase(person.getPhone().value, keyword)
                        || StringUtil.containsStringIgnoreCase(person.getEmail().value, keyword)
                        || StringUtil.containsStringIgnoreCase(person.getAddress().value, keyword));

        if (isAnyPersonDetailsMatched) {
            return true;
        }

        // check all tags for match
        for (String keyword : keywords) {
            if (person.getTags()
                    .stream()
                    .anyMatch(tag -> StringUtil.containsStringIgnoreCase(tag.tagName, keyword))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AnyFieldContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AnyFieldContainsKeywordsPredicate) other).keywords)); // state check
    }
}
