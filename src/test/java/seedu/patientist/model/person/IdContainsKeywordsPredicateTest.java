package seedu.patientist.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.patientist.testutil.PatientBuilder;

public class IdContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        IdContainsKeywordsPredicate firstPredicate = new IdContainsKeywordsPredicate(firstPredicateKeywordList);
        IdContainsKeywordsPredicate secondPredicate = new IdContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IdContainsKeywordsPredicate firstPredicateCopy = new IdContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_idContainsKeywords_returnsTrue() {
        // One keyword
        IdContainsKeywordsPredicate predicate = new IdContainsKeywordsPredicate(Collections.singletonList("A123"));
        assertTrue(predicate.test(new PatientBuilder().withIdNumber("A123").build()));

        // Multiple keywords
        predicate = new IdContainsKeywordsPredicate(Arrays.asList("A123", "B123"));
        assertTrue(predicate.test(new PatientBuilder().withIdNumber("A123").build()));

        // Mixed-case keywords
        predicate = new IdContainsKeywordsPredicate(List.of("a123A"));
        assertTrue(predicate.test(new PatientBuilder().withIdNumber("A123A").build()));
    }

    @Test
    public void test_idDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        IdContainsKeywordsPredicate predicate = new IdContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PatientBuilder().withIdNumber("A123").build()));

        // Non-matching keyword
        predicate = new IdContainsKeywordsPredicate(Arrays.asList("C12345"));
        assertFalse(predicate.test(new PatientBuilder().withIdNumber("A123").build()));

        // Keywords match phone, email and patientist, but does not match name
        predicate = new IdContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street", "Doing", "good", "A1234567890B"));
        assertFalse(predicate.test(new PatientBuilder().withIdNumber("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withStatus("Doing good")
                .withIdNumber("A12345").build()));
    }
}
