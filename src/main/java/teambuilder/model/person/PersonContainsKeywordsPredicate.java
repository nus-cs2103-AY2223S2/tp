package teambuilder.model.person;

import java.util.List;
import java.util.function.Predicate;

import teambuilder.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} or {@code Major} or {@code Tags}
 * matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        boolean isInName = keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
        boolean isInMajor = keywords.stream()
                .anyMatch(keywords -> StringUtil.containsWordIgnoreCase(person.getMajor().toString(), keywords));
        boolean isInTag = keywords.stream()
                .anyMatch(keyword -> person.getTags().stream()
                        .anyMatch(tag -> StringUtil.containsWordIgnoreCase(keyword, tag.tagName)));
        return isInName || isInMajor || isInTag;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonContainsKeywordsPredicate) other).keywords)); // state check
    }

}
