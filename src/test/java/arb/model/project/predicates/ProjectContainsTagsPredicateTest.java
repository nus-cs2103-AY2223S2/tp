package arb.model.project.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import arb.testutil.PredicateUtil;
import arb.testutil.ProjectBuilder;

public class ProjectContainsTagsPredicateTest {

    @Test
    public void equals() {
        ProjectContainsTagsPredicate firstPredicate = PredicateUtil.getProjectContainsTagsPredicate("first");
        ProjectContainsTagsPredicate secondPredicate = PredicateUtil.getProjectContainsTagsPredicate("first", "second");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ProjectContainsTagsPredicate firstPredicateCopy =
                PredicateUtil.getProjectContainsTagsPredicate("first");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tags -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        ProjectContainsTagsPredicate secondPredicateCopy =
                PredicateUtil.getProjectContainsTagsPredicate("second", "first");
        assertTrue(secondPredicate.equals(secondPredicateCopy)); // different order
    }

    @Test
    public void test_projectContainsTags_returnsTrue() {
        // One tag
        ProjectContainsTagsPredicate predicate =
                PredicateUtil.getProjectContainsTagsPredicate("painting");
        assertTrue(predicate.test(new ProjectBuilder().withTags("painting").build()));

        // Multiple tags
        predicate = PredicateUtil.getProjectContainsTagsPredicate("painting", "traditional");
        assertTrue(predicate.test(new ProjectBuilder().withTags("painting", "traditional").build()));

        // Only one matching tag
        predicate = PredicateUtil.getProjectContainsTagsPredicate("painting", "traditional");
        assertTrue(predicate.test(new ProjectBuilder().withTags("painting").build()));

        // Mixed-case tags
        predicate = PredicateUtil.getProjectContainsTagsPredicate("paiNTing", "trADiTioNal");
        assertTrue(predicate.test(new ProjectBuilder().withTags("painting", "traditional").build()));
    }

    @Test
    public void test_projectDoesNotContainTags_returnsFalse() {
        // Zero tags
        ProjectContainsTagsPredicate predicate = PredicateUtil.getProjectContainsTagsPredicate();
        assertFalse(predicate.test(new ProjectBuilder().withTags("painting").build()));

        // Non-matching tag
        predicate = PredicateUtil.getProjectContainsTagsPredicate("traditional");
        assertFalse(predicate.test(new ProjectBuilder().withTags("painting").build()));

        // Tags match name but does not match tag
        predicate = PredicateUtil.getProjectContainsTagsPredicate("Sunset");
        assertFalse(predicate.test(new ProjectBuilder().withTitle("Sunset").withTags("painting").build()));
    }
}
