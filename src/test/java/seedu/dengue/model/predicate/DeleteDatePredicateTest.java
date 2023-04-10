package seedu.dengue.model.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.testutil.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.person.ContinuousData;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.range.EndDate;
import seedu.dengue.model.range.Range;
import seedu.dengue.model.range.StartDate;
import seedu.dengue.testutil.PersonBuilder;

public class DeleteDatePredicateTest {
    private final Range<Date> emptyDateRange = ContinuousData.generateRange(
            new StartDate(Optional.empty()), new EndDate(Optional.empty()));
    private final Optional<Date> emptyDate = Optional.empty();
    private final Optional<Date> validDate = Optional.of(new Date("1999-12-31"));

    public DeleteDatePredicateTest() throws ParseException {
    }

    // test null
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> new DeleteDatePredicate(null, null));
    }

    // test date ranges
    @Test
    public void test_datesWithinRange_returnsTrue() throws ParseException {
        StartDate start = new StartDate(Optional.of(new Date("2000-01-01")));
        EndDate end = new EndDate(Optional.of(new Date("2009-01-01")));
        Range<Date> testDateRange = ContinuousData.generateRange(start, end);
        DeleteDatePredicate predicate = new DeleteDatePredicate(emptyDate, testDateRange);

        // Date falls within range
        assertTrue(predicate.test(
                new PersonBuilder().withDate("2002-01-01").build()));

        // Date barely falls within range
        assertTrue(predicate.test(
                new PersonBuilder().withDate("2009-01-01").build()));
        assertTrue(predicate.test(
                new PersonBuilder().withDate("2000-01-01").build()));
    }

    @Test
    public void test_datesOutsideRange_returnsFalse() throws ParseException {
        StartDate start = new StartDate(Optional.of(new Date("2000-01-01")));
        EndDate end = new EndDate(Optional.of(new Date("2009-01-01")));
        Range<Date> testDateRange = ContinuousData.generateRange(start, end);
        DeleteDatePredicate predicate = new DeleteDatePredicate(emptyDate, testDateRange);
        assertFalse(predicate.test(
                new PersonBuilder().withDate("2009-01-02").build()));
        assertFalse(predicate.test(
                new PersonBuilder().withDate("1999-12-31").build()));

    }

    // test empty date range but valid date
    @Test
    public void test_validDateEmptyDateRange_returnsTrue() {
        DeleteDatePredicate predicate = new DeleteDatePredicate(validDate, emptyDateRange);
        assertTrue(predicate.test(
                new PersonBuilder().withDate("1999-12-31").build()));
    }

    @Test
    public void test_validDateEmptyDateRange_returnsFalse() {
        DeleteDatePredicate predicate = new DeleteDatePredicate(validDate, emptyDateRange);
        assertFalse(predicate.test(
                new PersonBuilder().withDate("1998-12-31").build()));
    }

    // test empty date range and empty date
    @Test
    public void test_emptyDateEmptyDateRange_returnsTrue() {
        DeleteDatePredicate predicate = new DeleteDatePredicate(emptyDate, emptyDateRange);
        assertTrue(predicate.test(
                new PersonBuilder().withDate("1999-12-31").build()));
    }
}
