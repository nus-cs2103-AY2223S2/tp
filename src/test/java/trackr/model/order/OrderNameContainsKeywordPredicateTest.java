package trackr.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import trackr.testutil.OrderBuilder;

public class OrderNameContainsKeywordPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        OrderNameContainsKeywordPredicate firstPredicate =
                new OrderNameContainsKeywordPredicate(firstPredicateKeywordList);
        OrderNameContainsKeywordPredicate secondPredicate =
                new OrderNameContainsKeywordPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        OrderNameContainsKeywordPredicate firstPredicateCopy =
                new OrderNameContainsKeywordPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_orderNameContainsKeywords_returnsTrue() {
        // One keyword
        OrderNameContainsKeywordPredicate predicate =
                new OrderNameContainsKeywordPredicate(Collections.singletonList("Chocolate"));
        assertTrue(predicate.test(new OrderBuilder().withOrderName("Chocolate Cookies").build()));

        // Multiple keywords
        predicate = new OrderNameContainsKeywordPredicate(Arrays.asList("Chocolate", "Cookies"));
        assertTrue(predicate.test(new OrderBuilder().withOrderName("Chocolate Cookies").build()));

        // Only one matching keyword
        predicate = new OrderNameContainsKeywordPredicate(Arrays.asList("Vanilla", "Cookies"));
        assertTrue(predicate.test(new OrderBuilder().withOrderName("Chocolate Cookies").build()));

        // Mixed-case keywords
        predicate = new OrderNameContainsKeywordPredicate(Arrays.asList("ChOcOlAte", "cooKiEs"));
        assertTrue(predicate.test(new OrderBuilder().withOrderName("Chocolate Cookies").build()));
    }

    @Test
    public void test_orderNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        OrderNameContainsKeywordPredicate predicate = new OrderNameContainsKeywordPredicate(Collections.emptyList());
        assertFalse(predicate.test(new OrderBuilder().withOrderName("Chocolate Cookies").build()));

        // Non-matching keyword
        predicate = new OrderNameContainsKeywordPredicate(Arrays.asList("Vanilla"));
        assertFalse(predicate.test(new OrderBuilder().withOrderName("Cheese cake").build()));

        // Keywords match deadline and status, but does not match name
        predicate = new OrderNameContainsKeywordPredicate(Arrays.asList("11/11/2024", "N"));
        assertFalse(predicate.test(new OrderBuilder().withOrderName("Chocolate Cookies")
                .withOrderDeadline("11/11/2024")
                .withOrderStatus("N").build()));
    }
}
