package vimification.logic.commands;

import static java.util.Objects.requireNonNull;

import vimification.model.LogicTaskList;
import vimification.model.task.Task;

/**
 * Deletes a task identified using it's displayed index from the address book.
 */
public class DeleteCommand extends UndoableLogicCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: delete 1";

    public static final String SUCCESS_MESSAGE_FORMAT = "Deleted Task: %1$s";
    public static final String UNDO_MESSAGE =
            "The command has been undoed. The deleted task has been added back.";

    // targetIndex is ZERO-BASED
    private final int targetIndex;
    private Task deletedTask;

    public DeleteCommand(int targetIndex) {
        this.targetIndex = targetIndex;
        this.deletedTask = null;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList)
            throws IndexOutOfBoundsException, CommandException {
        requireNonNull(taskList);
        deletedTask = taskList.remove(targetIndex);
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, deletedTask));
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) throws CommandException {
        requireNonNull(taskList);
        taskList.add(targetIndex, deletedTask);
        return new CommandResult(String.format(UNDO_MESSAGE, deletedTask));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }

        if (!(other instanceof DeleteCommand)) { // instanceof handles nulls
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetIndex == otherDeleteCommand.targetIndex // state check
                && ((this.deletedTask == null && otherDeleteCommand.deletedTask == null)
                        || deletedTask.equals(otherDeleteCommand.deletedTask));
    }
}
