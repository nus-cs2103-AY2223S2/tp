package ezschedule.model.event;

import static ezschedule.logic.commands.CommandTestUtil.VALID_DATE_A;
import static ezschedule.logic.commands.CommandTestUtil.VALID_DATE_B;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ezschedule.testutil.EventBuilder;

public class EventMatchesDatePredicateTest {

    @Test
    public void test_equalDate_returnsTrue() {
        // equal date
        EventMatchesDatePredicate predicate = new EventMatchesDatePredicate(new Date(VALID_DATE_A));
        assertTrue(predicate.test(new EventBuilder().withDate(VALID_DATE_A).build()));
    }

    @Test
    public void test_notEqualDate_returnsFalse() {
        // not equal date
        EventMatchesDatePredicate predicate = new EventMatchesDatePredicate(new Date(VALID_DATE_A));
        assertFalse(predicate.test(new EventBuilder().withDate(VALID_DATE_B).build()));
    }

    @Test
    public void equals() {
        Date firstPredicateDate = new Date(VALID_DATE_A);
        Date secondPredicateDate = new Date(VALID_DATE_B);

        EventMatchesDatePredicate firstPredicate = new EventMatchesDatePredicate(firstPredicateDate);
        EventMatchesDatePredicate secondPredicate = new EventMatchesDatePredicate(secondPredicateDate);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EventMatchesDatePredicate firstPredicateCopy = new EventMatchesDatePredicate(firstPredicateDate);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different event -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
