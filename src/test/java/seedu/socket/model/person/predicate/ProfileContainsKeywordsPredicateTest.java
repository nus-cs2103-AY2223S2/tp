package seedu.socket.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.socket.testutil.PersonBuilder;

public class ProfileContainsKeywordsPredicateTest {

    @Test
    public void testEquals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FindCommandProfilePredicate firstPredicate = new FindCommandProfilePredicate(
                firstPredicateKeywordList);
        FindCommandProfilePredicate secondPredicate = new FindCommandProfilePredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FindCommandProfilePredicate firstPredicateCopy = new FindCommandProfilePredicate(
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
    public void test_profileContainsKeywords_returnsTrue() {
        // One keyword
        FindCommandProfilePredicate predicate = new FindCommandProfilePredicate(
                Collections.singletonList("alice-nus"));
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .withProfile("alice-nus")
                .build()));

        // Only one matching keyword
        predicate = new FindCommandProfilePredicate(Arrays.asList("alice-nus", "Carol-nus"));
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Carol")
                .withProfile("alice-nus")
                .build()));

        // Mixed-case keywords
        predicate = new FindCommandProfilePredicate(Arrays.asList("ALiCE-NuS"));
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .withProfile("alice-nus")
                .build()));
    }

    @Test
    public void test_profileDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FindCommandProfilePredicate predicate = new FindCommandProfilePredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice")
                .withProfile("alice-nus")
                .build()));

        // Non-matching keyword
        predicate = new FindCommandProfilePredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .withProfile("alice-nus")
                .build()));

        // Keywords match name, phone, email and address, but does not match profile
        predicate = new FindCommandProfilePredicate(Arrays.asList(
                "Alice", "12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice")
                .withProfile("alice-nus")
                .withPhone("12345")
                .withEmail("alice@email.com")
                .withAddress("Main Street")
                .build()));
    }
}
