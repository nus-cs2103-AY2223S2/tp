package seedu.powercards.model.deck;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class DeckContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DeckContainsKeywordsPredicate firstPredicate =
                new DeckContainsKeywordsPredicate(firstPredicateKeywordList);
        DeckContainsKeywordsPredicate secondPredicate =
                new DeckContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DeckContainsKeywordsPredicate firstPredicateCopy =
                new DeckContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different deck -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_deckContainsKeywords_returnsTrue() {
        // One keyword
        DeckContainsKeywordsPredicate predicate =
                new DeckContainsKeywordsPredicate(Collections.singletonList("science"));
        assertTrue(predicate.test(new Deck("Science")));

        // Multiple keywords
        predicate = new DeckContainsKeywordsPredicate(Arrays.asList("social", "studies"));
        assertTrue(predicate.test(new Deck("Social Studies")));

        // Only one matching keyword
        predicate = new DeckContainsKeywordsPredicate(Arrays.asList("asian", "studies"));
        assertTrue(predicate.test(new Deck("Asian History")));

        // Mixed-case keyword
        predicate = new DeckContainsKeywordsPredicate(Arrays.asList("sCienCE"));
        assertTrue(predicate.test(new Deck("Science")));
    }

    @Test
    public void test_deckDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DeckContainsKeywordsPredicate predicate = new DeckContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new Deck("Science")));

        // Non-matching keyword
        predicate = new DeckContainsKeywordsPredicate(Arrays.asList("Math"));
        assertFalse(predicate.test(new Deck("Science")));

    }
}
