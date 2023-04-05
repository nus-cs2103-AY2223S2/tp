package ezschedule.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ezschedule.testutil.EventBuilder;

public class EventContainsKeywordsPredicateTest {

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        EventContainsKeywordsPredicate predicate =
                new EventContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new EventBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new EventContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new EventBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new EventContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new EventBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new EventContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new EventBuilder().withName("Alice Bob").build()));

        // Partially matching one keyword
        predicate = new EventContainsKeywordsPredicate(Collections.singletonList("Al"));
        assertTrue(predicate.test(new EventBuilder().withName("Alice Bob").build()));

        // Only one partially matching keyword
        predicate = new EventContainsKeywordsPredicate(Arrays.asList("Al", "Bob"));
        assertTrue(predicate.test(new EventBuilder().withName("Alice Carol").build()));

        // Multiple partially matching keywords
        predicate = new EventContainsKeywordsPredicate(Arrays.asList("Al", "Bo"));
        assertTrue(predicate.test(new EventBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EventContainsKeywordsPredicate predicate = new EventContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EventBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new EventContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new EventBuilder().withName("Alice Bob").build()));

        // Keywords match date, start time and end time, but does not match name
        predicate = new EventContainsKeywordsPredicate(Arrays.asList("2023-06-01", "18:00", "20:00"));
        assertFalse(predicate.test(new EventBuilder().withName("Alice").withDate("2023-06-01")
                .withStartTime("18:00").withEndTime("20:00").build()));
    }

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        EventContainsKeywordsPredicate firstPredicate = new EventContainsKeywordsPredicate(firstPredicateKeywordList);
        EventContainsKeywordsPredicate secondPredicate = new EventContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EventContainsKeywordsPredicate firstPredicateCopy =
                new EventContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different event -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
