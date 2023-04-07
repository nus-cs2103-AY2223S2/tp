package seedu.dengue.model.predicate;

import java.util.Optional;

import seedu.dengue.model.person.ContinuousData;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.range.EndDate;
import seedu.dengue.model.range.Range;
import seedu.dengue.model.range.StartDate;

/**
 * Tests that a {@code Person}'s {@code Date} matches the date or date range given.
 */
public class DeleteDatePredicate extends PredicateUtil<Person> {
    public final Optional<Date> date;
    public final Range<Date> range;

    /**
     * A class that represents the predicate used to filter the case list.
     * @param date (Optional) Date input passed in by user.
     * @param range Range containing (Optional) Date inputs passed in by user.
     */

    public DeleteDatePredicate(Optional<Date> date, Range<Date> range) {
        this.date = date;
        this.range = range;
    }

    /**
     * A class that represents the predicate used to filter the case list.
     * @param date (Optional) Date input passed in by user.
     */

    public DeleteDatePredicate(Optional<Date> date) {
        this.date = date;
        this.range = ContinuousData.generateRange(
                new StartDate(Optional.empty()),
                new EndDate(Optional.empty()));
    }

    /**
     * A class that represents the predicate used to filter the case list.
     * @param range Range containing (Optional) Date inputs passed in by user.
     */
    public DeleteDatePredicate(Range<Date> range) {
        this.date = Optional.empty();
        this.range = range;
    }

    @Override
    public boolean test(Person person) {
        PersonContainsDatePredicate hasDate = new PersonContainsDatePredicate(date);
        RangeContainsPersonPredicate hasRange = new RangeContainsPersonPredicate(range);
        return andAll(hasDate, hasRange).test(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteDatePredicate // instanceof handles nulls
                && date.equals(((DeleteDatePredicate) other).date)
                && range.equals(((DeleteDatePredicate) other).range));
    }
}
