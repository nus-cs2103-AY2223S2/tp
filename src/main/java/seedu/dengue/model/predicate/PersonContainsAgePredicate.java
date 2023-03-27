package seedu.dengue.model.predicate;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.dengue.commons.util.StringUtil;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Person;

public class PersonContainsAgePredicate implements Predicate<Person> {

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
