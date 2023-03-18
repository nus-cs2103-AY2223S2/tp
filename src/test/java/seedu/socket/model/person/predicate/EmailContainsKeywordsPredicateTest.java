package seedu.socket.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.socket.testutil.PersonBuilder;

public class EmailContainsKeywordsPredicateTest {

    @Test
    public void testEquals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FindCommandEmailPredicate firstPredicate = new FindCommandEmailPredicate(
                firstPredicateKeywordList);
        FindCommandEmailPredicate secondPredicate = new FindCommandEmailPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FindCommandEmailPredicate firstPredicateCopy = new FindCommandEmailPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // One keyword
        FindCommandEmailPredicate predicate = new FindCommandEmailPredicate(
                Collections.singletonList("Alice@gmail.com"));
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .withEmail("Alice@gmail.com")
                .build()));

        // Only one matching keyword
        predicate = new FindCommandEmailPredicate(Arrays.asList("Alice@gmail.com", "Carol"));
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Carol")
                .withEmail("Alice@gmail.com")
                .build()));

        // Mixed-case keywords
        predicate = new FindCommandEmailPredicate(Arrays.asList("ALICE@GMaiL.cOm"));
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .withEmail("Alice@gmail.com")
                .build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FindCommandEmailPredicate predicate = new FindCommandEmailPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice")
                .withEmail("Alice@gmail.com")
                .build()));

        // Non-matching keyword
        predicate = new FindCommandEmailPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .withEmail("Alice@gmail.com")
                .build()));

        // Keywords match phone, name and address, but does not match email
        predicate = new FindCommandEmailPredicate(Arrays.asList("12345", "Alice", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice")
                .withPhone("12345")
                .withEmail("alice@email.com")
                .withAddress("Main Street")
                .build()));
    }
}
