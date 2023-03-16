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

        OrderContainsKeywordsPredicate firstPredicate =
                new OrderPredicateBuilder()
                        .withOrderNameKeywords(firstPredicateKeywordList)
                        .withOrderDeadline("01/01/2024")
                        .withOrderStatus("N")
                        .withOrderQuantity("10")
                        .withCustomerAddress("123 smith street")
                        .withCustomerName("John Doe")
                        .withCustomerPhone("12345678")
                        .build();

        OrderContainsKeywordsPredicate secondPredicate;

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        OrderContainsKeywordsPredicate firstPredicateCopy =
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
        OrderContainsKeywordsPredicate secondPredicateCopy = new OrderPredicateBuilder(secondPredicate).build();
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
        OrderContainsKeywordsPredicate predicate;

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

    @Test
    public void test_oneVariableDoesNotMatch_returnsFalse() {
        OrderContainsKeywordsPredicate predicate;
        Order testTask;

        // Zero keywords
        predicate =
                new OrderPredicateBuilder().withOrderNameKeywords(Collections.emptyList()).build();
        assertFalse(predicate.test(new OrderBuilder().withOrderName("Buy").build()));

        // Non-matching keyword
        predicate =
                new OrderPredicateBuilder().withOrderNameKeywords(Arrays.asList("Buy")).build();
        assertFalse(predicate.test(new OrderBuilder().withOrderName("Sort Inventory").build()));

        // Keywords match deadline and status, but does not match name
        predicate =
                new OrderPredicateBuilder().withOrderNameKeywords(Arrays.asList("11/11/2024", "N")).build();
        assertFalse(predicate.test(new OrderBuilder().withOrderName("Buy").withOrderDeadline("11/11/2024")
                .withOrderStatus("N").build()));

        // Non-matching deadline
        predicate =
                new OrderPredicateBuilder().withOrderDeadline("01/01/2023").build();
        assertFalse(predicate.test(new OrderBuilder().withOrderDeadline("11/11/2023").build()));

        // Match name and status, but not deadline
        predicate = new OrderPredicateBuilder()
                .withOrderNameKeywords(Arrays.asList("Buy", "Flour"))
                .withOrderStatus("N")
                .withOrderDeadline("01/01/2023")
                .build();
        testTask = new OrderBuilder()
                .withOrderName("Buy Flour")
                .withOrderStatus("N")
                .withOrderDeadline("11/11/2023")
                .build();
        assertTrue(predicate.test(testTask));

        // Non-matching status
        predicate =
                new OrderPredicateBuilder().withOrderStatus("N").build();
        assertFalse(predicate.test(new OrderBuilder().withOrderStatus("D").build()));

        // Match name and deadline, but not status
        predicate = new OrderPredicateBuilder()
                .withOrderNameKeywords(Arrays.asList("Buy", "Flour"))
                .withOrderStatus("N")
                .withOrderDeadline("01/01/2023")
                .build();
        testTask = new OrderBuilder()
                .withOrderName("Buy Flour")
                .withOrderStatus("D")
                .withOrderDeadline("01/01/2023")
                .build();
        assertTrue(predicate.test(testTask));
    }

    @Test
    public void test_twoVariableMatch_returnsTrue() {
        OrderContainsKeywordsPredicate predicate;

        // Same name and deadline
        predicate = new OrderPredicateBuilder()
                .withOrderDeadline("01/01/2023")
                .withOrderNameKeywords(Arrays.asList("Buy", "Food"))
                .build();
        assertTrue(predicate.test(new OrderBuilder().withOrderDeadline("01/01/2023")
                .withOrderName("Buy Food").build()));

        // Same deadline and status
        predicate = new OrderPredicateBuilder()
                .withOrderDeadline("01/01/2023")
                .withOrderStatus("N")
                .build();
        assertTrue(predicate.test(new OrderBuilder().withOrderDeadline("01/01/2023").withOrderStatus("N").build()));

        // Same name and status
        predicate = new OrderPredicateBuilder()
                .withOrderNameKeywords(Arrays.asList("Buy", "Food"))
                .withOrderStatus("N")
                .build();
        Order testTask = new OrderBuilder()
                .withOrderName("Buy Food")
                .withOrderStatus("N")
                .build();
        assertTrue(predicate.test(testTask));
    }

    @Test
    public void test_twoVariableDoesNotMatch_returnsFalse() {
        OrderContainsKeywordsPredicate predicate;
        Order testTask;

        // Same name, different deadline and status
        predicate = new OrderPredicateBuilder()
                .withOrderNameKeywords(Arrays.asList("Buy", "Food"))
                .withOrderStatus("D")
                .withOrderDeadline("11/11/2023")
                .build();
        testTask = new OrderBuilder()
                .withOrderName("Buy Food")
                .withOrderStatus("N")
                .withOrderDeadline("01/01/2023")
                .build();
        assertTrue(predicate.test(testTask));

        // Same deadline, different name and status
        predicate = new OrderPredicateBuilder()
                .withOrderNameKeywords(Arrays.asList("Sort", "Inventory"))
                .withOrderStatus("D")
                .withOrderDeadline("01/01/2023")
                .build();
        testTask = new OrderBuilder()
                .withOrderName("Buy Food")
                .withOrderStatus("N")
                .withOrderDeadline("01/01/2023")
                .build();
        assertFalse(predicate.test(testTask));

        // Same status, different name and deadline
        predicate = new OrderPredicateBuilder()
                .withOrderNameKeywords(Arrays.asList("Sort", "Inventory"))
                .withOrderStatus("N")
                .withOrderDeadline("01/01/2023")
                .build();
        testTask = new OrderBuilder()
                .withOrderName("Buy Food")
                .withOrderStatus("N")
                .withOrderDeadline("01/01/2023")
                .build();
        assertFalse(predicate.test(testTask));
    }

    @Test
    public void test_threeVariableMatch_returnsTrue() {
        OrderContainsKeywordsPredicate predicate;

        // Same name, deadline and status
        predicate = new OrderPredicateBuilder()
                .withOrderNameKeywords(Arrays.asList("Buy", "Food"))
                .withOrderStatus("N")
                .withOrderDeadline("01/01/2023")
                .build();
        Order testTask = new OrderBuilder()
                .withOrderName("Buy Food")
                .withOrderStatus("N")
                .withOrderDeadline("01/01/2023")
                .build();
        assertTrue(predicate.test(testTask));
    }

    @Test
    public void test_threeVariableDoesNotMatch_returnsFalse() {
        OrderContainsKeywordsPredicate predicate;

        // Different name, deadline and status
        predicate = new OrderPredicateBuilder()
                .withOrderNameKeywords(Arrays.asList("Sort", "Inventory"))
                .withOrderStatus("D")
                .withOrderDeadline("11/11/2023")
                .build();
        Order testTask = new OrderBuilder()
                .withOrderName("Buy Food")
                .withOrderStatus("N")
                .withOrderDeadline("01/01/2023")
                .build();
        assertFalse(predicate.test(testTask));
    }

    @Test
    public void test_atLeastOneFieldPresent_returnsTrue() {
        OrderContainsKeywordsPredicate predicate;

        // taskNameKeywords present
        predicate = new OrderPredicateBuilder()
                .withOrderNameKeywords(Arrays.asList("Sort", "Inventory"))
                .build();
        assertTrue(predicate.isAnyFieldPresent());

        // taskDeadline present
        predicate = new OrderPredicateBuilder()
                .withOrderDeadline("01/01/2022")
                .build();
        assertTrue(predicate.isAnyFieldPresent());

        // taskStatus present
        predicate = new OrderPredicateBuilder()
                .withOrderStatus("N")
                .build();
        assertTrue(predicate.isAnyFieldPresent());
    }

    @Test
    public void test_noFieldPresent_returnsFalse() {
        OrderContainsKeywordsPredicate predicate;

        // taskNameKeywords present
        predicate = new OrderPredicateBuilder().build();
        assertFalse(predicate.isAnyFieldPresent());
    }
}
