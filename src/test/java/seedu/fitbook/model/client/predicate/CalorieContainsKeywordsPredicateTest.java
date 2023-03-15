package seedu.fitbook.model.client.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.fitbook.testutil.client.ClientBuilder;

public class CalorieContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        CalorieContainsKeywordsPredicate firstPredicate = new CalorieContainsKeywordsPredicate(
                firstPredicateKeywordList);
        CalorieContainsKeywordsPredicate secondPredicate = new CalorieContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CalorieContainsKeywordsPredicate firstPredicateCopy = new CalorieContainsKeywordsPredicate(
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
    public void test_calorieContainsKeywords_returnsTrue() {
        // One keyword
        CalorieContainsKeywordsPredicate predicate = new CalorieContainsKeywordsPredicate(Collections
                .singletonList("1000"));
        assertTrue(predicate.test(new ClientBuilder().withCalorie("1000").build()));
    }

    @Test
    public void test_calorieDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CalorieContainsKeywordsPredicate predicate = new CalorieContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ClientBuilder().withCalorie("1000").build()));

        // Non-matching keyword
        predicate = new CalorieContainsKeywordsPredicate(Arrays.asList("1200"));
        assertFalse(predicate.test(new ClientBuilder().withName("1000").build()));
    }
}
