package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

public class EventNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        EventNameContainsKeywordsPredicate firstPredicate =
                new EventNameContainsKeywordsPredicate(firstPredicateKeywordList);
        EventNameContainsKeywordsPredicate secondPredicate =
                new EventNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EventNameContainsKeywordsPredicate firstPredicateCopy =
                new EventNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        EventNameContainsKeywordsPredicate predicate =
                new EventNameContainsKeywordsPredicate(Collections.singletonList("Party"));
        assertTrue(predicate.test(new EventBuilder().withName("Birthday Party").build()));

        // Multiple keywords
        predicate = new EventNameContainsKeywordsPredicate(Arrays.asList("Wedding", "Dinner"));
        assertTrue(predicate.test(new EventBuilder().withName("Wedding Dinner").build()));

        // Only one matching keyword
        predicate = new EventNameContainsKeywordsPredicate(Arrays.asList("Wedding", "Dinner"));
        assertTrue(predicate.test(new EventBuilder().withName("Fundraiser Dinner").build()));

        // Mixed-case keywords
        predicate = new EventNameContainsKeywordsPredicate(Arrays.asList("bIrThdaY", "pARty"));
        assertTrue(predicate.test(new EventBuilder().withName("Birthday Party").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EventNameContainsKeywordsPredicate predicate =
                new EventNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EventBuilder().withName("Party").build()));

        // Non-matching keyword
        predicate = new EventNameContainsKeywordsPredicate(Arrays.asList("Party"));
        assertFalse(predicate.test(new EventBuilder().withName("Wedding Dinner").build()));
    }
}
