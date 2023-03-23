package seedu.library.model.bookmark;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.library.testutil.BookmarkBuilder;

public class BookmarkContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstTitleKeywordList = Collections.singletonList("first");
        List<String> secondTitleKeywordList = Arrays.asList("first", "second");

        BookmarkContainsKeywordsPredicate firstPredicate =
                new BookmarkContainsKeywordsPredicate(firstTitleKeywordList, null, null, null);
        BookmarkContainsKeywordsPredicate secondPredicate =
                new BookmarkContainsKeywordsPredicate(secondTitleKeywordList, null, null, null);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BookmarkContainsKeywordsPredicate firstPredicateCopy =
                new BookmarkContainsKeywordsPredicate(firstTitleKeywordList, null, null, null);
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
        BookmarkContainsKeywordsPredicate predicate = new BookmarkContainsKeywordsPredicate(
                Collections.singletonList("Alice"), null, null, null
        );
        assertTrue(predicate.test(new BookmarkBuilder().withTitle("Alice Bob").build()));

        // Multiple keywords
        predicate = new BookmarkContainsKeywordsPredicate(
                Arrays.asList("Alice", "Bob"), null, null, null);
        assertTrue(predicate.test(new BookmarkBuilder().withTitle("Alice Bob").build()));

        // Mixed-case keywords
        predicate = new BookmarkContainsKeywordsPredicate(
                Arrays.asList("aLIce", "bOB"), null, null, null);
        assertTrue(predicate.test(new BookmarkBuilder().withTitle("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        BookmarkContainsKeywordsPredicate predicate =
                new BookmarkContainsKeywordsPredicate(Arrays.asList("Carol"), null, null, null);
        assertFalse(predicate.test(new BookmarkBuilder().withTitle("Alice Bob").build()));

        // Only one matching keyword
        predicate = new BookmarkContainsKeywordsPredicate(
                Arrays.asList("Bob", "Carol"), null, null, null);
        assertFalse(predicate.test(new BookmarkBuilder().withTitle("Alice Carol").build()));

    }
}
