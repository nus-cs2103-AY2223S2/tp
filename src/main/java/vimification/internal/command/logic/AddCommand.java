package vimification.internal.command.logic;

import static java.util.Objects.requireNonNull;

import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

/**
 * Creates a new task and adds it to the task planner.
 */
public class AddCommand extends UndoableLogicCommand {
    public static final String COMMAND_WORD = "i";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": adds a task to the displayed task list.\n"
            + "Parameters: DESCRIPTION (description of the task to be added)\n"
            + "Conditions: Description cannot be empty.\n"
            + "Example: " + COMMAND_WORD + " quiz";

    public static final String SUCCESS_MESSAGE_FORMAT = "Task %1$s created";
    public static final String UNDO_MESSAGE =
            "The command has been undone. The new task has been deleted.";

    private final Task newTask;

    /**
     * Creates an AddCommand to add the specified {@code Task}
     */
    public AddCommand(Task task) {
        requireNonNull(task);
        newTask = task;
    }

    // Pass a TaskList instead
    @Override
    public CommandResult execute(LogicTaskList taskList) throws CommandException {
        requireNonNull(taskList);
        taskList.add(newTask);
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, newTask));
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) throws CommandException {
        requireNonNull(taskList);
        taskList.pop();
        return new CommandResult(UNDO_MESSAGE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                        && newTask.equals(((AddCommand) other).newTask));
    }
}
