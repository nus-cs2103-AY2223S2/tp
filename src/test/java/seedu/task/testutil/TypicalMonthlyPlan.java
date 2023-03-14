package seedu.task.testutil;

import java.time.LocalDate;

import seedu.task.model.calendar.DailyPlan;
import seedu.task.model.calendar.MonthlyPlan;

/**
 * A utility class containing a {@TypicalMonthlyPlan} objects to be used in tests.
 */
public class TypicalMonthlyPlan {
    private static final LocalDate DEFAULT_DATE = LocalDate.parse("2023-04-26");

    private static final DailyPlan[] DAILY_PLANS = {
        TypicalDailyPlans.APRIL26, TypicalDailyPlans.APRIL27, TypicalDailyPlans.APRIL28, TypicalDailyPlans.APRIL29,
        TypicalDailyPlans.APRIL30, TypicalDailyPlans.MAY1, TypicalDailyPlans.MAY2, TypicalDailyPlans.MAY3,
        TypicalDailyPlans.MAY4, TypicalDailyPlans.MAY5, TypicalDailyPlans.MAY6, TypicalDailyPlans.MAY7,
        TypicalDailyPlans.MAY8, TypicalDailyPlans.MAY9, TypicalDailyPlans.MAY10, TypicalDailyPlans.MAY11,
        TypicalDailyPlans.MAY12, TypicalDailyPlans.MAY13, TypicalDailyPlans.MAY14, TypicalDailyPlans.MAY15,
        TypicalDailyPlans.MAY16, TypicalDailyPlans.MAY17, TypicalDailyPlans.MAY18, TypicalDailyPlans.MAY19,
        TypicalDailyPlans.MAY20, TypicalDailyPlans.MAY21, TypicalDailyPlans.MAY22, TypicalDailyPlans.MAY23,
        TypicalDailyPlans.MAY24, TypicalDailyPlans.MAY25
    };

    public static final MonthlyPlan APR26_PLAN = new MonthlyPlan(DAILY_PLANS, DEFAULT_DATE);
}
