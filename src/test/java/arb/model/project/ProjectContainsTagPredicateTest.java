/*package arb.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import arb.testutil.ProjectBuilder;

public class ProjectContainsTagPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateTagList = Collections.singletonList("first");
        List<String> secondPredicateTagList = Arrays.asList("first", "second");

        ProjectContainsTagPredicate firstPredicate = new ProjectContainsTagPredicate(firstPredicateTagList);
        ProjectContainsTagPredicate secondPredicate = new ProjectContainsTagPredicate(secondPredicateTagList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ProjectContainsTagPredicate firstPredicateCopy = new ProjectContainsTagPredicate(firstPredicateTagList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tags -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        ProjectContainsTagPredicate secondPredicateCopy =
                new ProjectContainsTagPredicate(Arrays.asList("second", "first"));
        assertTrue(secondPredicate.equals(secondPredicateCopy)); // different order
    }

    @Test
    public void test_projectContainsTag_returnsTrue() {
        // One tag
        ProjectContainsTagPredicate predicate = new ProjectContainsTagPredicate(Collections.singletonList("painting"));
        assertTrue(predicate.test(new ProjectBuilder().withTags("painting").build()));

        // Multiple tags
        predicate = new ProjectContainsTagPredicate(Arrays.asList("painting", "traditional"));
        assertTrue(predicate.test(new ProjectBuilder().withTags("painting", "traditional").build()));

        // Only one matching tag
        predicate = new ProjectContainsTagPredicate(Arrays.asList("painting", "traditional"));
        assertTrue(predicate.test(new ProjectBuilder().withTags("painting").build()));

        // Mixed-case tags
        predicate = new ProjectContainsTagPredicate(Arrays.asList("paiNTing", "trADiTioNal"));
        assertTrue(predicate.test(new ProjectBuilder().withTags("painting", "traditional").build()));
    }

    @Test
    public void test_projectDoesNotContainTags_returnsFalse() {
        // Zero tags
        ProjectContainsTagPredicate predicate = new ProjectContainsTagPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ProjectBuilder().withTags("painting").build()));

        // Non-matching tag
        predicate = new ProjectContainsTagPredicate(Arrays.asList("traditional"));
        assertFalse(predicate.test(new ProjectBuilder().withTags("painting").build()));

        // Tags match name but does not match tag
        predicate = new ProjectContainsTagPredicate(Arrays.asList("Sunset Painting"));
        assertFalse(predicate.test(new ProjectBuilder().withName("Sunset Painting").withTags("painting").build()));
    }
}*/
