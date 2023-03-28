package arb.model.project.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import arb.testutil.ProjectBuilder;

public class ProjectContainsTagsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateTagsList = Collections.singletonList("first");
        List<String> secondPredicateTagsList = Arrays.asList("first", "second");

        ProjectContainsTagsPredicate firstPredicate = new ProjectContainsTagsPredicate(firstPredicateTagsList);
        ProjectContainsTagsPredicate secondPredicate = new ProjectContainsTagsPredicate(secondPredicateTagsList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ProjectContainsTagsPredicate firstPredicateCopy = new ProjectContainsTagsPredicate(firstPredicateTagsList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tags -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        ProjectContainsTagsPredicate secondPredicateCopy =
                new ProjectContainsTagsPredicate(Arrays.asList("second", "first"));
        assertTrue(secondPredicate.equals(secondPredicateCopy)); // different order
    }

    @Test
    public void test_projectContainsTags_returnsTrue() {
        // One tag
        ProjectContainsTagsPredicate predicate =
                new ProjectContainsTagsPredicate(Collections.singletonList("painting"));
        assertTrue(predicate.test(new ProjectBuilder().withTags("painting").build()));

        // Multiple tags
        predicate = new ProjectContainsTagsPredicate(Arrays.asList("painting", "traditional"));
        assertTrue(predicate.test(new ProjectBuilder().withTags("painting", "traditional").build()));

        // Only one matching tag
        predicate = new ProjectContainsTagsPredicate(Arrays.asList("painting", "traditional"));
        assertTrue(predicate.test(new ProjectBuilder().withTags("painting").build()));

        // Mixed-case tags
        predicate = new ProjectContainsTagsPredicate(Arrays.asList("paiNTing", "trADiTioNal"));
        assertTrue(predicate.test(new ProjectBuilder().withTags("painting", "traditional").build()));
    }

    @Test
    public void test_projectDoesNotContainTags_returnsFalse() {
        // Zero tags
        ProjectContainsTagsPredicate predicate = new ProjectContainsTagsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ProjectBuilder().withTags("painting").build()));

        // Non-matching tag
        predicate = new ProjectContainsTagsPredicate(Arrays.asList("traditional"));
        assertFalse(predicate.test(new ProjectBuilder().withTags("painting").build()));

        // Tags match name but does not match tag
        predicate = new ProjectContainsTagsPredicate(Arrays.asList("Sunset"));
        assertFalse(predicate.test(new ProjectBuilder().withTitle("Sunset").withTags("painting").build()));
    }
}
