package seedu.patientist.model.person.staff;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.patientist.testutil.StaffBuilder;

public class StaffIdContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        StaffIdContainsKeywordsPredicate firstPredicate =
                new StaffIdContainsKeywordsPredicate(firstPredicateKeywordList);
        StaffIdContainsKeywordsPredicate secondPredicate =
                new StaffIdContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StaffIdContainsKeywordsPredicate firstPredicateCopy =
                new StaffIdContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_staffIdContainsKeywords_returnsTrue() {
        // One keyword
        StaffIdContainsKeywordsPredicate predicate =
                new StaffIdContainsKeywordsPredicate(Collections.singletonList("A123"));
        assertTrue(predicate.test(new StaffBuilder().withIdNumber("A123").build()));

        // Multiple keywords
        predicate = new StaffIdContainsKeywordsPredicate(Arrays.asList("A123", "B123"));
        assertTrue(predicate.test(new StaffBuilder().withIdNumber("A123").build()));

        // Mixed-case keywords
        predicate = new StaffIdContainsKeywordsPredicate(List.of("a123A"));
        assertTrue(predicate.test(new StaffBuilder().withIdNumber("A123A").build()));
    }

    @Test
    public void test_staffIdDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        StaffIdContainsKeywordsPredicate predicate = new StaffIdContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StaffBuilder().withIdNumber("A123").build()));

        // Non-matching keyword
        predicate = new StaffIdContainsKeywordsPredicate(Arrays.asList("C12345"));
        assertFalse(predicate.test(new StaffBuilder().withIdNumber("A123").build()));

        // Keywords match phone, email and patientist, but does not match name
        predicate = new StaffIdContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street", "Doing", "good", "A1234567890B"));
        assertFalse(predicate.test(new StaffBuilder().withIdNumber("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withIdNumber("A12345").build()));
    }
}
