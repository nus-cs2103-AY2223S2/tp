package seedu.dengue.model.predicate;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Person;

public class PersonContainsDatePredicate implements Predicate<Person> {
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
