package seedu.dengue.model.predicate;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Person;

/**
 * Represents the predicate which tests for whether the person in the persons list is of the age based
 * on the user input.
 */
public class PersonContainsAgePredicate extends PredicateUtil<Person> {

    private final Optional<Age> age;

    /**
     * Constructs a predicate used to test whether the age is a part of a person in the case list.
     * @param age optional age of the predicate which is used to test with a person.
     */
    public PersonContainsAgePredicate(Optional<Age> age) {
        requireNonNull(age);
        this.age = age;
    }

    @Override
    public boolean test(Person person) {
        if (age.isPresent()) {
            return person.getAge().equals(age.get());
        } else {
            return true;
        }
    }
}
