package seedu.dengue.model.predicate;

import java.util.function.Predicate;

import seedu.dengue.model.person.Person;
import seedu.dengue.model.range.Range;

/**
 * Represents the predicate which tests for whether the person in the persons list is reported on the
 * range based on the user input.
 */
public class RangeContainsPersonPredicate implements Predicate<Person> {

    private final Range range;

    public RangeContainsPersonPredicate(Range range) {
        this.range = range;
    }

    @Override
    public boolean test(Person person) {
        return range.getStart().isBefore(person) && range.getEnd().isAfter(person);
    }
}
