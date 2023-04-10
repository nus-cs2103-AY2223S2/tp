package arb.model.project.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import arb.testutil.PredicateUtil;
import arb.testutil.ProjectBuilder;

public class ProjectWithinTimeframePredicateTest {

    @Test
    public void equals() {
        Optional<String> start = Optional.of("1pm 2023-01-01");
        Optional<String> end = Optional.of("2pm 2023-05-05");

        ProjectWithinTimeframePredicate firstPredicate =
                PredicateUtil.getProjectWithinTimeframePredicate(start, end);
        ProjectWithinTimeframePredicate secondPredicate =
                PredicateUtil.getProjectWithinTimeframePredicate(Optional.empty(), end);
        ProjectWithinTimeframePredicate thirdPredicate =
                PredicateUtil.getProjectWithinTimeframePredicate(start, Optional.empty());

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ProjectWithinTimeframePredicate firstPredicateCopy =
                PredicateUtil.getProjectWithinTimeframePredicate(start, end);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different start values -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        // different end values -> returns false
        assertFalse(firstPredicate.equals(thirdPredicate));
    }

    @Test
    public void test_projectWithinTimeframe_returnsTrue() {
        Optional<String> sampleStart = Optional.of("1pm 2023-01-01");
        Optional<String> sampleEnd = Optional.of("2pm 2023-05-05");
        String inBetweenDeadline = "3pm 2023-03-03";

        // both start and end date are present
        ProjectWithinTimeframePredicate predicate =
                PredicateUtil.getProjectWithinTimeframePredicate(sampleStart, sampleEnd);
        assertTrue(predicate.test(new ProjectBuilder().withDeadline(inBetweenDeadline).build()));

        // only start date present
        predicate = PredicateUtil.getProjectWithinTimeframePredicate(sampleStart, Optional.empty());
        assertTrue(predicate.test(new ProjectBuilder().withDeadline(inBetweenDeadline).build()));

        // Only end date present
        predicate = PredicateUtil.getProjectWithinTimeframePredicate(Optional.empty(), sampleEnd);
        assertTrue(predicate.test(new ProjectBuilder().withDeadline(inBetweenDeadline).build()));
    }

    @Test
    public void test_projectNotWithinTimeframe_returnsFalse() {
        Optional<String> sampleStart = Optional.of("1pm 2023-01-01");
        Optional<String> sampleEnd = Optional.of("2pm 2023-05-05");

        String deadlineBeforeTimeframe = "12pm 2022-09-09";
        String deadlineAfterTimeframe = "3pm 2023-06-06";

        // Both start and end date are present, deadline falls outside timeframe
        ProjectWithinTimeframePredicate predicate =
                PredicateUtil.getProjectWithinTimeframePredicate(sampleStart, sampleEnd);
        assertFalse(predicate.test(new ProjectBuilder().withDeadline(deadlineBeforeTimeframe).build()));
        assertFalse(predicate.test(new ProjectBuilder().withDeadline(deadlineAfterTimeframe).build()));
        // Project does not have a deadline
        assertFalse(predicate.test(new ProjectBuilder().withDeadline(null).build()));

        // Only start date present, deadline falls outside timeframe
        predicate = PredicateUtil.getProjectWithinTimeframePredicate(sampleStart, Optional.empty());
        assertFalse(predicate.test(new ProjectBuilder().withDeadline(deadlineBeforeTimeframe).build()));

        // Only end date present, deadline falls outside timeframe
        predicate = PredicateUtil.getProjectWithinTimeframePredicate(Optional.empty(), sampleEnd);
        assertFalse(predicate.test(new ProjectBuilder().withDeadline(deadlineAfterTimeframe).build()));
    }
}
