package seedu.task.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.task.model.Planner;
import seedu.task.model.calendar.DailyPlan;

/**
 * A utility class containing a list of {@code TypicalDailyPlans} objects to be used in tests.
 */

public class TypicalDailyPlans {
    public static final DailyPlan APRIL26 = new DailyPlan(5, TypicalLocalDates.APR26);
    public static final DailyPlan APRIL27 = new DailyPlan(5, TypicalLocalDates.APR27);
    public static final DailyPlan APRIL28 = new DailyPlan(5, TypicalLocalDates.APR28);
    public static final DailyPlan APRIL29 = new DailyPlan(5, TypicalLocalDates.APR29);
    public static final DailyPlan APRIL30 = new DailyPlan(5, TypicalLocalDates.APR30);
    public static final DailyPlan MAY1 = new DailyPlan(5, TypicalLocalDates.MAY1);
    public static final DailyPlan MAY2 = new DailyPlan(5, TypicalLocalDates.MAY2);
    public static final DailyPlan MAY3 = new DailyPlan(5, TypicalLocalDates.MAY3);
    public static final DailyPlan MAY4 = new DailyPlan(5, TypicalLocalDates.MAY4);
    public static final DailyPlan MAY5 = new DailyPlan(5, TypicalLocalDates.MAY5);
    public static final DailyPlan MAY6 = new DailyPlan(5, TypicalLocalDates.MAY6);
    public static final DailyPlan MAY7 = new DailyPlan(5, TypicalLocalDates.MAY7);
    public static final DailyPlan MAY8 = new DailyPlan(5, TypicalLocalDates.MAY8);
    public static final DailyPlan MAY9 = new DailyPlan(5, TypicalLocalDates.MAY9);
    public static final DailyPlan MAY10 = new DailyPlan(5, TypicalLocalDates.MAY10);
    public static final DailyPlan MAY11 = new DailyPlan(5, TypicalLocalDates.MAY11);
    public static final DailyPlan MAY12 = new DailyPlan(5, TypicalLocalDates.MAY12);
    public static final DailyPlan MAY13 = new DailyPlan(5, TypicalLocalDates.MAY13);
    public static final DailyPlan MAY14 = new DailyPlan(5, TypicalLocalDates.MAY14);
    public static final DailyPlan MAY15 = new DailyPlan(5, TypicalLocalDates.MAY15);
    public static final DailyPlan MAY16 = new DailyPlan(5, TypicalLocalDates.MAY16);
    public static final DailyPlan MAY17 = new DailyPlan(5, TypicalLocalDates.MAY17);
    public static final DailyPlan MAY18 = new DailyPlan(5, TypicalLocalDates.MAY18);
    public static final DailyPlan MAY19 = new DailyPlan(5, TypicalLocalDates.MAY19);
    public static final DailyPlan MAY20 = new DailyPlan(5, TypicalLocalDates.MAY20);
    public static final DailyPlan MAY21 = new DailyPlan(5, TypicalLocalDates.MAY21);
    public static final DailyPlan MAY22 = new DailyPlan(5, TypicalLocalDates.MAY22);
    public static final DailyPlan MAY23 = new DailyPlan(5, TypicalLocalDates.MAY23);
    public static final DailyPlan MAY24 = new DailyPlan(5, TypicalLocalDates.MAY24);
    public static final DailyPlan MAY25 = new DailyPlan(5, TypicalLocalDates.MAY25);

    /**
     * Returns an {@code Planner} with all the typical DailyPlans.
     */
    public static Planner getTypicalDailyPlans() {
        Planner p = new Planner();
        for (DailyPlan dp : getDailyPlans()) {
            p.addDailyPlan(dp);
        }
        return p;
    }

    private static List<DailyPlan> getDailyPlans() {
        return new ArrayList<>((Arrays.asList(APRIL26, APRIL27, APRIL28, APRIL29, APRIL30, MAY1, MAY2, MAY3, MAY4,
                MAY5, MAY6, MAY7, MAY8, MAY9, MAY10, MAY11, MAY12, MAY13, MAY14, MAY15, MAY16, MAY17, MAY18, MAY19,
                MAY20, MAY21, MAY22, MAY23, MAY24, MAY25)));
    }
}
