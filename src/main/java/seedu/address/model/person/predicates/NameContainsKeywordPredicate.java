package seedu.address.model.person.predicates;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Name} contains the given keyword.
 */
public class NameContainsKeywordPredicate<T extends Person> implements Predicate<T> {
    private final String keyword;

    /**
     * Constructs a {@code NameContainsKeywordPredicate} with the given keyword.
     *
     * @param keyword The matching string.
     */
    public NameContainsKeywordPredicate(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean test(T object) {
        return object.getName().fullName.toLowerCase().contains(keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((NameContainsKeywordPredicate<?>) other).keyword)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyword);
    }
}
