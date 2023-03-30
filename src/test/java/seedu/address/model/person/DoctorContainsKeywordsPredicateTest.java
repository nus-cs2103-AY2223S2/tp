package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class DoctorContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DoctorContainsKeywordsPredicate firstPredicate =
                new DoctorContainsKeywordsPredicate(firstPredicateKeywordList);
        DoctorContainsKeywordsPredicate secondPredicate =
                new DoctorContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DoctorContainsKeywordsPredicate firstPredicateCopy =
                new DoctorContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_doctorContainsKeywords_returnsTrue() {
        // One keyword
        DoctorContainsKeywordsPredicate predicate =
                new DoctorContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withDoctor("Alice Bob").build()));

        // Multiple keywords
        predicate = new DoctorContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withDoctor("Alice Bob").build()));

        // Only one matching keyword
        predicate = new DoctorContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withDoctor("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new DoctorContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withDoctor("Alice Bob").build()));
    }

    @Test
    public void test_doctorDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DoctorContainsKeywordsPredicate predicate = new DoctorContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withDoctor("Alice").build()));

        // Non-matching keyword
        predicate = new DoctorContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withDoctor("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match doctor
        predicate = new DoctorContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withDoctor("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
