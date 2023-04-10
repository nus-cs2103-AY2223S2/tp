package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_FIELD_CANNOT_BE_EMPTY;
import static seedu.address.commons.util.AppUtil.argNotEmpty;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;
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
        requireNonNull(keyword);
        checkArgument(argNotEmpty(keyword), String.format(MESSAGE_FIELD_CANNOT_BE_EMPTY, "Address"));
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

    @Override
    public int hashCode() {
        return Objects.hash(keyword);
    }
}
