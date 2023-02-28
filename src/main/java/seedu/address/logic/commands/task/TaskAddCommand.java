package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Parses input arguments and creates a new TaskAddCommand object
 */
public class TaskAddCommand extends TaskCommand {
    public static final String TASK_COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + TASK_COMMAND_WORD + ": Adds a Task to SoConnect. \n"
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "Example: " + COMMAND_WORD + " " + TASK_COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Clean fresh water tank ";

    public static final String MESSAGE_SUCCESS = "New Task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This Task already exists in Fish Ahoy!";

    private final Task toAdd;

    /**
     * Constructs a {@code TaskAddCommand} with the {@code Task} to be added to the {@code TaskList}.
     */
    public TaskAddCommand(Task task) {
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
                || (other instanceof TaskAddCommand // instanceof handles nulls
                && toAdd.equals(((TaskAddCommand) other).toAdd));
    }
}
