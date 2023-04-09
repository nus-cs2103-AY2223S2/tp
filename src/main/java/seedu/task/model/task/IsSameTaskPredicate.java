package seedu.task.model.task;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests if a {@code Task} is in the day's planned task
 */
public class IsSameTaskPredicate implements Predicate<Task> {
    private final List<Task> taskList;

    /**
     * Creates a {@code IsSameTaskPredicate} with a list of tasks to check against.
     * @param taskList list of tasks to be checked against
     */
    public IsSameTaskPredicate(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public boolean test(Task task) {
        int size = this.taskList.size();
        for (int i = 0; i < size; i++) {
            if (taskList.get(i).equals(task)) {
                return true;
            }
        }
        return false;
    }
}
