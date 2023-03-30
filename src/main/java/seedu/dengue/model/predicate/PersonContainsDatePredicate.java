package seedu.dengue.model.predicate;

import java.util.Optional;

import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Person;

/**
 * Represents the predicate which tests for whether the person in the persons list is reported on the
 * date based on the user input.
 */
public class PersonContainsDatePredicate extends PredicateUtil<Person> {
    private final Optional<Date> date;

    public PersonContainsDatePredicate(Optional<Date> date) {
        this.date = date;
    }

    @Override
    public boolean test(Person person) {
        if (date.isPresent()) {
            Date d = date.get();
            return person.getDate().equals(d);
        }
        return true;
    }
}
