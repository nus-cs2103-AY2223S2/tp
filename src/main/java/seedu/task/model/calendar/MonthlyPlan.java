package seedu.task.model.calendar;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import seedu.task.model.task.Deadline;
import seedu.task.model.task.DeadlineList;
import seedu.task.model.task.EventList;
import seedu.task.model.task.SimpleTask;
import seedu.task.model.task.SimpleTaskList;

/**
 * A 30-day overview of the work left to be done.
 */
public class MonthlyPlan {
    private static LocalDate dateGenerated;
    private static int DATE_NOT_FOUND = -1;
    private DailyPlan[] dailyPlans = new DailyPlan[30];

    /**
     * Instantiates 30 days of daily plans starting from the date the command ran
     * @param workload amount of work user is willing to put in a day
     * @param currentDate date command is ran
     */
    public MonthlyPlan(long workload, LocalDate currentDate) {
        dateGenerated = currentDate;
        for (int i = 0; i < 30; i++) {
            dailyPlans[i] = new DailyPlan(workload, currentDate.plusDays(i));
        }
    }

    /**
     * Overloaded constructor used for testing.
     * Should not be called in regular program execution
     * @param dp DailyPlan stub
     * @param today LocalDate stub
     */
    public MonthlyPlan(DailyPlan[] dp, LocalDate today) {
        this.dailyPlans = dp;
        this.dateGenerated = today;
    }

    /**
     * Check for events occurring each day and allocate count effort expenditure
     * @param list all events currently stored in TaskBook
     */
    public void allocateEvents(EventList list) {
        for (int i = 0; i < 30; i++) {
            dailyPlans[i].allocateEvent(list);
        }
    }

    /**
     * Checks for days to assign Deadlines to.
     * Assign to the first free day, if there is one (add Deadline without exceeding maximum effort).
     * Assign to the least busy day before Deadline if assigning Deadline to any day will exceed maximum desired effort.
     * Tie-break by assigning to first least busy day before Deadline.
     * @param list Deadlines to select from
     */
    public void allocateDeadlines(DeadlineList list) {
        for (int i = 0; i < list.size(); i++) {
            Deadline d = list.getDeadlineFromIndex(i);
            long daysDueIn = d.getDaysBetween(dateGenerated);
            int daysFromToday = findFirstFreeDate(d.getEffortValue(), daysDueIn);
            if (hasTime(daysFromToday)) {
                dailyPlans[daysFromToday].addTask(d);
            } else {
                int relativelyFreeDay = findMinWorkloadDateBeforeDeadline(daysDueIn);
                dailyPlans[relativelyFreeDay].addTask(d);
            }
        }
    }

    /**
     * Checks for days to assign SimpleTasks to.
     * Assign tasks in descending order of effort required.
     * Assign to first free day (adding task to date will not exceed maximum effort) if possible
     * Assign to most free day if overloading (adding task beyond the minimum workload) is required.
     * @param list SimpleTasks to select from
     */
    public void allocateSimpleTasks(SimpleTaskList list) {
        for (int i = 0; i < list.size(); i++) {
            SimpleTask s = list.getSimpleTaskFromIndex(i);
            int daysFromToday = findMostBusyFreeDate(s.getEffortValue());
            if (hasTime(daysFromToday)) {
                dailyPlans[daysFromToday].addTask(s);
            } else {
                int relativelyFreeDay = findMinWorkloadDate();
                dailyPlans[relativelyFreeDay].addTask(s);
            }
        }
    }

    /**
     * Finds the most busy date, such that adding the task will not exceed the maximum workload
     * @param workload effort associated with the task
     * @return integer representing the relative number of days from the day the command was run
     */
    public int findMostBusyFreeDate(long workload) {
        long interval = Long.MAX_VALUE;
        int date = -1;
        for (int i = 0; i < 30; i++) {
            long spareTime = dailyPlans[i].getSpareTime();
            if (spareTime < interval && workload <= spareTime) {
                interval = spareTime;
                date = i;
            }
        }
        return date;
    }

    /**
     * Find first date to do Deadline task without exceeding maximum workload.
     * @param workload amount of effort required to do the work
     * @param daysDueIn last day to consider for task
     * @return number of days relative to the day command is run, -1 if no such date is found.
     */
    public int findFirstFreeDate(long workload, long daysDueIn) {
        for (int i = 0; i < 30; i++) {
            if (daysDueIn < i) {
                break;
            }
            if (dailyPlans[i].hasSpareTime(workload)) {
                return i;
            }
        }
        return DATE_NOT_FOUND;
    }

    /**
     * Helper function to find date with the least work (in terms of effort) before the specified deadline.
     * @return number of days relative to the date command is run
     */
    private int findMinWorkloadDateBeforeDeadline(long days) {
        long min = Long.MAX_VALUE;
        int date = 0;
        for (int i = 0; i < days; i++) {
            long work = dailyPlans[i].getCurrentWorkload();
            if (work < min) {
                date = i;
                min = work;
            }
        }
        return date;
    }

    /**
     * Helper function to find date with the least work (in terms of effort)
     * @return number of days relative to the date command is run
     */
    private int findMinWorkloadDate() {
        long min = Long.MAX_VALUE;
        int date = 0;
        for (int i = 0; i < 30; i++) {
            long work = dailyPlans[i].getCurrentWorkload();
            if (work < min) {
                date = i;
                min = work;
            }
        }
        return date;
    }

    /**
     * Converts invalid output into boolean for processing
     * @param daysFromToday computed relative number of days from other methods
     * @return boolean representing the validity of input dates
     */
    private boolean hasTime(int daysFromToday) {
        if (daysFromToday >= 0) {
            return true;
        }
        return false;
    }

    public List<DailyPlan> getDailyPlans() {
        return Arrays.asList(this.dailyPlans);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof MonthlyPlan)) {
            return false;
        } else {
            MonthlyPlan mp = (MonthlyPlan) other;
            for (int i = 0; i < 30; i++) {
                if (!mp.dailyPlans[i].equals(this.dailyPlans[i])) {
                    return false;
                }
            }
            return dateGenerated.equals(mp.dateGenerated);
        }
    }
}
