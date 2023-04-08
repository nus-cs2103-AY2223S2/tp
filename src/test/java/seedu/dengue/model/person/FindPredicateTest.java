package seedu.dengue.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.predicate.FindPredicate;
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


    @BeforeEach
    public void setUp() {
        this.testName = Optional.of(new Name("ALICE"));
    }
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

        // Similar name (superstring)
        FindPredicate predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);
        assertFalse(predicate.test(new PersonBuilder().withName("Alicia").build()));

        // Non-matching keyword
        testName = Optional.of(new Name("Carol"));
        predicate = new FindPredicate(
                testName, emptySubPostal, emptyAge, emptyDate, emptyVariants, emptyDateRange, emptyAgeRange);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_datesWithinRange_returnsTrue() {
        // Date falls within range
        StartDate start = new StartDate(Optional.<Date>of(new Date("2000-01-01")));
        EndDate end = new EndDate(Optional.<Date>of(new Date("2009-01-01")));
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
    public void test_datesOutsideRange_returnsFalse() {
        StartDate start = new StartDate(Optional.<Date>of(new Date("2000-01-01")));
        EndDate end = new EndDate(Optional.<Date>of(new Date("2009-01-01")));
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
    public void test_agesWithinRange_returnsTrue() {
        // ages fall within range
        StartAge start = new StartAge(Optional.<Age>of(new Age("2")));
        EndAge end = new EndAge(Optional.<Age>of(new Age("99")));
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
    public void test_agesOutsideRange_returnsFalse() {
        StartAge start = new StartAge(Optional.<Age>of(new Age("2")));
        EndAge end = new EndAge(Optional.<Age>of(new Age("99")));
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
    public void test_onesidedRangesForDateAndAge_returnsTrue() {
        StartAge startAge = new StartAge(Optional.<Age>of(new Age("50")));
        EndAge endAge = new EndAge(Optional.empty());
        StartDate startDate = new StartDate(Optional.empty());
        EndDate endDate = new EndDate(Optional.<Date>of(new Date("2009-01-01")));

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

}
