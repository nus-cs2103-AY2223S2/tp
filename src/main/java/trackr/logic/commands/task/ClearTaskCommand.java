package trackr.logic.commands.task;

import trackr.logic.commands.ClearItemCommand;
import trackr.model.ModelEnum;
import trackr.model.task.Task;

/**
 * Clears the task list.
 */
public class ClearTaskCommand extends ClearItemCommand<Task> {

    public static final String COMMAND_WORD = "clear_task";
    public static final String COMMAND_WORD_SHORTCUT = "clear_t";

    public ClearTaskCommand() {
        super(ModelEnum.TASK);
    }
}
