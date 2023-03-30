package seedu.loyaltylift.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.testutil.OrderBuilder;

public class OrderStatusPredicateTest {

    @Test
    public void equals() {

        OrderStatusPredicate pendingPredicate = new OrderStatusPredicate(StatusValue.PENDING);
        OrderStatusPredicate completedPredicate = new OrderStatusPredicate(StatusValue.COMPLETED);

        // same object -> returns true
        assertTrue(pendingPredicate.equals(pendingPredicate));

        // same predicate -> returns true
        OrderStatusPredicate pendingPredicateCopy = new OrderStatusPredicate(StatusValue.PENDING);
        assertTrue(pendingPredicate.equals(pendingPredicateCopy));

        // different types -> returns false
        assertFalse(pendingPredicate.equals(1));

        // null -> returns false
        assertFalse(pendingPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(pendingPredicate.equals(completedPredicate));
    }

    @Test
    public void test_matchingStatus_returnsTrue() {
        OrderStatusPredicate pendingPredicate = new OrderStatusPredicate(StatusValue.PENDING);
        assertTrue(pendingPredicate.test(new OrderBuilder().build()));

        OrderStatusPredicate paidPredicate = new OrderStatusPredicate(StatusValue.PAID);
        assertTrue(paidPredicate.test(new OrderBuilder().withNextStatus("2023/01/01").build()));
    }

    @Test
    public void test_nonMatchingStatus_returnsFalse() {
        // earlier status
        OrderStatusPredicate paidPredicate = new OrderStatusPredicate(StatusValue.PAID);
        assertFalse(paidPredicate.test(new OrderBuilder().build()));

        // later status
        OrderStatusPredicate pendingPredicate = new OrderStatusPredicate(StatusValue.PENDING);
        assertFalse(pendingPredicate.test(new OrderBuilder().withNextStatus("2023/01/01").build()));
    }
}
