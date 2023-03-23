package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

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

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        TagContainsKeywordsPredicate predicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("Dyslexic"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Dyslexic").build()));

        // Mixed-case keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("dYsLexIc"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Dyslexic").build()));

        // One keyword, person with multiple tags, one of which matches
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Dyslexic"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Dyslexic", "Diabetic").build()));

        // Multiple keywords, one of which matches a tag
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Dyslexic", "Osteoporotic"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Dyslexic").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate predicate =
                new TagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("Dyslexic", "Diabetic").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Dyslexic"));
        assertFalse(predicate.test(new PersonBuilder().withTags("Diabetic", "Osteoporotic").build()));

        // Keywords match phone, email and address, but does not match Tag
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("12345",
                    "alice@email.com", "Main", "Street", "Diabetic"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withTags("Dyslexic", "Osteoporotic").build()));
    }
}
