package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_FIELD_CANNOT_BE_EMPTY;
import static seedu.address.commons.util.AppUtil.argNotEmpty;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Phone} contains the given digits.
 */
public class PhoneContainsDigitsPredicate<T extends Person> implements Predicate<T> {
    private final String digits;

    /**
     * Constructs a {@code PhoneContainsDigitsPredicate} with the given digits.
     *
     * @param digits The matching string.
     */
    public PhoneContainsDigitsPredicate(String digits) {
        requireNonNull(digits);
        checkArgument(argNotEmpty(digits), String.format(MESSAGE_FIELD_CANNOT_BE_EMPTY, "Phone number"));
        this.digits = digits;
    }

    @Override
    public boolean test(T object) {
        return object.getPhone().value.contains(digits);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsDigitsPredicate // instanceof handles nulls
                && digits.equals(((PhoneContainsDigitsPredicate<?>) other).digits)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(digits);
    }
}
