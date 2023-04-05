package arb.model.project.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import arb.model.client.Client;
import arb.testutil.ClientBuilder;
import arb.testutil.PredicateUtil;
import arb.testutil.ProjectBuilder;

public class LinkedClientNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {

        LinkedClientNameContainsKeywordsPredicate firstPredicate =
                PredicateUtil.getLinkedClientNameContainsKeywordsPredicate("first");
        LinkedClientNameContainsKeywordsPredicate secondPredicate =
                PredicateUtil.getLinkedClientNameContainsKeywordsPredicate("first", "second");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        LinkedClientNameContainsKeywordsPredicate firstPredicateCopy =
                PredicateUtil.getLinkedClientNameContainsKeywordsPredicate("first");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        LinkedClientNameContainsKeywordsPredicate secondPredicateCopy =
                PredicateUtil.getLinkedClientNameContainsKeywordsPredicate("second", "first");
        assertTrue(secondPredicate.equals(secondPredicateCopy)); // different order
    }

    @Test
    public void test_linkedClientNameContainsKeywords_returnsTrue() {
        Client linkedClient = new ClientBuilder().withName("Alice Fuller").build();

        // One keyword
        LinkedClientNameContainsKeywordsPredicate predicate =
                PredicateUtil.getLinkedClientNameContainsKeywordsPredicate("Alice");
        assertTrue(predicate.test(new ProjectBuilder().withLinkedClient(linkedClient).build()));

        // Multiple keywords
        predicate = PredicateUtil.getLinkedClientNameContainsKeywordsPredicate("Alice", "Fuller");
        assertTrue(predicate.test(new ProjectBuilder().withLinkedClient(linkedClient).build()));

        // Only one matching keyword
        predicate = PredicateUtil.getLinkedClientNameContainsKeywordsPredicate("Alice", "Bob");
        assertTrue(predicate.test(new ProjectBuilder().withLinkedClient(linkedClient).build()));

        // Mixed-case keywords
        predicate = PredicateUtil.getLinkedClientNameContainsKeywordsPredicate("alIcE", "fULleR");
        assertTrue(predicate.test(new ProjectBuilder().withLinkedClient(linkedClient).build()));
    }

    @Test
    public void test_linkedClientNameDoesNotContainKeywords_returnsFalse() {
        Client linkedClient = new ClientBuilder().withName("Alice Fuller").build();

        // Zero keywords
        LinkedClientNameContainsKeywordsPredicate predicate =
                PredicateUtil.getLinkedClientNameContainsKeywordsPredicate();
        assertFalse(predicate.test(new ProjectBuilder().withLinkedClient(linkedClient).build()));

        // Non-matching keyword
        predicate = PredicateUtil.getLinkedClientNameContainsKeywordsPredicate("Bob");
        assertFalse(predicate.test(new ProjectBuilder().withLinkedClient(linkedClient).build()));

        // Keywords match title but does not match linked client name
        predicate = PredicateUtil.getLinkedClientNameContainsKeywordsPredicate("Sky");
        assertFalse(predicate.test(new ProjectBuilder().withTitle("Sky Painting").withLinkedClient(linkedClient)
                .build()));
    }
}
