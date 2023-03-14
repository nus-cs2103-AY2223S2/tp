package seedu.address.model.person.predicates;

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
}
