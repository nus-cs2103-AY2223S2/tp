package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Age} exactly matches the given age.
 */
public class AgeIsEqualPredicate<T extends Person> implements Predicate<T> {
    private final String age;

    /**
     * Constructs an {@code AgeIsEqualPredicate} with the given age.
     *
     * @param age The matching string.
     */
    public AgeIsEqualPredicate(String age) {
        this.age = age;
    }

    @Override
    public boolean test(T object) {
        return object.getAge().value.equals(age);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AgeIsEqualPredicate // instanceof handles nulls
                && age.equals(((AgeIsEqualPredicate<?>) other).age)); // state check
    }
}
