package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.task.testutil.DeadlineBuilder;
import seedu.task.testutil.EventBuilder;
import seedu.task.testutil.SimpleTaskBuilder;

public class TaskWithinTimelinePredicateTest {
    @Test
    public void equals() {
        TaskWithinTimelinePredicate firstPredicate =
                new TaskWithinTimelinePredicate(Duration.ofHours(5));
        TaskWithinTimelinePredicate secondPredicate =
                new TaskWithinTimelinePredicate(Duration.ofHours(10));

        // same object -> return true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        assertTrue(firstPredicate.equals(new TaskWithinTimelinePredicate(Duration.ofHours(5))));

        // different types -> return false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_taskIsWithinTimeline_returnsTrue() {
        TaskWithinTimelinePredicate firstPredicate =
                new TaskWithinTimelinePredicate(Duration.ofHours(5));

        LocalDateTime now = LocalDateTime.now().plus(Duration.ofHours(3));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        String formatDateTime = now.format(formatter);
        //within the timing
        assertTrue(firstPredicate.test(new DeadlineBuilder().withDate(formatDateTime).build()));
        assertTrue(firstPredicate.test(new EventBuilder().withTo(formatDateTime).build()));
    }

    @Test
    public void test_taskIsNotWithinTimeline_returnsFalse() {
        TaskWithinTimelinePredicate firstPredicate =
                new TaskWithinTimelinePredicate(Duration.ofHours(5));

        LocalDateTime now = LocalDateTime.now().plus(Duration.ofHours(6));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        String formatDateTime = now.format(formatter);
        //outside timing
        assertFalse(firstPredicate.test(new DeadlineBuilder().withDate(formatDateTime).build()));
        assertFalse(firstPredicate.test(new EventBuilder().withTo(formatDateTime).build()));
        assertFalse(firstPredicate.test(new SimpleTaskBuilder().build()));
    }
}
