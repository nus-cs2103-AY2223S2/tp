package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Email} contains the given keyword.
 */
public class EmailContainsKeywordPredicate<T extends Person> implements Predicate<T> {
    private final String keyword;

    /**
     * Constructs an {@code EmailContainsKeywordPredicate} with the given keyword.
     *
     * @param keyword The matching string.
     */
    public EmailContainsKeywordPredicate(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean test(T object) {
        return object.getEmail().value.toLowerCase().contains(keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((EmailContainsKeywordPredicate<?>) other).keyword)); // state check
    }
}
