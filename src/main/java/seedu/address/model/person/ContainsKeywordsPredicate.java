package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.Prefix;


/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final Prefix prefix;

    /**
     * Public constructor for predicate.
     * @param keywords List of strings of keywords.
     * @param prefix Prefix from CliSyntax.
     */
    public ContainsKeywordsPredicate(List<String> keywords, Prefix prefix) {
        this.keywords = keywords;
        this.prefix = prefix;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsKeyWordIgnoreCase(selector(person), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ContainsKeywordsPredicate) other).keywords)); // state check
    }

    private String selector(Person person) {
        switch (prefix) {

        case NAME:
            return person.getName().getValue();

        case PHONE:
            return person.getPhone().getValue();

        case EMAIL:
            return person.getEmail().getValue();

        case ADDRESS:
            return person.getAddress().getValue().getName();

        case TELEGRAM_HANDLE:
            return person.getTelegramHandle().getValue();

        case MODULE_TAG:
            return person.getImmutableModuleTags().toString();

        case GROUP_TAG:
            return person.getImmutableGroupTags().toString();

        default:
            // Will not reach here
            return "";
        }
    }

}
