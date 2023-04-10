package seedu.task.model.calendar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import seedu.task.model.task.Event;
import seedu.task.model.task.EventList;
import seedu.task.model.task.Task;

/**
 * Represents a day worth of work planned according to the scheduling algorithm.
 * Handles logic in determining if a task should be done in that day.
 */
public class DailyPlan {
    private ArrayList<Task> taskList = new ArrayList<Task>();
    private long desiredWorkload;
    private long currentWorkload;
    private LocalDate date;

    /**
     * Instantiates a daily plan for users.
     * @param workload Amount of work user wants to put in a day
     * @param date Date which plan is for
     */
    public DailyPlan(long workload, LocalDate date) {
        this.desiredWorkload = workload;
        this.currentWorkload = 0;
        this.date = date;
    }

    /**
     * Constructor used when reading values from JSON.
     */
    public DailyPlan(ArrayList<Task> taskList, long desiredWorkload, long currentWorkload, LocalDate date) {
        this.taskList = taskList;
        this.desiredWorkload = desiredWorkload;
        this.currentWorkload = currentWorkload;
        this.date = date;
    }

    /**
     * Checks through EventList for events occurring on this date.
     * Always add to workload if there is an event happening.
     * @param l list of event to check through
     */
    public void allocateEvent(EventList l) {
        for (int i = 0; i < l.size(); i++) {
            Event e = l.getEventFromIndex(i);
            if (isWithinRange(e)) {
                currentWorkload += e.getEffortValue();
                taskList.add(e);
            }
        }
    }

    /**
     * Adds task into day's work plan.
     * @param t task to be added
     */
    public void addTask(Task t) {
        currentWorkload += t.getEffortValue();
        taskList.add(t);
    }

    /**
     * Checks if task to be removed is in this daily plan. Removes task if found.
     */
    public boolean hasRemoved(Task t) {
        int size = taskList.size();

        for (int i = 0; i < size; i++) {
            if (taskList.get(i) == t) {
                this.currentWorkload = this.currentWorkload - taskList.get(i).getEffortValue();
                taskList.remove(i);
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if target task is in this daily plan. Modifies if task is found, do nothing if task is not found.
     */
    public boolean hasEdited(Task target, Task editedTask) {
        int size = taskList.size();

        for (int i = 0; i < size; i++) {
            if (taskList.get(i) == target) {
                taskList.remove(i);
                taskList.add(i, editedTask);
                return true;
            }
        }

        return false;
    }

    public long getDesiredWorkload() {
        return this.desiredWorkload;
    }
    public long getCurrentWorkload() {
        return this.currentWorkload;
    }

    public List<Task> getTasks() {
        return this.taskList;
    }
    /**
     * Calculates the remaining amount of effort user can allocate to for this day
     * @return effort leftover (can be negative if overloaded)
     */
    public long getSpareTime() {
        return this.desiredWorkload - this.currentWorkload;
    }

    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Checks if plan's date fall within event date
     * @param e event to be checked
     * @return whether task should be added to day's workload
     */
    public boolean isWithinRange(Event e) {
        LocalDate eventStart = e.getFrom().getDate();
        LocalDate eventEnd = e.getTo().getDate();
        if (date.isAfter(eventStart) && date.isBefore(eventEnd) || date.isEqual(eventStart) || date.isEqual(eventEnd)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if a task can be added into a day's worth of work without going over the maximum effort user is willing to
     * put in.
     * @param work amount of effort associated with the task we are considering to include
     * @return indicates if we should include task
     */
    public boolean hasSpareTime(long work) {
        if (currentWorkload + work <= desiredWorkload) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof DailyPlan)) {
            return false;
        } else {
            DailyPlan p = (DailyPlan) other;
            return p.date.equals(this.date)
                    && p.desiredWorkload == this.desiredWorkload
                    && p.currentWorkload == this.currentWorkload;
        }
    }
}
