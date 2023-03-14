package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Address} contains the given keyword.
 */
public class AddressContainsKeywordPredicate<T extends Person> implements Predicate<T> {
    private final String keyword;

    /**
     * Constructs an {@code AddressContainsKeywordPredicate} with the given keyword.
     *
     * @param keyword The matching string.
     */
    public AddressContainsKeywordPredicate(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean test(T object) {
        return object.getAddress().value.toLowerCase().contains(keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((AddressContainsKeywordPredicate<?>) other).keyword)); // state check
    }
}
