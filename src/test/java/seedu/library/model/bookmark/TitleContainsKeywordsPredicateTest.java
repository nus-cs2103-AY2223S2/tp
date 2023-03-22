package seedu.library.model.bookmark;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.library.testutil.BookmarkBuilder;

public class TitleContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstTitleKeywordList = Collections.singletonList("first");
        List<String> secondTitleKeywordList = Arrays.asList("first", "second");

        TitleContainsKeywordsPredicate firstPredicate =
                new TitleContainsKeywordsPredicate(firstTitleKeywordList, null, null, null);
        TitleContainsKeywordsPredicate secondPredicate =
                new TitleContainsKeywordsPredicate(secondTitleKeywordList, null, null, null);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TitleContainsKeywordsPredicate firstPredicateCopy =
                new TitleContainsKeywordsPredicate(firstTitleKeywordList, null, null, null);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different bookmark -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        TitleContainsKeywordsPredicate predicate = new TitleContainsKeywordsPredicate(
                Collections.singletonList("Alice"), null, null, null
        );
        assertTrue(predicate.test(new BookmarkBuilder().withTitle("Alice Bob").build()));

        // Multiple keywords
        predicate = new TitleContainsKeywordsPredicate(
                Arrays.asList("Alice", "Bob"), null, null, null);
        assertTrue(predicate.test(new BookmarkBuilder().withTitle("Alice Bob").build()));

        // Mixed-case keywords
        predicate = new TitleContainsKeywordsPredicate(
                Arrays.asList("aLIce", "bOB"), null, null, null);
        assertTrue(predicate.test(new BookmarkBuilder().withTitle("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        TitleContainsKeywordsPredicate predicate =
                new TitleContainsKeywordsPredicate(Arrays.asList("Carol"), null, null, null);
        assertFalse(predicate.test(new BookmarkBuilder().withTitle("Alice Bob").build()));

        // Only one matching keyword
        predicate = new TitleContainsKeywordsPredicate(
                Arrays.asList("Bob", "Carol"), null, null, null);
        assertFalse(predicate.test(new BookmarkBuilder().withTitle("Alice Carol").build()));
    }
}
