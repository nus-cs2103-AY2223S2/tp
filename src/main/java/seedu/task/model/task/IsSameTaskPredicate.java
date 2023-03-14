package seedu.task.model.task;

import java.util.List;
import java.util.function.Predicate;

public class IsSameTaskPredicate implements Predicate<Task> {
    private final List<Task> taskList;

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
