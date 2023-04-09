package trackr.model;

import trackr.model.item.ItemList;
import trackr.model.task.Task;

/**
 * Wraps all data at the task-list level.
 * Duplicates are not allowed (by .isSameItem comparison).
 */
public class TaskList extends ItemList<Task> implements ReadOnlyTaskList {

    public TaskList() {
        super();
    }

    /**
     * Creates a TaskList using the Tasks in the {@code toBeCopied}.
     */
    public TaskList(ReadOnlyTaskList toBeCopied) {
        super(toBeCopied);
    }
}
