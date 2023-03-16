package seedu.address.model.person.predicates;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests if any of a {@code Person}'s tags matches the given tag name.
 */
public class TagIsEqualPredicate<T extends Person> implements Predicate<T> {
    private final String tagName;

    /**
     * Constructs a {@code TagIsEqualPredicate} with the given tag name.
     *
     * @param tagName The matching tag name.
     */
    public TagIsEqualPredicate(String tagName) {
        this.tagName = tagName.toLowerCase();
    }

    @Override
    public boolean test(T object) {
        return object.getTags().stream().anyMatch(tag -> tag.tagName.toLowerCase().equals(tagName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagIsEqualPredicate // instanceof handles nulls
                && tagName.equals(((TagIsEqualPredicate<?>) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName);
    }
}
