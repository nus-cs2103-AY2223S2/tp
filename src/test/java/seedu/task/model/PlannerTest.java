package seedu.task.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.task.testutil.Assert.assertThrows;
import static seedu.task.testutil.TypicalDailyPlans.getTypicalPlanner;

import java.util.Collections;

import org.junit.jupiter.api.Test;

public class PlannerTest {
    private final Planner planner = new Planner();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), planner.getDailyPlanList());
    }
    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> planner.resetData(null));
    }
    @Test
    public void resetData_withValidReadOnlyPlanner_replacesData() {
        Planner newPlans = getTypicalPlanner();
        planner.resetData(newPlans);
        assertEquals(newPlans, planner);
    }
}
