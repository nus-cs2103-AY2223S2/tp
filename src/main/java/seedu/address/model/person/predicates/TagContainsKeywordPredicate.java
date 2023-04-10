package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_FIELD_CANNOT_BE_EMPTY;
import static seedu.address.commons.util.AppUtil.argNotEmpty;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that any {@code Person}'s {@code Tag} contains the given keyword.
 */
public class TagContainsKeywordPredicate<T extends Person> implements Predicate<T> {
    private final String keyword;

    /**
     * Constructs a {@code TagContainsKeywordPredicate} with the given keyword.
     *
     * @param keyword The matching string.
     */
    public TagContainsKeywordPredicate(String keyword) {
        requireNonNull(keyword);
        checkArgument(argNotEmpty(keyword), String.format(MESSAGE_FIELD_CANNOT_BE_EMPTY, "Tag"));
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean test(T object) {
        return object.getTags().stream().anyMatch(tag -> tag.tagName.toLowerCase().contains(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((TagContainsKeywordPredicate<?>) other).keyword)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyword);
    }
}
