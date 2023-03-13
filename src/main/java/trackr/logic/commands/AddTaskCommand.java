package trackr.logic.commands;

import static java.util.Objects.requireNonNull;
import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;

import trackr.logic.commands.exceptions.CommandException;
import trackr.model.Model;
import trackr.model.task.Task;
/**
 * Adds a task to the task list.
 */
public class AddTaskCommand extends Command {
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

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list";

    private final Task toAdd;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && toAdd.equals(((AddTaskCommand) other).toAdd));
    }
}
