package trackr.model;

import trackr.model.item.ReadOnlyItemList;
import trackr.model.task.Task;

/**
 * Unmodifiable view of a task list.
 */
public interface ReadOnlyTaskList extends ReadOnlyItemList<Task> {
}
