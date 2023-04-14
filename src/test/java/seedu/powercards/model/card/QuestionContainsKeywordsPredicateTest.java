package seedu.powercards.model.card;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.powercards.testutil.CardBuilder;

public class QuestionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        QuestionContainsKeywordsPredicate firstPredicate =
                new QuestionContainsKeywordsPredicate(firstPredicateKeywordList);
        QuestionContainsKeywordsPredicate secondPredicate =
                new QuestionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        QuestionContainsKeywordsPredicate firstPredicateCopy =
                new QuestionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different card -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_questionContainsKeywords_returnsTrue() {
        // One keyword
        QuestionContainsKeywordsPredicate predicate =
                new QuestionContainsKeywordsPredicate(Collections.singletonList("gravity"));
        assertTrue(predicate.test(new CardBuilder().withQuestion("What is gravity").build()));

        // Multiple keywords
        predicate = new QuestionContainsKeywordsPredicate(Arrays.asList("What", "gravity"));
        assertTrue(predicate.test(new CardBuilder().withQuestion("What is gravity").build()));

        // Only one matching keyword
        predicate = new QuestionContainsKeywordsPredicate(Arrays.asList("gravity", "photosynthesis"));
        assertTrue(predicate.test(new CardBuilder().withQuestion("What is photosynthesis").build()));

        // Mixed-case keywords
        predicate = new QuestionContainsKeywordsPredicate(Arrays.asList("wHaT", "gRaVIty"));
        assertTrue(predicate.test(new CardBuilder().withQuestion("What is gravity").build()));
    }

    @Test
    public void test_questionDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        QuestionContainsKeywordsPredicate predicate = new QuestionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new CardBuilder().withQuestion("What is gravity").build()));

        // Non-matching keyword
        predicate = new QuestionContainsKeywordsPredicate(Arrays.asList("photosynthesis"));
        assertFalse(predicate.test(new CardBuilder().withQuestion("What is gravity").build()));

        // Keywords match answer, but does not match question
        predicate = new QuestionContainsKeywordsPredicate(Arrays.asList("sunlight", "energy"));
        assertFalse(predicate.test(new CardBuilder().withQuestion("What is gravity")
                .withAnswer("The process by which plants convert sunlight into energy").build()));
    }
}
