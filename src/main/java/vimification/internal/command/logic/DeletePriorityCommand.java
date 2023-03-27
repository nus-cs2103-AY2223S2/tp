package vimification.internal.command.logic;

import vimification.commons.core.Index;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.internal.command.logic.UndoableLogicCommand;
import vimification.model.LogicTaskList;
import vimification.model.task.Priority;

import static java.util.Objects.requireNonNull;

public class DeletePriorityCommand extends UndoableLogicCommand{
    public static final String COMMAND_WORD = "d -p";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the priority of the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String SUCCESS_MESSAGE_FORMAT =
            "Priority of task %1$s deleted.";
    public static final String UNDO_MESSAGE =
            "The command has been undone. The priority of the task has been changed back.";

    private final Index targetIndex;

    private Priority oldPriority;

    public DeletePriorityCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.oldPriority = null;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList)
            throws IndexOutOfBoundsException, CommandException {
        requireNonNull(taskList);
        int zero_based_index = targetIndex.getZeroBased();
        oldPriority = taskList.getPriority(zero_based_index);
        taskList.deletePriority(zero_based_index);
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, targetIndex.getOneBased()));
    }

    @Override
    public CommandResult undo(LogicTaskList taskList)
            throws IndexOutOfBoundsException, CommandException {
        requireNonNull(taskList);
        int zero_based_index = targetIndex.getZeroBased();
        taskList.setPriority(zero_based_index, oldPriority);
        return new CommandResult(UNDO_MESSAGE);
    }

}

