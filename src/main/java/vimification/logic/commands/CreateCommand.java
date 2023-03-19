package vimification.logic.commands;

import static java.util.Objects.requireNonNull;
import static vimification.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static vimification.logic.parser.CliSyntax.PREFIX_EMAIL;
import static vimification.logic.parser.CliSyntax.PREFIX_NAME;
import static vimification.logic.parser.CliSyntax.PREFIX_PHONE;
import static vimification.logic.parser.CliSyntax.PREFIX_TAG;

import vimification.logic.commands.exceptions.CommandException;
import vimification.model.Model;
import vimification.model.task.Task;
import vimification.model.task.TaskType;
import vimification.model.task.Todo;

/**
 * Creates a new task and adds it to the task planner.
 */
public class CreateCommand extends LogicCommand {

    public static final String COMMAND_WORD = "create";
    public static final String MESSAGE_SUCCESS = "New task created: %1$s";
    public static final String UNDO_MESSAGE =
            "The command has been undoed. The new task has been deleted.";
    public static final String MESSAGE_DUPLICATE_TASK =
            "This task already exists in the task planner";

    private final Task newTask;

    /**
     * Creates an CreateCommand to add the specified {@code Task}
     */
    public CreateCommand(Task task) {
        requireNonNull(task);
        newTask = task;
    }

    public CreateCommand(TaskType taskType, String... taskComponents) {
        this(Todo.createTask(taskComponents));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        checkBeforeExecuting();

        if (model.hasTask(newTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(newTask);
        setUndoable(true);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newTask));
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        requireNonNull(model);
        checkBeforeUndoing();

        model.deleteTask(newTask);
        setUndoable(false);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newTask));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateCommand // instanceof handles nulls
                        && newTask.equals(((CreateCommand) other).newTask));
    }
}
