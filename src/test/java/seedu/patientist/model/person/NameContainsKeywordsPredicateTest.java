package seedu.patientist.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.patientist.testutil.PatientBuilder;
import seedu.patientist.testutil.StaffBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
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
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new StaffBuilder().withName("Alice Bob").build()));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new StaffBuilder().withName("Alice Bob").build()));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));


        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new StaffBuilder().withName("Alice Carol").build()));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Carol").build()));


        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new StaffBuilder().withName("Alice Bob").build()));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StaffBuilder().withName("Alice").build()));
        assertFalse(predicate.test(new PatientBuilder().withName("Alice").build()));


        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new StaffBuilder().withName("Alice Bob").build()));
        assertFalse(predicate.test(new PatientBuilder().withName("Alice Bob").build()));


        // Keywords match phone, email, ID and patientist, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345",
                "alice@email.com",
                "Main",
                "Street",
                "A123456789"));
        assertFalse(predicate.test(new StaffBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withIdNumber("A123456789").build()));
        assertFalse(predicate.test(new PatientBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withIdNumber("A123456789").build()));
    }
}
