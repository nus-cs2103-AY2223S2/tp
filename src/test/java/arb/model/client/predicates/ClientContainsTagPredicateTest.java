package arb.model.client.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import arb.testutil.ClientBuilder;
import arb.testutil.PredicateUtil;

public class ClientContainsTagPredicateTest {

    @Test
    public void equals() {
        ClientContainsTagsPredicate firstPredicate = PredicateUtil.getClientContainsTagPredicate("first");
        ClientContainsTagsPredicate secondPredicate = PredicateUtil.getClientContainsTagPredicate("first", "second");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ClientContainsTagsPredicate firstPredicateCopy = PredicateUtil.getClientContainsTagPredicate("first");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tags -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        ClientContainsTagsPredicate secondPredicateCopy =
                PredicateUtil.getClientContainsTagPredicate("second", "first");
        assertTrue(secondPredicateCopy.equals(secondPredicate)); // different order -> returns true
    }

    @Test
    public void test_clientContainsTag_returnsTrue() {
        // One tag
        ClientContainsTagsPredicate predicate = PredicateUtil.getClientContainsTagPredicate("friend");
        assertTrue(predicate.test(new ClientBuilder().withTags("friend").build()));

        // Multiple tags
        predicate = PredicateUtil.getClientContainsTagPredicate("friend", "husband");
        assertTrue(predicate.test(new ClientBuilder().withTags("frined", "husband").build()));

        // Only one matching tag
        predicate = PredicateUtil.getClientContainsTagPredicate("friend", "husband");
        assertTrue(predicate.test(new ClientBuilder().withTags("friend").build()));

        // Mixed-case tags
        predicate = PredicateUtil.getClientContainsTagPredicate("frIenD", "huSBand");
        assertTrue(predicate.test(new ClientBuilder().withTags("friend", "husband").build()));
    }

    @Test
    public void test_clientDoesNotContainTags_returnsFalse() {
        // Zero tags
        ClientContainsTagsPredicate predicate = PredicateUtil.getClientContainsTagPredicate();
        assertFalse(predicate.test(new ClientBuilder().withTags("friend").build()));

        // Non-matching tag
        predicate = PredicateUtil.getClientContainsTagPredicate("husband");
        assertFalse(predicate.test(new ClientBuilder().withTags("friend").build()));

        // Tags match name but does not match tag
        predicate = PredicateUtil.getClientContainsTagPredicate("Alice");
        assertFalse(predicate.test(new ClientBuilder().withName("Alice").withTags("friend").build()));
    }
}
