package seedu.fitbook.model.client.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.fitbook.testutil.client.ClientBuilder;

public class GoalContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        GoalContainsKeywordsPredicate firstPredicate = new GoalContainsKeywordsPredicate(firstPredicateKeywordList);
        GoalContainsKeywordsPredicate secondPredicate = new GoalContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GoalContainsKeywordsPredicate firstPredicateCopy = new GoalContainsKeywordsPredicate(
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
    public void test_goalContainsKeywords_returnsTrue() {
        // One keyword
        GoalContainsKeywordsPredicate predicate = new GoalContainsKeywordsPredicate(Collections
                .singletonList("lose weight"));
        assertTrue(predicate.test(new ClientBuilder().withGoal("lose weight").build()));

        // Mixed-case keywords
        predicate = new GoalContainsKeywordsPredicate(Arrays.asList("LoSe WEIght"));
        assertTrue(predicate.test(new ClientBuilder().withGoal("lose weight").build()));
    }

    @Test
    public void test_goalDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        GoalContainsKeywordsPredicate predicate = new GoalContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ClientBuilder().withGoal("lose weight").build()));

        // Non-matching keyword
        predicate = new GoalContainsKeywordsPredicate(Arrays.asList("gain weight"));
        assertFalse(predicate.test(new ClientBuilder().withGoal("be fitter").build()));
    }
}
