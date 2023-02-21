package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.CliSyntax;
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
        switch (prefix.getPrefix()) {

        case CliSyntax.PREFIX_NAME_STRING:
            return person.getName().fullName;

        case CliSyntax.PREFIX_PHONE_STRING:
            return person.getPhone().value;

        case CliSyntax.PREFIX_EMAIL_STRING:
            return person.getEmail().value;

        case CliSyntax.PREFIX_ADDRESS_STRING:
            return person.getAddress().value;

        case CliSyntax.PREFIX_TELEGRAM_HANDLE_STRING:
            return person.getTelegramHandle().telegramHandle;

        case CliSyntax.PREFIX_MODULE_TAG_STRING:
            return person.getModuleTags().toString();

        case CliSyntax.PREFIX_GROUP_TAG_STRING:
            return person.getGroupTags().toString();

        default:
            // Will not reach here
            return "";
        }
    }

}
