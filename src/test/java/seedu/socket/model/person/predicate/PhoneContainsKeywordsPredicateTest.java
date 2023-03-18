package seedu.socket.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.socket.testutil.PersonBuilder;

public class PhoneContainsKeywordsPredicateTest {

    @Test
    public void testEquals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FindCommandPhonePredicate firstPredicate = new FindCommandPhonePredicate(
                firstPredicateKeywordList);
        FindCommandPhonePredicate secondPredicate = new FindCommandPhonePredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FindCommandPhonePredicate firstPredicateCopy = new FindCommandPhonePredicate(
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
    public void test_phoneContainsKeywords_returnsTrue() {
        // One keyword
        FindCommandPhonePredicate predicate = new FindCommandPhonePredicate(
                Collections.singletonList("12345678"));
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .withPhone("12345678")
                .build()));

        // Only one matching keyword
        predicate = new FindCommandPhonePredicate(Arrays.asList("12345678", "00001111"));
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Carol")
                .withPhone("12345678")
                .build()));
    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FindCommandPhonePredicate predicate = new FindCommandPhonePredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice")
                .withPhone("12345678")
                .build()));

        // Non-matching keyword
        predicate = new FindCommandPhonePredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .withPhone("12345678")
                .build()));

        // Keywords match name, email and address, but does not match phone
        predicate = new FindCommandPhonePredicate(Arrays.asList("Alice", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice")
                .withPhone("12345678")
                .withEmail("alice@email.com")
                .withAddress("Main Street")
                .build()));
    }
}
