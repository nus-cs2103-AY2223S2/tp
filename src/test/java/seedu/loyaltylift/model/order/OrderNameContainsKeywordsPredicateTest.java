package seedu.loyaltylift.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.testutil.OrderBuilder;

public class OrderNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        OrderNameContainsKeywordsPredicate firstPredicate = new OrderNameContainsKeywordsPredicate(
                firstPredicateKeywordList);
        OrderNameContainsKeywordsPredicate secondPredicate = new OrderNameContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        OrderNameContainsKeywordsPredicate firstPredicateCopy = new OrderNameContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different order -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        OrderNameContainsKeywordsPredicate predicate = new OrderNameContainsKeywordsPredicate(
                Collections.singletonList("Strawberry"));
        assertTrue(predicate.test(new OrderBuilder().withName("Strawberry Shortcake").build()));

        // Multiple keywords
        predicate = new OrderNameContainsKeywordsPredicate(Arrays.asList("Strawberry", "Shortcake"));
        assertTrue(predicate.test(new OrderBuilder().withName("Strawberry Shortcake").build()));

        // Only one matching keyword
        predicate = new OrderNameContainsKeywordsPredicate(Arrays.asList("Strawberry", "Melon"));
        assertTrue(predicate.test(new OrderBuilder().withName("Strawberry Shortcake").build()));

        // Mixed-case keywords
        predicate = new OrderNameContainsKeywordsPredicate(Arrays.asList("stRAwBerRy", "ShOrTcakE"));
        assertTrue(predicate.test(new OrderBuilder().withName("Strawberry Shortcake").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        OrderNameContainsKeywordsPredicate predicate = new OrderNameContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new OrderBuilder().withName("Strawberry Shortcake").build()));

        // Non-matching keyword
        predicate = new OrderNameContainsKeywordsPredicate(Arrays.asList("Melon"));
        assertFalse(predicate.test(new OrderBuilder().withName("Strawberry Shortcake").build()));

        // Keywords match address, but does not match name
        predicate = new OrderNameContainsKeywordsPredicate(Arrays.asList("Main", "Street"));
        assertFalse(predicate.test(new OrderBuilder().withName("Strawberry Shortcake")
                .withAddress("Main Street").build()));
    }
}
