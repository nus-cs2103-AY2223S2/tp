package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordName = List.of("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PersonContainsKeywordsPredicate firstPredicate = new PersonContainsKeywordsPredicate()
                .withField(PredicateKey.NAME, firstPredicateKeywordName);
        PersonContainsKeywordsPredicate secondPredicate = new PersonContainsKeywordsPredicate()
                .withField(PredicateKey.NAME, secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonContainsKeywordsPredicate firstPredicateCopy = new PersonContainsKeywordsPredicate()
                .withField(PredicateKey.NAME, firstPredicateKeywordName);
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
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate()
                .withField(PredicateKey.NAME, Collections.singleton("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new PersonContainsKeywordsPredicate()
                .withField(PredicateKey.NAME, Collections.singleton("Alice"))
                .withField(PredicateKey.ADDRESS, Collections.singleton("Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withAddress("Bob").build()));

        // Only one matching keyword
        predicate = new PersonContainsKeywordsPredicate()
                .withField(PredicateKey.NAME, (Arrays.asList("Bob", "Carol")));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new PersonContainsKeywordsPredicate().withField(PredicateKey.NAME, Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate()
                .withField(PredicateKey.NAME, Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new PersonContainsKeywordsPredicate().withField(PredicateKey.NAME, List.of("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new PersonContainsKeywordsPredicate()
                .withField(PredicateKey.NAME, List.of("Bob"))
                .withField(PredicateKey.PHONE, List.of("12345"))
                .withField(PredicateKey.EMAIL, List.of("alice@email.com"))
                .withField(PredicateKey.ADDRESS, List.of("Main Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
