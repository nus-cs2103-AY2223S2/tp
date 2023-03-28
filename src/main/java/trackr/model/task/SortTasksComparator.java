package trackr.model.task;

import java.util.Comparator;

import static trackr.commons.util.CollectionUtil.requireAllNonNull;

public class SortTasksComparator implements Comparator<Task> {
    /**
     * Constructs SortTasksComparator.
     */
    public SortTasksComparator() {
        super();
    }

    @Override
    public int compare(Task task1, Task task2) {
        requireAllNonNull(task1, task2);
        return task1.compare(task2);
    }
}
