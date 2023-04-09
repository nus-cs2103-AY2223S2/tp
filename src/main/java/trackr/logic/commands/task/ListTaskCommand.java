package trackr.logic.commands.task;

import trackr.logic.commands.ListItemCommand;
import trackr.model.ModelEnum;
import trackr.model.task.Task;

/**
 * Lists all tasks in the task list to the user.
 */
public class ListTaskCommand extends ListItemCommand<Task> {

    public static final String COMMAND_WORD = "list_task";
    public static final String COMMAND_WORD_SHORTCUT = "list_t";

    /**
     * Creates a ListTaskCommand to list all the tasks.
     */
    public ListTaskCommand() {
        super(ModelEnum.TASK);
    }
}
