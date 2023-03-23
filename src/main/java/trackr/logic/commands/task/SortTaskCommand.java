package trackr.logic.commands.task;

import trackr.logic.commands.SortItemListCommand;
import trackr.model.ModelEnum;
import trackr.model.task.Task;

/**
 * Sort all tasks in the task list by status and deadline.
 * Tasks with the nearest deadline and not done status will be on top.
 */
public class SortTaskCommand extends SortItemListCommand {

    public static final String COMMAND_WORD = "sort_task";
    public static final String COMMAND_WORD_SHORTCUT = "sort_t";

    public SortTaskCommand() {
        super(ModelEnum.TASK);
    }
}
