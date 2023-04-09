package trackr.model.task;

import trackr.model.ModelEnum;
import trackr.model.item.Item;
import trackr.model.item.UniqueItemList;

/**
 * A list of tasks that enforces uniqueness between its elements and does not allow nulls.
 * A task is considered unique by comparing using {@code Task#isSameItem(Item)}. As such, adding and updating of
 * tasks uses Task#isSameItem(Item) for equality to ensure that the task being added or updated is unique
 * in terms of identity in the UniqueTaskList. However, the removal of a task uses Task#equals(Object) to
 * ensure that the task with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Task#isSameItem(Item)
 */
public class UniqueTaskList extends UniqueItemList<Task> {

    public UniqueTaskList() {
        super(ModelEnum.TASK);
    }
}
