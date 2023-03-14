package seedu.task.model.calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.task.testutil.TypicalLocalDates;
import seedu.task.testutil.TypicalMonthlyPlan;

public class MonthlyPlanTest {

    @Test
    public void constructorCreation_test() {
        MonthlyPlan m = new MonthlyPlan(5, TypicalLocalDates.LOCAL_DATE_FOURTH);
        MonthlyPlan m2 = TypicalMonthlyPlan.APR26_PLAN;
        assertEquals(TypicalMonthlyPlan.APR26_PLAN, m);
    }

    @Test
    public void findMostBusyFreeDate_test() {
        // not yet populated, first free day
        assertEquals(0, TypicalMonthlyPlan.APR26_PLAN.findMostBusyFreeDate(0));

        // task requires too much effort -> return -1
        assertEquals(-1, TypicalMonthlyPlan.APR26_PLAN.findMostBusyFreeDate(10));
    }

    @Test
    public void findFirstFreeDate_test() {
        // not yet populated, first free day
        assertEquals(0, TypicalMonthlyPlan.APR26_PLAN.findFirstFreeDate(3, 1));

        // no free days before the deadline -> return -1
        assertEquals(-1, TypicalMonthlyPlan.APR26_PLAN.findFirstFreeDate(3, -1));
    }
}
