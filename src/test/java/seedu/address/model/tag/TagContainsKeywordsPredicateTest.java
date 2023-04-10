package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TagContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagContainsKeywordsPredicate firstPredicate = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        TagContainsKeywordsPredicate secondPredicate = new TagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.singletonList("friends"));
        assertTrue(predicate.test(new seedu.address.testutil.StudentBuilder().withTags("friends").build()));

        // Multiple keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("friends", "owesMoney"));
        assertTrue(predicate.test(new seedu.address.testutil.StudentBuilder()
                .withTags("friends", "owesMoney").build()));


        // Only one matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("friends", "JC"));
        assertTrue(predicate.test(new seedu.address.testutil.StudentBuilder()
                .withTags("friends", "primary").build()));


        // Mixed-case keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("FriEnds", "JC"));
        assertTrue(predicate.test(new seedu.address.testutil.StudentBuilder()
                .withTags("friends", "jc").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new seedu.address.testutil.StudentBuilder().withTags("friends").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("female"));
        assertFalse(predicate.test(new seedu.address.testutil.StudentBuilder().withName("friends owesMoney").build()));
    }
}

