package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.application.InternshipStatus.ACCEPTED;
import static seedu.address.model.application.InternshipStatus.PENDING;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

public class StatusPredicateTest {
    @Test
    public void equals() {
        StatusPredicate firstPredicate = new StatusPredicate(ACCEPTED);
        StatusPredicate secondPredicate = new StatusPredicate(PENDING);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StatusPredicate firstPredicateCopy =
                new StatusPredicate(ACCEPTED);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different status -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_matchingStatus_returnsTrue() {
        StatusPredicate predicate =
                new StatusPredicate(PENDING);
        assertTrue(predicate.test(new InternshipBuilder().withStatus(PENDING).build()));
    }

    @Test
    public void test_mismatchStatus_returnsFalse() {
        StatusPredicate predicate =
                new StatusPredicate(ACCEPTED);
        assertFalse(predicate.test(new InternshipBuilder().withStatus(PENDING).build()));
    }
}
