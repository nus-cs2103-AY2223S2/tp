package seedu.fitbook.model.client.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.fitbook.testutil.client.ClientBuilder;

public class WeightContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        WeightContainsKeywordsPredicate firstPredicate = new WeightContainsKeywordsPredicate(firstPredicateKeywordList);
        WeightContainsKeywordsPredicate secondPredicate = new WeightContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        WeightContainsKeywordsPredicate firstPredicateCopy = new WeightContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different client -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_weightContainsKeywords_returnsTrue() {
        // One keyword
        WeightContainsKeywordsPredicate predicate = new WeightContainsKeywordsPredicate(Collections
                .singletonList("30"));
        assertTrue(predicate.test(new ClientBuilder().withWeight("30").build()));
    }

    @Test
    public void test_weightDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ClientBuilder().withWeight("30").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("30"));
        assertFalse(predicate.test(new ClientBuilder().withAddress("20").build()));
    }
}
