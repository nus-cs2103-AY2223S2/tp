package trackr.logic.commands.task;

import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;

import trackr.logic.commands.AddItemCommand;
import trackr.model.ModelEnum;
import trackr.model.task.Task;
/**
 * Adds a task to the task list.
 */
public class AddTaskCommand extends AddItemCommand<Task> {
    public static final String COMMAND_WORD = "add_task";
    public static final String COMMAND_WORD_SHORTCUT = "add_t";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task list. "
            + "Parameters: "
            + PREFIX_NAME + "TASK NAME "
            + PREFIX_DEADLINE + "DEADLINE "
            + "[" + PREFIX_STATUS + "STATUS]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Sort Inventory "
            + PREFIX_DEADLINE + "01/01/2024 "
            + PREFIX_STATUS + "N ";

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}.
     *
     * @param task The task to be added.
     */
    public AddTaskCommand(Task task) {
        super(task, ModelEnum.TASK);
    }
}
