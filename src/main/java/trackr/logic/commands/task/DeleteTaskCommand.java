package trackr.logic.commands.task;

import trackr.commons.core.index.Index;
import trackr.logic.commands.DeleteItemCommand;
import trackr.model.ModelEnum;
import trackr.model.task.Task;

/**
 * Deletes a task identified using it's displayed index from the task list.
 */
public class DeleteTaskCommand extends DeleteItemCommand<Task> {

    public static final String COMMAND_WORD = "delete_task";
    public static final String COMMAND_WORD_SHORTCUT = "delete_t";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    /**
     * Creates a DeleteTaskCommand to delete the task at the given index.
     *
     * @param targetIndex The index of the task to be deleted.
     */
    public DeleteTaskCommand(Index targetIndex) {
        super(targetIndex, ModelEnum.TASK);
    }
}
