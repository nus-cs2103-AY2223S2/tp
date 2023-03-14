package seedu.task.model;

import java.util.List;

import seedu.task.model.calendar.DailyPlan;

/**
 * Unmodifiable view of a Planner
 */
public interface ReadOnlyPlanner {
    /**
     * Returns an unmodifiable view of Planner
     */
    List<DailyPlan> getDailyPlanList();
}
