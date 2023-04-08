package ezschedule.model.event;

import static ezschedule.logic.commands.CommandTestUtil.VALID_DATE_A;
import static ezschedule.logic.commands.CommandTestUtil.VALID_DATE_B;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ezschedule.testutil.EventBuilder;

public class EventMatchesKeywordsAndDatePredicateTest {

    @Test
    public void test_nameContainsKeywordsWithMatchingDate_returnsTrue() {
        // One keyword
        EventMatchesKeywordsAndDatePredicate predicate =
                new EventMatchesKeywordsAndDatePredicate(Collections.singletonList("Alice"), new Date(VALID_DATE_A));
        assertTrue(predicate.test(new EventBuilder().withName("Alice Bob").withDate(VALID_DATE_A).build()));

        // Multiple keywords
        predicate = new EventMatchesKeywordsAndDatePredicate(Arrays.asList("Alice", "Bob"), new Date(VALID_DATE_A));
        assertTrue(predicate.test(new EventBuilder().withName("Alice Bob").withDate(VALID_DATE_A).build()));

        // Only one matching keyword
        predicate = new EventMatchesKeywordsAndDatePredicate(Arrays.asList("Bob", "Carol"), new Date(VALID_DATE_A));
        assertTrue(predicate.test(new EventBuilder().withName("Alice Carol").withDate(VALID_DATE_A).build()));

        // Mixed-case keywords
        predicate = new EventMatchesKeywordsAndDatePredicate(Arrays.asList("aLIce", "bOB"), new Date(VALID_DATE_A));
        assertTrue(predicate.test(new EventBuilder().withName("Alice Bob").withDate(VALID_DATE_A).build()));

        // Partially matching one keyword
        predicate = new EventMatchesKeywordsAndDatePredicate(Collections.singletonList("Al"), new Date(VALID_DATE_A));
        assertTrue(predicate.test(new EventBuilder().withName("Alice Bob").withDate(VALID_DATE_A).build()));

        // Only one partially matching keyword
        predicate = new EventMatchesKeywordsAndDatePredicate(Arrays.asList("Al", "Bob"), new Date(VALID_DATE_A));
        assertTrue(predicate.test(new EventBuilder().withName("Alice Carol").withDate(VALID_DATE_A).build()));

        // Multiple partially matching keywords
        predicate = new EventMatchesKeywordsAndDatePredicate(Arrays.asList("Al", "Bo"), new Date(VALID_DATE_A));
        assertTrue(predicate.test(new EventBuilder().withName("Alice Bob").withDate(VALID_DATE_A).build()));
    }

    @Test
    public void test_nameDoesNotContainKeywordsWithMatchingDate_returnsFalse() {
        // Zero keywords
        EventMatchesKeywordsAndDatePredicate predicate =
                new EventMatchesKeywordsAndDatePredicate(Collections.emptyList(), new Date(VALID_DATE_A));
        assertFalse(predicate.test(new EventBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new EventMatchesKeywordsAndDatePredicate(Arrays.asList("Carol"), new Date(VALID_DATE_A));
        assertFalse(predicate.test(new EventBuilder().withName("Alice Bob").build()));

        // Keywords match date, start time and end time, but does not match name
        predicate = new EventMatchesKeywordsAndDatePredicate(Arrays.asList("2023-06-01", "18:00", "20:00"),
                new Date(VALID_DATE_A));
        assertFalse(predicate.test(new EventBuilder().withName("Alice").withDate(VALID_DATE_A)
                .withStartTime("18:00").withEndTime("20:00").build()));
    }

    @Test
    public void test_nameContainsKeywordsWithNonMatchingDate_returnsFalse() {
        // One keyword
        EventMatchesKeywordsAndDatePredicate predicate =
                new EventMatchesKeywordsAndDatePredicate(Collections.singletonList("Alice"), new Date(VALID_DATE_A));
        assertFalse(predicate.test(new EventBuilder().withName("Alice Bob").withDate(VALID_DATE_B).build()));

        // Multiple keywords
        predicate = new EventMatchesKeywordsAndDatePredicate(Arrays.asList("Alice", "Bob"), new Date(VALID_DATE_A));
        assertFalse(predicate.test(new EventBuilder().withName("Alice Bob").withDate(VALID_DATE_B).build()));

        // Only one matching keyword
        predicate = new EventMatchesKeywordsAndDatePredicate(Arrays.asList("Bob", "Carol"), new Date(VALID_DATE_A));
        assertFalse(predicate.test(new EventBuilder().withName("Alice Carol").withDate(VALID_DATE_B).build()));

        // Mixed-case keywords
        predicate = new EventMatchesKeywordsAndDatePredicate(Arrays.asList("aLIce", "bOB"), new Date(VALID_DATE_A));
        assertFalse(predicate.test(new EventBuilder().withName("Alice Bob").withDate(VALID_DATE_B).build()));

        // Partially matching one keyword
        predicate = new EventMatchesKeywordsAndDatePredicate(Collections.singletonList("Al"), new Date(VALID_DATE_A));
        assertFalse(predicate.test(new EventBuilder().withName("Alice Bob").withDate(VALID_DATE_B).build()));

        // Only one partially matching keyword
        predicate = new EventMatchesKeywordsAndDatePredicate(Arrays.asList("Al", "Bob"), new Date(VALID_DATE_A));
        assertFalse(predicate.test(new EventBuilder().withName("Alice Carol").withDate(VALID_DATE_B).build()));

        // Multiple partially matching keywords
        predicate = new EventMatchesKeywordsAndDatePredicate(Arrays.asList("Al", "Bo"), new Date(VALID_DATE_A));
        assertFalse(predicate.test(new EventBuilder().withName("Alice Bob").withDate(VALID_DATE_B).build()));
    }

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        Date firstPredicateDate = new Date(VALID_DATE_A);
        Date secondPredicateDate = new Date(VALID_DATE_B);

        EventMatchesKeywordsAndDatePredicate firstPredicate =
                new EventMatchesKeywordsAndDatePredicate(firstPredicateKeywordList, firstPredicateDate);
        EventMatchesKeywordsAndDatePredicate secondPredicate =
                new EventMatchesKeywordsAndDatePredicate(secondPredicateKeywordList, secondPredicateDate);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EventMatchesKeywordsAndDatePredicate firstPredicateCopy =
                new EventMatchesKeywordsAndDatePredicate(firstPredicateKeywordList, firstPredicateDate);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different event -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
