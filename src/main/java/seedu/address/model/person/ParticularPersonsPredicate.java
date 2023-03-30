package seedu.address.model.person;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Tests for equality against at least one of a supplied list of {@code Person}s.
 */
public class ParticularPersonsPredicate implements Predicate<Person> {
    private final List<Person> persons;

    /**
     * Creates a ParticularPersonsPredicate matching any Person equal to an element in {@code persons}.
     *
     * @param persons Persons to match against
     */
    public ParticularPersonsPredicate(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean test(Person person) {
        return persons.contains(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ParticularPersonsPredicate // instanceof handles nulls
                && Objects.equals(persons, ((ParticularPersonsPredicate) other).persons)); // state check
    }

}
