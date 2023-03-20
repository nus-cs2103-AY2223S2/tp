package seedu.sudohr.model.department;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.sudohr.testutil.DepartmentBuilder;


public class DepartmentNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DepartmentNameContainsKeywordsPredicate firstPredicate =
                new DepartmentNameContainsKeywordsPredicate(firstPredicateKeywordList);
        DepartmentNameContainsKeywordsPredicate secondPredicate =
                new DepartmentNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DepartmentNameContainsKeywordsPredicate firstPredicateCopy =
                new DepartmentNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different department -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_departmentNameContainsKeywords_returnsTrue() {
        // One keyword
        DepartmentNameContainsKeywordsPredicate predicate =
                new DepartmentNameContainsKeywordsPredicate(Collections.singletonList("Human"));

        assertTrue(predicate.test(new DepartmentBuilder().withDepartmentName("Human Resources").build()));

        // Multiple keywords
        predicate = new DepartmentNameContainsKeywordsPredicate(Arrays.asList("Human", "Resources"));
        assertTrue(predicate.test(new DepartmentBuilder().withDepartmentName("Human Resources").build()));

        // Only one matching keyword
        predicate = new DepartmentNameContainsKeywordsPredicate(Arrays.asList("Human", "Manpower"));
        assertTrue(predicate.test(new DepartmentBuilder().withDepartmentName("Human Resources").build()));

        // Mixed-case keywords
        predicate = new DepartmentNameContainsKeywordsPredicate(Arrays.asList("hUmAn", "RESOUrces"));
        assertTrue(predicate.test(new DepartmentBuilder().withDepartmentName("Human Resources").build()));
    }

    @Test
    public void test_departmentNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DepartmentNameContainsKeywordsPredicate predicate =
                new DepartmentNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new DepartmentBuilder().withDepartmentName("Human Resources").build()));

        // Non-matching keyword
        predicate = new DepartmentNameContainsKeywordsPredicate(Arrays.asList("Engineering"));
        assertFalse(predicate.test(new DepartmentBuilder().withDepartmentName("Human Resources").build()));

    }


}
