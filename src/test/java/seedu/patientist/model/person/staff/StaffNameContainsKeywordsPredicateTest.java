package seedu.patientist.model.person.staff;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_PID_AMY;
import static seedu.patientist.testutil.TypicalStaff.AMY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class StaffNameContainsKeywordsPredicateTest {

    @Test
    public void test_staffNameContainsKeywords_returnsTrue() {
        // One keyword
        StaffNameContainsKeywordsPredicate predicate =
                new StaffNameContainsKeywordsPredicate(Collections.singletonList("Amy"));
        assertTrue(predicate.test(AMY));

        // Multiple keywords
        predicate = new StaffNameContainsKeywordsPredicate(Arrays.asList("Amy", "Bee"));
        assertTrue(predicate.test(AMY));

        // Mixed-case keywords
        predicate = new StaffNameContainsKeywordsPredicate(List.of("aMy"));
        assertTrue(predicate.test(AMY));

    }

    @Test
    public void test_staffNameContainsNoKeywords_returnsFalse() {
        // Zero keywords
        StaffNameContainsKeywordsPredicate predicate = new StaffNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(AMY));

        // Non-matching keyword
        predicate = new StaffNameContainsKeywordsPredicate(Arrays.asList("Charles"));
        assertFalse(predicate.test(AMY));

        // Keywords match phone, email and patientist, but does not match name
        predicate = new StaffNameContainsKeywordsPredicate(
                Arrays.asList(VALID_PID_AMY, VALID_EMAIL_AMY, VALID_PHONE_AMY));
        assertFalse(predicate.test(AMY));
    }

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        StaffNameContainsKeywordsPredicate firstPredicate =
                new StaffNameContainsKeywordsPredicate(firstPredicateKeywordList);
        StaffNameContainsKeywordsPredicate secondPredicate =
                new StaffNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StaffNameContainsKeywordsPredicate firstPredicateCopy =
                new StaffNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
