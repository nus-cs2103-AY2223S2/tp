package trackr.model.task;

import static trackr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;

/**
 * Comparator used to sort tasks.
 */
public class SortTasksComparator implements Comparator<Task> {
    /**
     * Constructs SortTasksComparator.
     */
    public SortTasksComparator() {
        super();
    }

    /**
     * Compares two given tasks.
     * @param task1 the first task to be compared.
     * @param task2 the second task to be compared.
     * @return -1 if this task is not done while the other task is done,
     *         or if both have the same status but this task has an earlier deadline.
     *         Returns 1 if this task is done while the other task is not done,
     *         or if both have the same status but this task has a later deadline.
     *         Returns 0 if both tasks have the same statuses and deadlines.
     */
    @Override
    public int compare(Task task1, Task task2) {
        requireAllNonNull(task1, task2);
        return task1.compare(task2);
    }
}
