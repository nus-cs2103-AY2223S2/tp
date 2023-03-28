package arb.model.project.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import arb.model.client.Client;
import arb.testutil.ClientBuilder;
import arb.testutil.ProjectBuilder;

public class LinkedClientNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        LinkedClientNameContainsKeywordsPredicate firstPredicate = new LinkedClientNameContainsKeywordsPredicate(
                firstPredicateKeywordList);
        LinkedClientNameContainsKeywordsPredicate secondPredicate = new LinkedClientNameContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        LinkedClientNameContainsKeywordsPredicate firstPredicateCopy =
                new LinkedClientNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        LinkedClientNameContainsKeywordsPredicate secondPredicateCopy =
                new LinkedClientNameContainsKeywordsPredicate(Arrays.asList("second", "first"));
        assertTrue(secondPredicate.equals(secondPredicateCopy)); // different order
    }

    @Test
    public void test_linkedClientNameContainsKeywords_returnsTrue() {
        Client linkedClient = new ClientBuilder().withName("Alice Fuller").build();

        // One keyword
        LinkedClientNameContainsKeywordsPredicate predicate =
                new LinkedClientNameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new ProjectBuilder().withLinkedClient(linkedClient).build()));

        // Multiple keywords
        predicate = new LinkedClientNameContainsKeywordsPredicate(Arrays.asList("Alice", "Fuller"));
        assertTrue(predicate.test(new ProjectBuilder().withLinkedClient(linkedClient).build()));

        // Only one matching keyword
        predicate = new LinkedClientNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new ProjectBuilder().withLinkedClient(linkedClient).build()));

        // Mixed-case keywords
        predicate = new LinkedClientNameContainsKeywordsPredicate(Arrays.asList("alIcE", "fULleR"));
        assertTrue(predicate.test(new ProjectBuilder().withLinkedClient(linkedClient).build()));
    }

    @Test
    public void test_linkedClientNameDoesNotContainKeywords_returnsFalse() {
        Client linkedClient = new ClientBuilder().withName("Alice Fuller").build();

        // Zero keywords
        LinkedClientNameContainsKeywordsPredicate predicate =
                new LinkedClientNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ProjectBuilder().withLinkedClient(linkedClient).build()));

        // Non-matching keyword
        predicate = new LinkedClientNameContainsKeywordsPredicate(Arrays.asList("Bob"));
        assertFalse(predicate.test(new ProjectBuilder().withLinkedClient(linkedClient).build()));

        // Keywords match title but does not match linked client name
        predicate = new LinkedClientNameContainsKeywordsPredicate(Arrays.asList("Sky"));
        assertFalse(predicate.test(new ProjectBuilder().withTitle("Sky Painting").withLinkedClient(linkedClient)
                .build()));
    }
}
