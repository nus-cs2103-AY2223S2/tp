package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s Details matches the keywords given.
 */
public class PersonContainsTagsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonContainsTagsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(
                    person.getTags()
                    .stream()
                    .map(tag -> tag.tagName)
                    .collect(Collectors.joining(" ")), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsTagsPredicate // instanceof handles nulls
                && keywords.equals(((PersonContainsTagsPredicate) other).keywords)); // state check
    }

}
