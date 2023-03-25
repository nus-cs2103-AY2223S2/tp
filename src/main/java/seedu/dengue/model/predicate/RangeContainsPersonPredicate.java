package seedu.dengue.model.predicate;

import java.util.function.Predicate;

import seedu.dengue.model.person.Person;
import seedu.dengue.model.range.Range;

public class RangeContainsPersonPredicate implements Predicate<Person> {

    private final Range range;

    public RangeContainsPersonPredicate(Range range) {
        this.range = range;
    }

    @Override
    public boolean test(Person person) {
        return range.getStart().isBefore(person) & range.getEnd().isAfter(person);
    }
}
