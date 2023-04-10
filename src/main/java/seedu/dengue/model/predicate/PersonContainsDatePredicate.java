package seedu.dengue.model.predicate;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Person;

/**
 * Represents the predicate which tests for whether the person in the persons list is reported on the
 * date based on the user input.
 */
public class PersonContainsDatePredicate extends PredicateUtil<Person> {
    private final Optional<Date> date;

    /**
     * Constructs a predicate used to test whether the date is a part of a person in the case list.
     * @param date optional date of the predicate which is used to test with a person.
     */
    public PersonContainsDatePredicate(Optional<Date> date) {
        requireNonNull(date);
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
