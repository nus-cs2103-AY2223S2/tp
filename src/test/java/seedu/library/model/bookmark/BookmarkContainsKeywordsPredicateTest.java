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
    public void test_titleContainsKeywords_returnsTrue() {
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
    public void test_titleDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        BookmarkContainsKeywordsPredicate predicate =
                new BookmarkContainsKeywordsPredicate(Arrays.asList("Carol"), null, null, null);
        assertFalse(predicate.test(new BookmarkBuilder().withTitle("Alice Bob").build()));

        // Only one matching keyword
        predicate = new BookmarkContainsKeywordsPredicate(
                Arrays.asList("Bob", "Carol"), null, null, null);
        assertFalse(predicate.test(new BookmarkBuilder().withTitle("Alice Carol").build()));

    }

    @Test
    public void test_genreContainsKeywords_returnsTrue() {
        // One keyword
        BookmarkContainsKeywordsPredicate predicate = new BookmarkContainsKeywordsPredicate(
                null, Collections.singletonList("Fantasy"), null, null
        );
        assertTrue(predicate.test(new BookmarkBuilder().withGenre("Fantasy").build()));
    }

    @Test
    public void test_genreContainsKeywords_returnsFalse() {
        // Non-matching keyword
        BookmarkContainsKeywordsPredicate predicate = new BookmarkContainsKeywordsPredicate(
                null, Collections.singletonList("Fantasy"), null, null
        );
        assertFalse(predicate.test(new BookmarkBuilder().withGenre("Action").build()));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        BookmarkContainsKeywordsPredicate predicate = new BookmarkContainsKeywordsPredicate(
                null, null, Collections.singletonList("Hunters"), null
        );
        assertTrue(predicate.test(new BookmarkBuilder().withTags("Hunters").build()));
    }

    @Test
    public void test_tagContainsKeywords_returnsFalse() {
        // Non-matching keyword
        BookmarkContainsKeywordsPredicate predicate = new BookmarkContainsKeywordsPredicate(
                null, null, Collections.singletonList("Hunters"), null
        );
        assertFalse(predicate.test(new BookmarkBuilder().withTags("Gore").build()));
    }

    @Test
    public void test_authorContainsKeywords_returnsTrue() {
        // One keyword
        BookmarkContainsKeywordsPredicate predicate = new BookmarkContainsKeywordsPredicate(
                null, null, null, Collections.singletonList("Carol")
        );
        assertTrue(predicate.test(new BookmarkBuilder().withAuthor("Carol Lee").build()));

        // Multiple keyword
        predicate = new BookmarkContainsKeywordsPredicate(
                null, null, null, Arrays.asList("Mark", "Tan")
        );
        assertTrue(predicate.test(new BookmarkBuilder().withAuthor("Mark Tan").build()));

        // Mix case keyword
        predicate = new BookmarkContainsKeywordsPredicate(
                null, null, null, Collections.singletonList("MaRK")
        );
        assertTrue(predicate.test(new BookmarkBuilder().withAuthor("Mark").build()));
    }

    @Test
    public void test_authorContainsKeywords_returnsFalse() {
        // Non-matching keyword
        BookmarkContainsKeywordsPredicate predicate = new BookmarkContainsKeywordsPredicate(
                null, null, Collections.singletonList("Carol"), null
        );
        assertFalse(predicate.test(new BookmarkBuilder().withTags("Mark").build()));
    }

    @Test
    public void test_bookmarkHasNoAuthor_returnsFalse() {
        BookmarkContainsKeywordsPredicate predicate = new BookmarkContainsKeywordsPredicate(
                null, null, null, Collections.singletonList("Carol")
        );
        assertFalse(predicate.test(new BookmarkBuilder().build()));
    }

    @Test
    public void test_multipleFieldsContainKeywords_returnsTrue() {
        // Non-matching keyword
        BookmarkContainsKeywordsPredicate predicate = new BookmarkContainsKeywordsPredicate(
                null, Collections.singletonList("Fantasy"), Collections.singletonList("Carol"), null
        );
        assertTrue(predicate.test(new BookmarkBuilder().withGenre("Fantasy").withTags("Carol").build()));
    }

    @Test
    public void test_multipleFieldsContainKeywords_returnsFalse() {
        // Non-matching keyword
        BookmarkContainsKeywordsPredicate predicate = new BookmarkContainsKeywordsPredicate(
                null, Collections.singletonList("Fantasy"), Collections.singletonList("Carol"), null
        );
        assertFalse(predicate.test(new BookmarkBuilder().withGenre("Fantasy").build()));
    }
}
