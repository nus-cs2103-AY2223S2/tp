package seedu.dengue.model.predicate;

import static java.util.Objects.requireNonNull;

import seedu.dengue.model.person.ContinuousData;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.range.Range;

/**
 * Represents the predicate which tests for whether the person in the persons list is reported on the
 * range based on the user input.
 */
public class RangeContainsPersonPredicate extends PredicateUtil<Person> {

    private final Range<? extends ContinuousData> range;

    /**
     * Creates a {@code RangeContainsPersonPredicate} that checks a {@code Range} of
     * {@link ContinuousData} such as Date or Age.
     * @param range A range of {@link ContinuousData}.
     */
    public RangeContainsPersonPredicate(Range<? extends ContinuousData> range) {
        requireNonNull(range);
        this.range = range;
    }

    @Override
    public boolean test(Person person) {
        return range.getStart().isBefore(person) && range.getEnd().isAfter(person);
    }
}
