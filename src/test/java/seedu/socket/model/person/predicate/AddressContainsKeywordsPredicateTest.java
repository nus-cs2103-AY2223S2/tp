package seedu.socket.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.socket.testutil.PersonBuilder;

public class AddressContainsKeywordsPredicateTest {

    @Test
    public void testEquals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FindCommandAddressPredicate firstPredicate = new FindCommandAddressPredicate(
                firstPredicateKeywordList);
        FindCommandAddressPredicate secondPredicate = new FindCommandAddressPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FindCommandAddressPredicate firstPredicateCopy = new FindCommandAddressPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different profile -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        // One keyword
        FindCommandAddressPredicate predicate =
                new FindCommandAddressPredicate(Collections.singletonList("bedok"));
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .withAddress("Bedok block 999")
                .build()));

        // Multiple keywords
        predicate = new FindCommandAddressPredicate(Arrays.asList("Bedok", "99"));
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .withAddress("Bedok block 999")
                .build()));

        // Only one matching keyword
        predicate = new FindCommandAddressPredicate(Arrays.asList("Bedok", "99"));
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Carol")
                .withAddress("Bedok block 1")
                .build()));

        // Mixed-case keywords
        predicate = new FindCommandAddressPredicate(Arrays.asList("bEDok", "bLOck"));
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .withAddress("Bedok block 999")
                .build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FindCommandAddressPredicate predicate = new FindCommandAddressPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice")
                .withAddress("Bedok block 999")
                .build()));

        // Non-matching keyword
        predicate = new FindCommandAddressPredicate(Arrays.asList("Kentridge"));
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .withAddress("Bedok block 999")
                .build()));

        // Keywords match phone, email and name, but does not match address
        predicate = new FindCommandAddressPredicate(Arrays.asList("12345", "alice@email.com", "Alice"));
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice")
                .withPhone("12345")
                .withEmail("alice@email.com")
                .withAddress("Main Street")
                .build()));
    }
}
