package seedu.address.model.person;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches all of the keywords given.
 */
public class NameContainsAllKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    public NameContainsAllKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NameContainsAllKeywordsPredicate that = (NameContainsAllKeywordsPredicate) o;

        return Objects.equals(keywords, that.keywords);
    }

    @Override
    public int hashCode() {
        return keywords != null ? keywords.hashCode() : 0;
    }
}
