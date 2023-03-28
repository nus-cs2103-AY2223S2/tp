package arb.model.project.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import arb.model.project.Deadline;
import arb.testutil.ProjectBuilder;

public class ProjectWithinTimeframePredicateTest {

    @Test
    public void equals() {
        Deadline firstStart = new Deadline("1pm 2023-01-01");
        Deadline firstEnd = new Deadline("2pm 2023-05-05");

        Deadline secondStart = null;
        Deadline secondEnd = new Deadline("2pm 2023-05-05");

        Deadline thirdStart = new Deadline("1pm 2023-01-01");
        Deadline thirdEnd = null;

        ProjectWithinTimeframePredicate firstPredicate = new ProjectWithinTimeframePredicate(firstStart, firstEnd);
        ProjectWithinTimeframePredicate secondPredicate = new ProjectWithinTimeframePredicate(secondStart, secondEnd);
        ProjectWithinTimeframePredicate thirdPredicate = new ProjectWithinTimeframePredicate(thirdStart, thirdEnd);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ProjectWithinTimeframePredicate firstPredicateCopy = new ProjectWithinTimeframePredicate(firstStart, firstEnd);
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
        Deadline sampleStart = new Deadline("1pm 2023-01-01");
        Deadline sampleEnd = new Deadline("2pm 2023-05-05");
        String inBetweenDeadline = "3pm 2023-03-03";
        // both start and end date are present
        ProjectWithinTimeframePredicate predicate = new ProjectWithinTimeframePredicate(sampleStart, sampleEnd);
        assertTrue(predicate.test(new ProjectBuilder().withDeadline(inBetweenDeadline).build()));

        // only start date present
        predicate = new ProjectWithinTimeframePredicate(sampleStart, null);
        assertTrue(predicate.test(new ProjectBuilder().withDeadline(inBetweenDeadline).build()));

        // Only end date present
        predicate = new ProjectWithinTimeframePredicate(null, sampleEnd);
        assertTrue(predicate.test(new ProjectBuilder().withDeadline(inBetweenDeadline).build()));
    }

    @Test
    public void test_projectNotWithinTimeframe_returnsFalse() {
        Deadline sampleStart = new Deadline("1pm 2023-01-01");
        Deadline sampleEnd = new Deadline("2pm 2023-05-05");

        String deadlineBeforeTimeframe = "12pm 2022-09-09";
        String deadlineAfterTimeframe = "3pm 2023-06-06";

        // Both start and end date are present, deadline falls outside timeframe
        ProjectWithinTimeframePredicate predicate = new ProjectWithinTimeframePredicate(sampleStart, sampleEnd);
        assertFalse(predicate.test(new ProjectBuilder().withDeadline(deadlineBeforeTimeframe).build()));
        assertFalse(predicate.test(new ProjectBuilder().withDeadline(deadlineAfterTimeframe).build()));

        // Only start date present, deadline falls outside timeframe
        predicate = new ProjectWithinTimeframePredicate(sampleStart, null);
        assertFalse(predicate.test(new ProjectBuilder().withDeadline(deadlineBeforeTimeframe).build()));

        // Only end date present, deadline falls outside timeframe
        predicate = new ProjectWithinTimeframePredicate(null, sampleEnd);
        assertFalse(predicate.test(new ProjectBuilder().withDeadline(deadlineAfterTimeframe).build()));

        // Project does not have a deadline
        predicate = new ProjectWithinTimeframePredicate(sampleStart, sampleEnd);
        assertFalse(predicate.test(new ProjectBuilder().withDeadline(null).build()));
    }
}
