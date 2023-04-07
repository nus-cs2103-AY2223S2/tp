package seedu.event.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.event.testutil.TypicalTimes.CLOCK_FIXED_AT_TIME_NOW;

import org.junit.jupiter.api.Test;

import seedu.event.testutil.EventBuilder;

public class StartTimeWithinDaysPredicateTest {
    @Test
    public void equals() {
        int firstPredicateDays = 3;
        int secondPredicateDays = 4;

        StartTimeWithinDaysPredicate firstPredicate = new StartTimeWithinDaysPredicate(
                CLOCK_FIXED_AT_TIME_NOW, firstPredicateDays);
        StartTimeWithinDaysPredicate secondPredicate = new StartTimeWithinDaysPredicate(
                CLOCK_FIXED_AT_TIME_NOW, secondPredicateDays);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StartTimeWithinDaysPredicate firstPredicateCopy = new StartTimeWithinDaysPredicate(
                CLOCK_FIXED_AT_TIME_NOW, firstPredicateDays);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_isWithinDays_returnsTrue() {
        StartTimeWithinDaysPredicate predicate = new StartTimeWithinDaysPredicate(CLOCK_FIXED_AT_TIME_NOW, 3);

        // 1 March 2023 to 1 March 2023 is <= 3 days
        assertTrue(predicate.test(new EventBuilder().withStartTime("01-03-2023 11:00").build()));

        // 1 March 2023 to 4 March 2023 is <= 3 days
        assertTrue(predicate.test(new EventBuilder().withStartTime("04-03-2023 23:59").build()));

    }

    @Test
    public void test_isNotWithinDays_returnsFalse() {
        StartTimeWithinDaysPredicate predicate = new StartTimeWithinDaysPredicate(CLOCK_FIXED_AT_TIME_NOW, 3);

        // 1 March 2023 10:59 is before 1 March 2023 11:00
        assertFalse(predicate.test(new EventBuilder().withStartTime("01-03-2023 10:59").build()));

        // 5 March 2023 to 1 March 2023 > 3 days
        assertFalse(predicate.test(new EventBuilder().withStartTime("05-03-2023 00:00").build()));
    }
}
