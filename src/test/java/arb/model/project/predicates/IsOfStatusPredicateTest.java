package arb.model.project.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import arb.model.project.Status;
import arb.testutil.ProjectBuilder;

public class IsOfStatusPredicateTest {
    @Test
    public void equals() {
        IsOfStatusPredicate donePredicate = new IsOfStatusPredicate(new Status(true));
        IsOfStatusPredicate notDonePredicate = new IsOfStatusPredicate(new Status(false));

        // Same object
        assertTrue(donePredicate.equals(donePredicate));

        // Same value -> true
        IsOfStatusPredicate donePredicateCopy = new IsOfStatusPredicate(new Status(true));
        assertTrue(donePredicate.equals(donePredicateCopy));

        // null -> false
        assertFalse(donePredicate.equals(null));

        // Different type -> false
        assertFalse(donePredicate.equals(3));

        // Different values -> false
        assertFalse(donePredicate.equals(notDonePredicate));
    }

    @Test
    public void test_projectMatchesStatus_returnsTrue() {
        // Done status
        IsOfStatusPredicate donePredicate = new IsOfStatusPredicate(new Status(true));
        assertTrue(donePredicate.test(new ProjectBuilder().withStatus(true).build()));

        // Not done status
        IsOfStatusPredicate notDonePredicate = new IsOfStatusPredicate(new Status(false));
        assertTrue(notDonePredicate.test(new ProjectBuilder().withStatus(false).build()));
    }

    @Test
    public void test_projectDoesNotMatchStatus_returnsFalse() {
        // Done status
        IsOfStatusPredicate donePredicate = new IsOfStatusPredicate(new Status(true));
        assertFalse(donePredicate.test(new ProjectBuilder().withStatus(false).build()));

        // Not done status
        IsOfStatusPredicate notDonePredicate = new IsOfStatusPredicate(new Status(false));
        assertFalse(notDonePredicate.test(new ProjectBuilder().withStatus(true).build()));
    }
}
