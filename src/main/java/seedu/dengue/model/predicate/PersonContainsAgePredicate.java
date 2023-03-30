package seedu.dengue.model.predicate;

import java.util.Optional;

import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Person;

/**
 * Represents the predicate which tests for whether the person in the persons list is of the age based
 * on the user input.
 */
public class PersonContainsAgePredicate extends PredicateUtil<Person> {

    private final Optional<Age> age;

    public PersonContainsAgePredicate(Optional<Age> age) {
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
