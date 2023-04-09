package trackr.logic.commands.task;

import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;

import trackr.commons.core.index.Index;
import trackr.logic.commands.EditItemCommand;
import trackr.model.ModelEnum;
import trackr.model.item.ItemDescriptor;
import trackr.model.task.Task;
import trackr.model.task.TaskDeadline;
import trackr.model.task.TaskDescriptor;
import trackr.model.task.TaskName;
import trackr.model.task.TaskStatus;

/**
 * Edits the details of an existing task in the task list.
 */
public class EditTaskCommand extends EditItemCommand<Task> {

    public static final String COMMAND_WORD = "edit_task";
    public static final String COMMAND_WORD_SHORTCUT = "edit_t";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "TASK NAME] "
            + "[" + PREFIX_DEADLINE + "TASK DEADLINE] "
            + "[" + PREFIX_STATUS + "TASK STATUS]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Buy sugar "
            + PREFIX_STATUS + "D";

    /**
     * Creates an EditTaskCommand to edit the task at given index.
     * @param index              of the task in the filtered task list to edit.
     * @param editTaskDescriptor details to edit the task with.
     */
    public EditTaskCommand(Index index, TaskDescriptor editTaskDescriptor) {
        super(index, new TaskDescriptor(editTaskDescriptor), ModelEnum.TASK);
    }

    @Override
    protected Task createEditedItem(Task itemToEdit, ItemDescriptor<? super Task> itemDescriptor) {
        assert itemToEdit != null;

        TaskDescriptor taskDescriptor = (TaskDescriptor) itemDescriptor;

        TaskName updatedTaskName =
                taskDescriptor.getTaskName().orElse(itemToEdit.getTaskName());
        TaskDeadline updatedTaskDeadline =
                taskDescriptor.getTaskDeadline().orElse(itemToEdit.getTaskDeadline());
        TaskStatus updatedTaskStatus =
                taskDescriptor.getTaskStatus().orElse(itemToEdit.getTaskStatus());

        return new Task(updatedTaskName, updatedTaskDeadline, updatedTaskStatus);
    }
}
