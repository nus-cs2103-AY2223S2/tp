package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Title} matches exactly the keywords given.
 */
public class NameContainsInOrderKeywordsPredicate implements Predicate<Person> {
    private final String keyword;

    public NameContainsInOrderKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return person.getStringNameLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsInOrderKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((NameContainsInOrderKeywordsPredicate) other).keyword)); // state check
    }
}
