package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class NameContainsAllKeywordsPredicateTest {

    @Test
    public void test_nameContainsAllKeywords_returnsTrue() {
        // All keywords match exactly
        NameContainsAllKeywordsPredicate predicate =
                new NameContainsAllKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // One keyword only
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));

        // All keywords match, with longer name
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Bob Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainAllKeywords_returnsFalse() {
        // Non-matching keyword
        NameContainsAllKeywordsPredicate predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Partially matching keyword, multiple keywords
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Carol", "Lewis"));
        assertFalse(predicate.test(new PersonBuilder().withName("Carol").build()));

        // Partially matching keyword, multi-word name
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList("Carol", "Lewis"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

    }
}
