package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NricContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NricContainsKeywordsPredicate firstPredicate = new NricContainsKeywordsPredicate(firstPredicateKeywordList);
        NricContainsKeywordsPredicate secondPredicate = new NricContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NricContainsKeywordsPredicate firstPredicateCopy = new NricContainsKeywordsPredicate(firstPredicateKeywordList);
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
        NricContainsKeywordsPredicate predicate =
                new NricContainsKeywordsPredicate(Collections.singletonList("T0046785I"));
        assertTrue(predicate.test(new PersonBuilder().withNric("T0046785I").build()));

        // Mixed-case keywords
        predicate = new NricContainsKeywordsPredicate(Arrays.asList("S0056789G"));
        assertTrue(predicate.test(new PersonBuilder().withNric("S0056789G").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("S0056789G"));
        assertFalse(predicate.test(new PersonBuilder().withName("T0068975L").build()));

        // Keywords match phone, email and address, but does not match NRIC
        predicate =
                new NameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street", "S0056789G"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withNric("S0056789G").build()));
    }
}
