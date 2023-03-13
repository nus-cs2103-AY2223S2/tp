package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class MemberOfGroupPredicate implements Predicate<Person> {
    private final List<String> groupKeywords;

    public MemberOfGroupPredicate(List<String> keywords) {
        groupKeywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return groupKeywords.stream().anyMatch(keyword -> person.getGroups().stream().anyMatch(group ->
                                StringUtil.containsWordIgnoreCase(group.getName(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MemberOfGroupPredicate // instanceof handles nulls
                && groupKeywords.equals(((MemberOfGroupPredicate) other).groupKeywords)); // state check
    }

}
