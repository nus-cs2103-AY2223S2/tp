package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.ElderlyBuilder;
import seedu.address.testutil.VolunteerBuilder;

class AvailableDatesWithinRangePredicateTest {
    private static final LocalDate firstDate = LocalDate.parse("2023-05-08");
    private static final LocalDate secondDate = LocalDate.parse("2024-11-26");
    private static final LocalDate thirdDate = LocalDate.parse("2025-02-12");
    @Test
    public void equals() {
        AvailableDatesWithinRangePredicate<Person> firstPredicate =
                new AvailableDatesWithinRangePredicate<>(firstDate, secondDate);
        AvailableDatesWithinRangePredicate<Person> secondPredicate =
                new AvailableDatesWithinRangePredicate<>(firstDate, thirdDate);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AvailableDatesWithinRangePredicate<Person> firstPredicateCopy =
                new AvailableDatesWithinRangePredicate<>(firstDate, secondDate);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different date range -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_availableDatesWithinRange_returnsTrue() {
        // exact match
        AvailableDatesWithinRangePredicate<Person> predicate =
                new AvailableDatesWithinRangePredicate<>(firstDate, secondDate);
        assertTrue(predicate.test(new ElderlyBuilder().withAvailableDates("2023-05-08", "2024-11-26").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withAvailableDates("2023-05-08", "2024-11-26").build()));

        // start date earlier
        assertTrue(predicate.test(new ElderlyBuilder().withAvailableDates("2023-04-11", "2024-11-26").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withAvailableDates("2023-04-11", "2024-11-26").build()));

        // end date later
        assertTrue(predicate.test(new ElderlyBuilder().withAvailableDates("2023-05-08", "2024-12-26").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withAvailableDates("2023-05-08", "2024-12-26").build()));

        // start date earlier and end date later
        assertTrue(predicate.test(new ElderlyBuilder().withAvailableDates("2022-05-08", "2024-11-30").build()));
        assertTrue(predicate.test(new VolunteerBuilder().withAvailableDates("2022-05-08", "2024-11-30").build()));
    }

    @Test
    public void test_availableDatesEmpty_returnsTrue() {
        // no dates specified
        AvailableDatesWithinRangePredicate<Person> predicate =
                new AvailableDatesWithinRangePredicate<>(firstDate, secondDate);
        assertTrue(predicate.test(new ElderlyBuilder().build()));
        assertTrue(predicate.test(new VolunteerBuilder().build()));
    }

    @Test
    public void test_availableDatesNotWithinRange_returnsFalse() {
        // start date later
        AvailableDatesWithinRangePredicate<Person> predicate =
                new AvailableDatesWithinRangePredicate<>(firstDate, secondDate);
        assertFalse(predicate.test(new ElderlyBuilder().withAvailableDates("2023-05-13", "2024-11-26").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withAvailableDates("2023-05-13", "2024-11-26").build()));

        // end date earlier
        assertFalse(predicate.test(new ElderlyBuilder().withAvailableDates("2023-05-13", "2024-10-06").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withAvailableDates("2023-05-13", "2024-10-06").build()));

        // start date earlier and end date later
        assertFalse(predicate.test(new ElderlyBuilder().withAvailableDates("2024-02-11", "2024-09-13").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withAvailableDates("2024-02-11", "2024-09-13").build()));
    }
}
