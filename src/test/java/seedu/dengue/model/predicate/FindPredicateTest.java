package seedu.dengue.model.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.ContinuousData;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.SubPostal;
import seedu.dengue.model.range.EndAge;
import seedu.dengue.model.range.EndDate;
import seedu.dengue.model.range.Range;
import seedu.dengue.model.range.StartAge;
import seedu.dengue.model.range.StartDate;
import seedu.dengue.model.variant.Variant;
import seedu.dengue.testutil.PersonBuilder;


public class FindPredicateTest {
    private final Optional<SubPostal> emptySubPostal = Optional.empty();
    private final Optional<Age> emptyAge = Optional.empty();
    private final Optional<Date> emptyDate = Optional.empty();
    private final Set<Variant> emptyVariants = new HashSet<>();
    private final Range<Date> emptyDateRange = ContinuousData.generateRange(
            new StartDate(Optional.empty()), new EndDate(Optional.empty()));
    private final Range<Age> emptyAgeRange = ContinuousData.generateRange(
            new StartAge(Optional.empty()), new EndAge(Optional.empty()));
    private Optional<Name> testName;
    private Optional<Date> testDate;

    public FindPredicateTest() throws ParseException {
    }

    @BeforeEach
    public void setUp() {
        this.testName = Optional.of(new Name("ALICE"));
        this.testDate = Optional.of(new Date("2000-11-11"));
    }

