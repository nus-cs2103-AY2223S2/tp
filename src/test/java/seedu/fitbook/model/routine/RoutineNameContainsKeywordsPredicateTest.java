package seedu.fitbook.model.routine;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.fitbook.model.routines.RoutineNameContainsKeywordsPredicate;
import seedu.fitbook.testutil.routine.RoutineBuilder;


public class RoutineNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        RoutineNameContainsKeywordsPredicate firstPredicate =
                new RoutineNameContainsKeywordsPredicate(firstPredicateKeywordList);
        RoutineNameContainsKeywordsPredicate secondPredicate =
                new RoutineNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RoutineNameContainsKeywordsPredicate firstPredicateCopy =
                new RoutineNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different routine -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_routineNameContainsKeywords_returnsTrue() {
        // One keyword
        RoutineNameContainsKeywordsPredicate predicate =
                new RoutineNameContainsKeywordsPredicate(Collections.singletonList("Jumps"));
        assertTrue(predicate.test(new RoutineBuilder().withRoutineName("Jumps").build()));

        // Multiple keywords
        predicate = new RoutineNameContainsKeywordsPredicate(Arrays.asList("Jumps", "Strength"));
        assertTrue(predicate.test(new RoutineBuilder().withRoutineName("Jumps Strength").build()));

        // Only one matching keyword
        predicate = new RoutineNameContainsKeywordsPredicate(Arrays.asList("Jumps", "Carol"));
        assertTrue(predicate.test(new RoutineBuilder().withRoutineName("Jumps Strength").build()));

        // Mixed-case keywords
        predicate = new RoutineNameContainsKeywordsPredicate(Arrays.asList("JUmps", "StRENGTH"));
        assertTrue(predicate.test(new RoutineBuilder().withRoutineName("Jumps Strength").build()));
    }

}
