package seedu.task.testutil;

import seedu.task.model.Planner;
import seedu.task.model.calendar.DailyPlan;

/**
 * A utility class to help with building Planner objects.
 * Example usage: <br>
 *     {@code Planner ab = new PlannerBuilder().withPlan("APRIL26").build();}
 */
public class PlannerBuilder {
    private Planner planner;

    public PlannerBuilder() {
        planner = new Planner();
    }

    /**
     * Adds a new {@code DailyPlan} to the {@code Planner} that we are building.
     */
    public PlannerBuilder withPlan(DailyPlan dp) {
        planner.addDailyPlan(dp);
        return this;
    }

    public Planner build() {
        return planner;
    }
}
