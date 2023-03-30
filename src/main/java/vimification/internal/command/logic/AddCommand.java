package vimification.internal.command.logic;

import static java.util.Objects.requireNonNull;

import vimification.internal.command.CommandResult;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

/**
 * Creates a new task and adds it to the task planner.
 */
public class AddCommand extends UndoableLogicCommand {
    public static final String COMMAND_WORD = "a";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": adds a task to the displayed task list.\n"
            + "Parameters: DESCRIPTION (description of the task to be added)\n"
            + "Conditions: Description cannot be empty.\n"
            + "Example: " + COMMAND_WORD + " quiz";

    public static final String SUCCESS_MESSAGE = "A new task has been created.";
    public static final String UNDO_MESSAGE =
            "The command has been undone. The new task has been deleted.";

    private final Task addedTask;

    /**
     * Creates an AddCommand to add the specified {@code Task}
     */
    public AddCommand(Task addedTask) {
        requireNonNull(addedTask);
        this.addedTask = addedTask;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList, CommandStack commandStack) {
        requireNonNull(taskList);
        taskList.add(addedTask);
        commandStack.push(this);
        return new CommandResult(SUCCESS_MESSAGE);
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) {
        requireNonNull(taskList);
        taskList.pop();
        return new CommandResult(UNDO_MESSAGE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                        && addedTask.equals(((AddCommand) other).addedTask));
    }
}
