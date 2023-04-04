package seedu.task.testutil;

import static seedu.task.testutil.TypicalDeadlines.CHICKEN_EXPIRY;
import static seedu.task.testutil.TypicalDeadlines.SUSHI_EXPIRY;
import static seedu.task.testutil.TypicalEvents.EXAM;
import static seedu.task.testutil.TypicalEvents.SLEEPOVER;
import static seedu.task.testutil.TypicalTasks.ALICE;
import static seedu.task.testutil.TypicalTasks.BENSON;
import static seedu.task.testutil.TypicalTasks.CARL;
import static seedu.task.testutil.TypicalTasks.DANIEL;
import static seedu.task.testutil.TypicalTasks.ELLE;
import static seedu.task.testutil.TypicalTasks.FIONA;
import static seedu.task.testutil.TypicalTasks.GEORGE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.task.model.Planner;
import seedu.task.model.calendar.DailyPlan;
import seedu.task.model.task.Task;

/**
 * A utility class containing a list of {@code TypicalDailyPlans} objects to be used in tests.
 */

public class TypicalDailyPlans {
    public static final int DEFAULT_WORKLOAD = 24;
    public static final DailyPlan APRIL26 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.APR26);
    public static final DailyPlan APRIL27 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.APR27);
    public static final DailyPlan APRIL28 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.APR28);
    public static final DailyPlan APRIL29 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.APR29);
    public static final DailyPlan APRIL30 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.APR30);
    public static final DailyPlan MAY1 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY1);
    public static final DailyPlan MAY2 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY2);
    public static final DailyPlan MAY3 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY3);
    public static final DailyPlan MAY4 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY4);
    public static final DailyPlan MAY5 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY5);
    public static final DailyPlan MAY6 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY6);
    public static final DailyPlan MAY7 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY7);
    public static final DailyPlan MAY8 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY8);
    public static final DailyPlan MAY9 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY9);
    public static final DailyPlan MAY10 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY10);
    public static final DailyPlan MAY11 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY11);
    public static final DailyPlan MAY12 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY12);
    public static final DailyPlan MAY13 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY13);
    public static final DailyPlan MAY14 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY14);
    public static final DailyPlan MAY15 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY15);
    public static final DailyPlan MAY16 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY16);
    public static final DailyPlan MAY17 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY17);
    public static final DailyPlan MAY18 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY18);
    public static final DailyPlan MAY19 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY19);
    public static final DailyPlan MAY20 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY20);
    public static final DailyPlan MAY21 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY21);
    public static final DailyPlan MAY22 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY22);
    public static final DailyPlan MAY23 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY23);
    public static final DailyPlan MAY24 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY24);
    public static final DailyPlan MAY25 = new DailyPlan(DEFAULT_WORKLOAD, TypicalLocalDates.MAY25);
    public static final DailyPlan JULY18 = new DailyPlan(21, TypicalLocalDates.JULY18);
    public static final DailyPlan AUG22 = new DailyPlan(21, TypicalLocalDates.AUGUST22);

    /**
     * Returns an {@code Planner} with all the typical DailyPlans.
     */
    public static Planner getTypicalPlanner() {
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

    /**
     * SimpleTasks allocated to Apr 26 in test.
     */
    public static DailyPlan april26SimpleTask() {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(ELLE);
        return new DailyPlan(taskList, 24, 24, LocalDate.parse("2023-04-26"));
    }

    /**
     * SimpleTasks allocated to Apr 27 in test.
     */
    public static DailyPlan april27SimpleTask() {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(FIONA);
        return new DailyPlan(taskList, 24, 24, LocalDate.parse("2023-04-27"));
    }

    /**
     * SimpleTasks allocated to Apr 28 in test.
     */
    public static DailyPlan april28SimpleTask() {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(GEORGE);
        return new DailyPlan(taskList, 24, 24, LocalDate.parse("2023-04-28"));
    }

    /**
     * SimpleTasks allocated to Apr 29 in test.
     */
    public static DailyPlan april29SimpleTask() {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(DANIEL);
        taskList.add(BENSON);
        taskList.add(CARL);
        taskList.add(ALICE);
        return new DailyPlan(taskList, 24, 15, LocalDate.parse("2023-04-29"));
    }

    /**
     * Deadlines allocated to Apr 26 in test.
     */
    public static DailyPlan april26Deadline() {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(CHICKEN_EXPIRY);
        return new DailyPlan(taskList, 24, 28, LocalDate.parse("2023-04-26"));
    }

    /**
     * Deadline allocated to Apr 27 in test.
     */
    public static DailyPlan april27Deadline() {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(SUSHI_EXPIRY);
        return new DailyPlan(taskList, 24, 12, LocalDate.parse("2023-04-27"));
    }

    /**
     * Events allocated to Apr 26 in test.
     */
    public static DailyPlan april26Event() {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(EXAM);
        taskList.add(SLEEPOVER);
        return new DailyPlan(taskList, 24, 6, LocalDate.parse("2023-04-26"));
    }

    /**
     * Events allocated to Apr 27 in test.
     */
    public static DailyPlan april27Event() {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(SLEEPOVER);
        return new DailyPlan(taskList, 24, 1, LocalDate.parse("2023-04-27"));
    }

    /**
     * Events allocated to Apr 28 in test.
     */
    public static DailyPlan april28Event() {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(SLEEPOVER);
        return new DailyPlan(taskList, 24, 1, LocalDate.parse("2023-04-28"));
    }

    /**
     * Events allocated to Apr 29 in test.
     */
    public static DailyPlan april29Event() {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(SLEEPOVER);
        return new DailyPlan(taskList, 24, 1, LocalDate.parse("2023-04-29"));
    }

    /**
     * Events allocated to Apr 30 in test.
     */
    public static DailyPlan april30Event() {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(SLEEPOVER);
        return new DailyPlan(taskList, 24, 1, LocalDate.parse("2023-04-30"));
    }
}
