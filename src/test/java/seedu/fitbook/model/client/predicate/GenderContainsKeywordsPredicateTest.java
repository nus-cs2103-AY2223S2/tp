package seedu.fitbook.model.client.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.fitbook.testutil.client.ClientBuilder;

public class GenderContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        GenderContainsKeywordsPredicate firstPredicate = new GenderContainsKeywordsPredicate(
                firstPredicateKeywordList);
        GenderContainsKeywordsPredicate secondPredicate = new GenderContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GenderContainsKeywordsPredicate firstPredicateCopy = new GenderContainsKeywordsPredicate(
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
    public void test_genderContainsKeywords_returnsTrue() {
        // One keyword
        GenderContainsKeywordsPredicate predicate = new GenderContainsKeywordsPredicate(Collections
                .singletonList("F"));
        assertTrue(predicate.test(new ClientBuilder().withAddress("F").build()));

        // Mixed-case keywords
        predicate = new GenderContainsKeywordsPredicate(Arrays.asList("f"));
        assertTrue(predicate.test(new ClientBuilder().withAddress("F").build()));
    }

    @Test
    public void test_genderDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        GenderContainsKeywordsPredicate predicate = new GenderContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ClientBuilder().withGender("F").build()));

        // Non-matching keyword
        predicate = new GenderContainsKeywordsPredicate(Arrays.asList("F"));
        assertFalse(predicate.test(new ClientBuilder().withGender("M").build()));
    }
}
