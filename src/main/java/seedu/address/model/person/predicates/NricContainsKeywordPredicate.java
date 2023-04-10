package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_FIELD_CANNOT_BE_EMPTY;
import static seedu.address.commons.util.AppUtil.argNotEmpty;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Nric} contains the given keyword.
 */
public class NricContainsKeywordPredicate<T extends Person> implements Predicate<T> {
    private final String keyword;

    /**
     * Constructs an {@code NricContainsKeywordPredicate} with the given keyword.
     *
     * @param keyword The matching string.
     */
    public NricContainsKeywordPredicate(String keyword) {
        requireNonNull(keyword);
        checkArgument(argNotEmpty(keyword), String.format(MESSAGE_FIELD_CANNOT_BE_EMPTY, "NRIC"));
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean test(T object) {
        return object.getNric().value.toLowerCase().contains(keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NricContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((NricContainsKeywordPredicate<?>) other).keyword)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyword);
    }
}
