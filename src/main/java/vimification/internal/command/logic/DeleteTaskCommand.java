package vimification.internal.command.logic;

import static java.util.Objects.requireNonNull;

import vimification.commons.core.Index;
import vimification.internal.command.CommandResult;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

/**
 * Deletes a task identified using it's displayed index from the address book.
 */
public class DeleteTaskCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a task\n"
            + "Parameters: INDEX (index number of the target task in the displayed task list)\n"
            + "Conditions: Index must be positive integer and cannot exceed total number of tasks.\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String SUCCESS_MESSAGE_FORMAT = "Task %1$s has been deleted.";
    public static final String UNDO_MESSAGE =
            "The command has been undone. The deleted task has been added back.";

    // targetIndex is ZERO-BASED
    private final Index targetIndex;
    private Task deletedTask = null;

    public DeleteTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList, CommandStack commandStack) {
        requireNonNull(taskList);
        deletedTask = taskList.remove(targetIndex.getZeroBased());
        commandStack.push(this);
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, deletedTask.forDisplay()));
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) {
        requireNonNull(taskList);
        taskList.add(targetIndex.getZeroBased(), deletedTask);
        return new CommandResult(UNDO_MESSAGE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                        && targetIndex.equals(((DeleteTaskCommand) other).targetIndex));
    }
}