    @Test
    public void equals() {

        FindPredicate firstPredicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);
        FindPredicate secondPredicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, testDate, emptyVariants, emptyDateRange, emptyAgeRange);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FindPredicate firstPredicateCopy = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    //test null
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> new FindPredicate(null, null, null, null, null, null, null));
    }

    //test keywords
    @Test
    public void test_nameContainsKeywords_returnsTrue() {

        // one keyword all case
        FindPredicate predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        testName = Optional.of(new Name("ALICE BOB"));
        // Multiple keywords
        predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        testName = Optional.of(new Name("ALicE BoB"));
        // Mixed-case keywords
        predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {

        // similar name (superstring)
        FindPredicate predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);
        assertFalse(predicate.test(new PersonBuilder().withName("Alicia").build()));

        // non-matching keyword
        testName = Optional.of(new Name("Carol"));
        predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_datesWithinRange_returnsTrue() throws ParseException {
        // Date falls within range
        StartDate start = new StartDate(Optional.of(new Date("2000-01-01")));
        EndDate end = new EndDate(Optional.of(new Date("2009-01-01")));
        Range<Date> testRange = ContinuousData.generateRange(start, end);
        FindPredicate predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, testRange, emptyAgeRange);
        assertTrue(predicate.test(
                new PersonBuilder().withName(
                        testName.get().toString()).withDate("2002-01-01").build()));

        // Date barely falls within range
        assertTrue(predicate.test(
                new PersonBuilder().withName(
                        testName.get().toString()).withDate("2009-01-01").build()));
        assertTrue(predicate.test(
                new PersonBuilder().withName(
                        testName.get().toString()).withDate("2000-01-01").build()));
    }

    @Test
    public void test_datesOutsideRange_returnsFalse() throws ParseException {
        StartDate start = new StartDate(Optional.of(new Date("2000-01-01")));
        EndDate end = new EndDate(Optional.of(new Date("2009-01-01")));
        Range<Date> testRange = ContinuousData.generateRange(start, end);
        FindPredicate predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, testRange, emptyAgeRange);
        assertFalse(predicate.test(
                new PersonBuilder().withName(
                        testName.get().toString()).withDate("2009-01-02").build()));
        assertFalse(predicate.test(
                new PersonBuilder().withName(
                        testName.get().toString()).withDate("1999-12-31").build()));

    }

    @Test
    public void test_agesWithinRange_returnsTrue() throws ParseException {
        // ages fall within range
        StartAge start = new StartAge(Optional.of(new Age("2")));
        EndAge end = new EndAge(Optional.of(new Age("99")));
        Range<Age> testRange = ContinuousData.generateRange(start, end);
        FindPredicate predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, testRange);
        assertTrue(predicate.test(
                new PersonBuilder().withName(
                        testName.get().toString()).withAge("23").build()));

        // ages barely fall within range
        assertTrue(predicate.test(
                new PersonBuilder().withName(
                        testName.get().toString()).withAge("2").build()));
        assertTrue(predicate.test(
                new PersonBuilder().withName(
                        testName.get().toString()).withAge("99").build()));
    }

    @Test
    public void test_agesOutsideRange_returnsFalse() throws ParseException {
        StartAge start = new StartAge(Optional.of(new Age("2")));
        EndAge end = new EndAge(Optional.of(new Age("99")));
        Range<Age> testRange = ContinuousData.generateRange(start, end);
        FindPredicate predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, testRange);
        assertFalse(predicate.test(
                new PersonBuilder().withName(
                        testName.get().toString()).withAge("1").build()));
        assertFalse(predicate.test(
                new PersonBuilder().withName(
                        testName.get().toString()).withAge("100").build()));
    }

    @Test
    public void test_onesidedRangesForDateAndAge_returnsTrue() throws ParseException {
        StartAge startAge = new StartAge(Optional.of(new Age("50")));
        EndAge endAge = new EndAge(Optional.empty());
        StartDate startDate = new StartDate(Optional.empty());
        EndDate endDate = new EndDate(Optional.of(new Date("2009-01-01")));

        Range<Age> ageRange = ContinuousData.generateRange(startAge, endAge);
        Range<Date> dateRange = ContinuousData.generateRange(startDate, endDate);

        FindPredicate predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, dateRange, ageRange);

        assertTrue(predicate.test(
                new PersonBuilder().withName(
                                testName.get().toString())
                        .withAge("199")
                        .withDate("1992-01-01")
                        .build()));

    }

    @Test
    public void test_oneSidedRangesForDateAndAge_returnsFalse() throws ParseException {
        StartAge startAge = new StartAge(Optional.of(new Age("50")));
        EndAge endAge = new EndAge(Optional.empty());
        StartDate startDate = new StartDate(Optional.empty());
        EndDate endDate = new EndDate(Optional.of(new Date("2009-01-01")));

        Range<Age> ageRange = ContinuousData.generateRange(startAge, endAge);
        Range<Date> dateRange = ContinuousData.generateRange(startDate, endDate);

        FindPredicate predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, dateRange, ageRange);

        assertFalse(predicate.test(
                new PersonBuilder().withName(
                                testName.get().toString())
                        .withAge("0")
                        .withDate("2010-01-01")
                        .build()));
    }

    @Test
    public void test_oneSidedRangesForDateTwoSidedRangesForAge_returnsFalse() throws ParseException {
        StartAge startAge = new StartAge(Optional.of(new Age("50")));
        EndAge endAge = new EndAge(Optional.of(new Age("100")));
        StartDate startDate = new StartDate(Optional.empty());
        EndDate endDate = new EndDate(Optional.of(new Date("2009-01-01")));

        Range<Age> ageRange = ContinuousData.generateRange(startAge, endAge);
        Range<Date> dateRange = ContinuousData.generateRange(startDate, endDate);

        FindPredicate predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, dateRange, ageRange);

        assertFalse(predicate.test(
                new PersonBuilder().withName(
                                testName.get().toString())
                        .withAge("75")
                        .withDate("2010-01-01")
                        .build()));
    }

    @Test
    public void test_oneSidedRangesForDateTwoSidedRangesForAge_returnsTrue() throws ParseException {
        StartAge startAge = new StartAge(Optional.of(new Age("50")));
        EndAge endAge = new EndAge(Optional.of(new Age("100")));
        StartDate startDate = new StartDate(Optional.empty());
        EndDate endDate = new EndDate(Optional.of(new Date("2009-01-01")));

        Range<Age> ageRange = ContinuousData.generateRange(startAge, endAge);
        Range<Date> dateRange = ContinuousData.generateRange(startDate, endDate);

        FindPredicate predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, dateRange, ageRange);

        assertTrue(predicate.test(
                new PersonBuilder().withName(
                                testName.get().toString())
                        .withAge("75")
                        .withDate("2008-01-01")
                        .build()));
    }

    @Test
    public void test_twoSidedRangesForDateOneSidedRangesForAge_returnsFalse() throws ParseException {
        StartAge startAge = new StartAge(Optional.of(new Age("50")));
        EndAge endAge = new EndAge(Optional.empty());
        StartDate startDate = new StartDate(Optional.of(new Date("2000-01-01")));
        EndDate endDate = new EndDate(Optional.of(new Date("2009-01-01")));

        Range<Age> ageRange = ContinuousData.generateRange(startAge, endAge);
        Range<Date> dateRange = ContinuousData.generateRange(startDate, endDate);

        FindPredicate predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, dateRange, ageRange);

        assertFalse(predicate.test(
                new PersonBuilder().withName(
                                testName.get().toString())
                        .withAge("0")
                        .withDate("2008-01-01")
                        .build()));
    }

    @Test
    public void test_twoSidedRangesForDateOneSidedRangesForAge_returnsTrue() throws ParseException {
        StartAge startAge = new StartAge(Optional.of(new Age("50")));
        EndAge endAge = new EndAge(Optional.empty());
        StartDate startDate = new StartDate(Optional.of(new Date("2000-01-01")));
        EndDate endDate = new EndDate(Optional.of(new Date("2009-01-01")));

        Range<Age> ageRange = ContinuousData.generateRange(startAge, endAge);
        Range<Date> dateRange = ContinuousData.generateRange(startDate, endDate);

        FindPredicate predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, dateRange, ageRange);

        assertTrue(predicate.test(
                new PersonBuilder().withName(
                                testName.get().toString())
                        .withAge("75")
                        .withDate("2008-01-01")
                        .build()));
    }
}
