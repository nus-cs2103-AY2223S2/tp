package seedu.dengue.model.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.testutil.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.ContinuousData;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.range.EndAge;
import seedu.dengue.model.range.EndDate;
import seedu.dengue.model.range.Range;
import seedu.dengue.model.range.StartAge;
import seedu.dengue.model.range.StartDate;
import seedu.dengue.testutil.PersonBuilder;

public class RangeContainsPersonPredicateTest {
    private final Range<Date> emptyDateRange = ContinuousData.generateRange(
            new StartDate(Optional.empty()), new EndDate(Optional.empty()));
    private final Range<Age> emptyAgeRange = ContinuousData.generateRange(
            new StartAge(Optional.empty()), new EndAge(Optional.empty()));

    public RangeContainsPersonPredicateTest() throws ParseException {
    }

    // test null
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> new RangeContainsPersonPredicate(null));
    }

    // test date ranges
    @Test
    public void test_datesWithinRange_returnsTrue() throws ParseException {
        StartDate start = new StartDate(Optional.of(new Date("2000-01-01")));
        EndDate end = new EndDate(Optional.of(new Date("2009-01-01")));
        Range<Date> testDateRange = ContinuousData.generateRange(start, end);
        RangeContainsPersonPredicate predicate = new RangeContainsPersonPredicate(testDateRange);

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
        RangeContainsPersonPredicate predicate = new RangeContainsPersonPredicate(testDateRange);
        assertFalse(predicate.test(
                new PersonBuilder().withDate("2009-01-02").build()));
        assertFalse(predicate.test(
                new PersonBuilder().withDate("1999-12-31").build()));

    }

    //test age ranges
    @Test
    public void test_agesWithinRange_returnsTrue() throws ParseException {
        StartAge start = new StartAge(Optional.of(new Age("2")));
        EndAge end = new EndAge(Optional.of(new Age("99")));
        Range<Age> testAgeRange = ContinuousData.generateRange(start, end);
        RangeContainsPersonPredicate predicate = new RangeContainsPersonPredicate(testAgeRange);

        // ages fall within range
        assertTrue(predicate.test(
                new PersonBuilder().withAge("23").build()));

        // ages barely fall within range
        assertTrue(predicate.test(
                new PersonBuilder().withAge("2").build()));
        assertTrue(predicate.test(
                new PersonBuilder().withAge("99").build()));
    }

    @Test
    public void test_agesOutsideRange_returnsFalse() throws ParseException {
        StartAge start = new StartAge(Optional.of(new Age("2")));
        EndAge end = new EndAge(Optional.of(new Age("99")));
        Range<Age> testAgeRange = ContinuousData.generateRange(start, end);
        RangeContainsPersonPredicate predicate = new RangeContainsPersonPredicate(testAgeRange);
        assertFalse(predicate.test(
                new PersonBuilder().withAge("1").build()));
        assertFalse(predicate.test(
                new PersonBuilder().withAge("100").build()));
    }

    // test empty ranges
    @Test
    public void test_emptyDateRange_returnsTrue() {
        RangeContainsPersonPredicate predicate = new RangeContainsPersonPredicate(emptyDateRange);
        assertTrue(predicate.test(
                new PersonBuilder().withDate("1999-12-31").build()));
    }

    @Test
    public void test_emptyAgeRange_returnsTrue() {
        RangeContainsPersonPredicate predicate = new RangeContainsPersonPredicate(emptyAgeRange);
        assertTrue(predicate.test(
                new PersonBuilder().withAge("31").build()));
    }
}
