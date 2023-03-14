package seedu.task.model;

import java.util.List;

import seedu.task.model.calendar.DailyPlan;
import seedu.task.model.task.Date;

/**
 * Unmodifiable view of a Planner
 */
public interface ReadOnlyPlanner {
    /**
     * Returns an unmodifiable view of Planner
     */
    List<DailyPlan> getDailyPlanList();

}
