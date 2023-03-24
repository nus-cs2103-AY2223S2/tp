package seedu.dengue.model.predicate;

import java.util.function.Predicate;

import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Person;

public class PersonContainsDatePredicate implements Predicate<Person> {
    private final Date date;

    public PersonContainsDatePredicate(Date date) {
        this.date = date;
    }

    @Override
    public boolean test(Person person) {
        return person.getDate().equals(date);
    }
}
