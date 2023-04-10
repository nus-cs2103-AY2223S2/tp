package seedu.patientist.model.person.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_PID_AMY;
import static seedu.patientist.testutil.TypicalPatients.AMY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PatientNameContainsKeywordsPredicateTest {
    @Test
    public void test_patientNameContainsKeywords_returnsTrue() {
        // One keyword
        PatientNameContainsKeywordsPredicate predicate =
                new PatientNameContainsKeywordsPredicate(Collections.singletonList("Amy"));
        assertTrue(predicate.test(AMY));

        // Multiple keywords
        predicate = new PatientNameContainsKeywordsPredicate(Arrays.asList("Amy", "Bee"));
        assertTrue(predicate.test(AMY));

        // Mixed-case keywords
        predicate = new PatientNameContainsKeywordsPredicate(List.of("aMy"));
        assertTrue(predicate.test(AMY));

    }

    @Test
    public void test_patientNameContainsNoKeywords_returnsFalse() {
        // Zero keywords
        PatientNameContainsKeywordsPredicate predicate =
                new PatientNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(AMY));

        // Non-matching keyword
        predicate = new PatientNameContainsKeywordsPredicate(Arrays.asList("Charles"));
        assertFalse(predicate.test(AMY));

        // Keywords match phone, email and patientist, but does not match name
        predicate = new PatientNameContainsKeywordsPredicate(
                Arrays.asList(VALID_PID_AMY, VALID_EMAIL_AMY, VALID_PHONE_AMY));
        assertFalse(predicate.test(AMY));
    }

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PatientNameContainsKeywordsPredicate firstPredicate =
                new PatientNameContainsKeywordsPredicate(firstPredicateKeywordList);
        PatientNameContainsKeywordsPredicate secondPredicate =
                new PatientNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PatientNameContainsKeywordsPredicate firstPredicateCopy =
                new PatientNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
