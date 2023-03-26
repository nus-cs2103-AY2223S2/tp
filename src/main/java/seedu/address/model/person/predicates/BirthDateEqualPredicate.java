package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code BirthDate} is equal with another person.
 */
public class BirthDateEqualPredicate<T extends Person> implements Predicate<T> {
    private final String birthDate;

    /**
     * Constructs an {@code BirthDateEqualPredicate} with the given keyword.
     *
     * @param birthDate The matching string.
     */
    public BirthDateEqualPredicate(String birthDate) {
        requireNonNull(birthDate);
        this.birthDate = birthDate;
    }

    @Override
    public boolean test(T object) {
        return object.getBirthDate().birthDate.equals(
                LocalDate.parse(birthDate)
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof BirthDateEqualPredicate
                && birthDate.equals(((BirthDateEqualPredicate) other).birthDate));
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthDate);
    }
}
