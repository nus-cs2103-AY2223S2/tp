package trackr.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import trackr.testutil.OrderBuilder;
import trackr.testutil.OrderPredicateBuilder;

public class OrderNameContainsKeywordPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        OrderNameContainsKeywordPredicate firstPredicate =
                new OrderPredicateBuilder()
                        .withOrderNameKeywords(firstPredicateKeywordList)
                        .withOrderDeadline("01/01/2024")
                        .withOrderStatus("N")
                        .withOrderQuantity("10")
                        .withCustomerAddress("123 smith street")
                        .withCustomerName("John Doe")
                        .withCustomerPhone("12345678")
                        .build();

        OrderNameContainsKeywordPredicate secondPredicate;

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        OrderNameContainsKeywordPredicate firstPredicateCopy =
                        new OrderPredicateBuilder()
                                .withOrderNameKeywords(firstPredicateKeywordList)
                                .withOrderDeadline("01/01/2024")
                                .withOrderStatus("N")
                                .withOrderQuantity("10")
                                .withCustomerAddress("123 smith street")
                                .withCustomerName("John Doe")
                                .withCustomerPhone("12345678")
                                .build();

        assertTrue(firstPredicate.equals(firstPredicateCopy));

        firstPredicateCopy = new OrderPredicateBuilder(firstPredicate).build();
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        secondPredicate = new OrderPredicateBuilder().build();
        OrderNameContainsKeywordPredicate secondPredicateCopy = new OrderPredicateBuilder(secondPredicate).build();
        assertTrue(secondPredicate.equals(secondPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false

        secondPredicate =
                new OrderPredicateBuilder()
                        .withOrderNameKeywords(secondPredicateKeywordList)
                        .withOrderDeadline("01/01/2024")
                        .withOrderStatus("N")
                        .withOrderQuantity("10")
                        .withCustomerAddress("123 smith street")
                        .withCustomerName("John Doe")
                        .withCustomerPhone("12345678")
                        .build();

        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_oneVariableMatch_returnsTrue() {
        OrderNameContainsKeywordPredicate predicate;

        // One keyword
        predicate =
                new OrderPredicateBuilder().withOrderNameKeywords(Collections.singletonList("Buy")).build();
        assertTrue(predicate.test(new OrderBuilder().withOrderName("Buy Food").build()));

        // Multiple keywords
        predicate =
                new OrderPredicateBuilder().withOrderNameKeywords(Arrays.asList("Buy", "Food")).build();
        assertTrue(predicate.test(new OrderBuilder().withOrderName("Buy Food").build()));

        // Only one matching keyword
        predicate =
                new OrderPredicateBuilder().withOrderNameKeywords(Arrays.asList("Buy", "flour")).build();
        assertTrue(predicate.test(new OrderBuilder().withOrderName("Buy Food").build()));

        // Mixed-case keywords
        predicate =
                new OrderPredicateBuilder().withOrderNameKeywords(Arrays.asList("BuY", "FloUr")).build();
        assertTrue(predicate.test(new OrderBuilder().withOrderName("Buy Flour").build()));

        // Deadline only
        predicate =
                new OrderPredicateBuilder().withOrderDeadline("01/01/2023").build();
        assertTrue(predicate.test(new OrderBuilder().withOrderDeadline("01/01/2023").build()));

        // Status only
        predicate =
                new OrderPredicateBuilder().withOrderStatus("N").build();
        assertTrue(predicate.test(new OrderBuilder().withOrderStatus("N").build()));

        predicate =
                new OrderPredicateBuilder().withOrderQuantity("10").build();
        assertTrue(predicate.test(new OrderBuilder().withOrderQuantity("10").build()));

        predicate =
                new OrderPredicateBuilder().withCustomerName("JohnDoe").build();
        assertTrue(predicate.test(new OrderBuilder().withCustomerName("JohnDoe").build()));

        predicate =
                new OrderPredicateBuilder().withCustomerAddress("123 Smith Street").build();
        assertTrue(predicate.test(new OrderBuilder().withCustomerAddress("123 Smith Street").build()));

        predicate =
                new OrderPredicateBuilder().withCustomerPhone("12345678").build();
        assertTrue(predicate.test(new OrderBuilder().withCustomerPhone("12345678").build()));
    }
}
