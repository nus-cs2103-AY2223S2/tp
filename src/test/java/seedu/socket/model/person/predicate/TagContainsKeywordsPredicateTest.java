package seedu.socket.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.socket.testutil.PersonBuilder;

public class TagContainsKeywordsPredicateTest {

    @Test
    public void testEquals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FindCommandTagPredicate firstPredicate = new FindCommandTagPredicate(
                firstPredicateKeywordList);
        FindCommandTagPredicate secondPredicate = new FindCommandTagPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FindCommandTagPredicate firstPredicateCopy = new FindCommandTagPredicate(firstPredicateKeywordList);
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
        FindCommandTagPredicate predicate = new FindCommandTagPredicate(Collections.singletonList("friend"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("friend", "student").build()));

        // Multiple keywords
        predicate = new FindCommandTagPredicate(Arrays.asList("friend", "student"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("friend", "student").build()));

        // Only one matching keyword
        predicate = new FindCommandTagPredicate(Arrays.asList("friend", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").withTags("friend", "student").build()));

        // Mixed-case keywords
        predicate = new FindCommandTagPredicate(Arrays.asList("FRIend", "stuDEnt"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("friend", "student").build()));

        // Alphanumerical keywords
        predicate = new FindCommandTagPredicate(Arrays.asList("cs2103t"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("cs2103t").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FindCommandTagPredicate predicate = new FindCommandTagPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withTags("friend", "student").build()));

        // Non-matching keyword
        predicate = new FindCommandTagPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("friend", "student").build()));

        // Keywords match name, phone, email and address, but does not match tag
        predicate = new FindCommandTagPredicate(Arrays.asList(
                "Alice", "12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice")
                .withPhone("12345")
                .withEmail("alice@email.com")
                .withAddress("Main Street")
                .withTags("friend", "student")
                .build()));
    }
}
