package ezschedule.model.event;

import static ezschedule.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UpcomingEventPredicateTest {

    @Test
    public void constructor() {
        // Should be able to construct without any errors
        // if error -> test failed
        new UpcomingEventPredicate();
    }

    @Test
    public void constructor_validCount_success() {
        // Should be able to construct without any errors
        // if error -> test failed
        new UpcomingEventPredicate(1);
        new UpcomingEventPredicate(2);
        new UpcomingEventPredicate(30);
        new UpcomingEventPredicate(404);
    }

    @Test
    public void constructor_invalidCount_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new UpcomingEventPredicate(0));
        assertThrows(IllegalArgumentException.class, () -> new UpcomingEventPredicate(-1));
        assertThrows(IllegalArgumentException.class, () -> new UpcomingEventPredicate(-50));
    }

    @Test
    public void equals() {
        final int firstCount = 1;
        final int secondCount = 2;

        UpcomingEventPredicate firstPredicate = new UpcomingEventPredicate(firstCount);
        UpcomingEventPredicate secondPredicate = new UpcomingEventPredicate(secondCount);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        UpcomingEventPredicate firstPredicateCopy = new UpcomingEventPredicate(firstCount);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals("some other type"));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different event -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

}
