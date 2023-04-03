package arb.model.client.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import arb.testutil.ClientBuilder;
import arb.testutil.PredicateUtil;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate = PredicateUtil.getNameContainsKeywordsPredicate("first");
        NameContainsKeywordsPredicate secondPredicate =
                PredicateUtil.getNameContainsKeywordsPredicate("first", "second");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = PredicateUtil.getNameContainsKeywordsPredicate("first");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        NameContainsKeywordsPredicate secondPredicateCopy =
                PredicateUtil.getNameContainsKeywordsPredicate("second", "first");
        assertTrue(secondPredicate.equals(secondPredicateCopy)); // different order -> returns true
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate =
                PredicateUtil.getNameContainsKeywordsPredicate("Alice");
        assertTrue(predicate.test(new ClientBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = PredicateUtil.getNameContainsKeywordsPredicate("Alice", "Bob");
        assertTrue(predicate.test(new ClientBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = PredicateUtil.getNameContainsKeywordsPredicate("Bob", "Carol");
        assertTrue(predicate.test(new ClientBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = PredicateUtil.getNameContainsKeywordsPredicate("aLIce", "bOB");
        assertTrue(predicate.test(new ClientBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = PredicateUtil.getNameContainsKeywordsPredicate();
        assertFalse(predicate.test(new ClientBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = PredicateUtil.getNameContainsKeywordsPredicate("Carol");
        assertFalse(predicate.test(new ClientBuilder().withName("Alice Bob").build()));

        // Keywords match phone, and email, but does not match name
        predicate = PredicateUtil.getNameContainsKeywordsPredicate("12345", "alice@email.com");
        assertFalse(predicate.test(new ClientBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").build()));
    }

    @Test
    public void keywordsToString() {
        NameContainsKeywordsPredicate predicate = PredicateUtil.getNameContainsKeywordsPredicate("a", "b", "c");
        assertTrue("a, b, c".equals(predicate.keywordsToString()));
    }
}
