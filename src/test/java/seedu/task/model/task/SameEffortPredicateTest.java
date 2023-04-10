package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.testutil.TypicalTasks.ALICE;
import static seedu.task.testutil.TypicalTasks.GEORGE;

import org.junit.jupiter.api.Test;

public class SameEffortPredicateTest {

    @Test
    public void sameEffort() {
        Effort e = new Effort(24);
        SameEffortPredicate predicate = new SameEffortPredicate(e);

        // same effort -> return true
        assertTrue(predicate.test(GEORGE));

        // different effort -> return false
        assertFalse(predicate.test(ALICE));
    }

    @Test
    public void equals() {
        Effort e1 = new Effort(24);
        Effort e2 = new Effort(10);

        SameEffortPredicate firstPredicate = new SameEffortPredicate(e1);
        SameEffortPredicate secondPredicate = new SameEffortPredicate(e2);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        SameEffortPredicate firstPredicateCopy = new SameEffortPredicate(e1);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different effort -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
