package seedu.address.model.application;

import java.util.Comparator;

import seedu.address.model.task.Task;

/**
 * Compares two applications based on the deadline of their upcoming task.
 * Applications with no tasks are ranked lower.
 */
public class DeadlineComparator implements Comparator<Application> {

    @Override
    public int compare(Application appOne, Application appTwo) {
        if (appOne.hasTask() && appTwo.hasTask()) {
            Task taskOne = appOne.getTask();
            Task taskTwo = appTwo.getTask();
            if (taskOne.getDeadline().isBefore(taskTwo.getDeadline())) {
                return -1;
            } else if (taskOne.getDeadline().isAfter(taskTwo.getDeadline())) {
                return 1;
            }
        } else {
            if (appOne.hasTask() && !appTwo.hasTask()) {
                return -1;
            } else if (!appOne.hasTask() && appTwo.hasTask()) {
                return 1;
            }
        }
        return 0;
    }
}
