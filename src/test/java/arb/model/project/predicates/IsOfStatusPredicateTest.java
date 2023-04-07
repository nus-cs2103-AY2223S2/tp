package arb.model.project.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import arb.testutil.PredicateUtil;
import arb.testutil.ProjectBuilder;

public class IsOfStatusPredicateTest {
    @Test
    public void equals() {
        IsOfStatusPredicate donePredicate = PredicateUtil.getIsOfStatusPredicate(true);
        IsOfStatusPredicate notDonePredicate = PredicateUtil.getIsOfStatusPredicate(false);

        // Same object
        assertTrue(donePredicate.equals(donePredicate));

        // Same value -> true
        IsOfStatusPredicate donePredicateCopy = PredicateUtil.getIsOfStatusPredicate(true);
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
        IsOfStatusPredicate donePredicate = PredicateUtil.getIsOfStatusPredicate(true);
        assertTrue(donePredicate.test(new ProjectBuilder().withStatus(true).build()));

        // Not done status
        IsOfStatusPredicate notDonePredicate = PredicateUtil.getIsOfStatusPredicate(false);
        assertTrue(notDonePredicate.test(new ProjectBuilder().withStatus(false).build()));
    }

    @Test
    public void test_projectDoesNotMatchStatus_returnsFalse() {
        // Done status
        IsOfStatusPredicate donePredicate = PredicateUtil.getIsOfStatusPredicate(true);
        assertFalse(donePredicate.test(new ProjectBuilder().withStatus(false).build()));

        // Not done status
        IsOfStatusPredicate notDonePredicate = PredicateUtil.getIsOfStatusPredicate(false);
        assertFalse(notDonePredicate.test(new ProjectBuilder().withStatus(true).build()));
    }
}
