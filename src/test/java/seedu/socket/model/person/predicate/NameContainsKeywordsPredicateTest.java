package seedu.socket.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.socket.testutil.PersonBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FindCommandNamePredicate firstPredicate = new FindCommandNamePredicate(
                firstPredicateKeywordList);
        FindCommandNamePredicate secondPredicate = new FindCommandNamePredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FindCommandNamePredicate firstPredicateCopy = new FindCommandNamePredicate(
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
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        FindCommandNamePredicate predicate = new FindCommandNamePredicate(
                Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .build()));

        // Multiple keywords
        predicate = new FindCommandNamePredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .build()));

        // Only one matching keyword
        predicate = new FindCommandNamePredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Carol")
                .build()));

        // Mixed-case keywords
        predicate = new FindCommandNamePredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FindCommandNamePredicate predicate = new FindCommandNamePredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice")
                .build()));

        // Non-matching keyword
        predicate = new FindCommandNamePredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new FindCommandNamePredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice")
                .withPhone("12345")
                .withEmail("alice@email.com")
                .withAddress("Main Street")
                .build()));
    }
}
