package vimification.internal.command.logic;

import static java.util.Objects.requireNonNull;

import vimification.commons.core.Index;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;
import vimification.model.task.Status;
import vimification.model.task.Task;

public class EditStatusCommand extends UndoableLogicCommand {
    public static final String COMMAND_WORD = "e -s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edit the status of a task.\n"
            + "Parameters: Index (index number of the target task in the displayed task list)\n"
            + "          : Status (0 for not done, 1 for in progress, 2 for completed, 3 for overdue).\n"
            + "Conditions: Index must be positive integer and cannot exceed total number of tasks.\n"
            + "          : Status cannot be empty.\n"
            + "Example: " + COMMAND_WORD + " 1" + " 2";

    public static final String SUCCESS_MESSAGE_FORMAT =
            "Status of task %1$s updated.";
    public static final String UNDO_MESSAGE =
            "The command has been undone. The status of the task has been changed back.";

    private final Index targetIndex;
    private final Status newStatus;
    private Status oldStatus;


    public EditStatusCommand(Index targetIndex, Status newStatus) {
        this.targetIndex = targetIndex;
        this.newStatus = newStatus;
        this.oldStatus = null;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList) throws CommandException {
        requireNonNull(taskList);
        Task editedTask = taskList.get(targetIndex.getZeroBased());
        oldStatus = editedTask.getStatus();
        editedTask.setStatus(newStatus);
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, targetIndex.getOneBased()));
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) throws CommandException {
        requireNonNull(taskList);
        taskList.get(targetIndex.getZeroBased()).setStatus(oldStatus);
        return new CommandResult(UNDO_MESSAGE);
    }
}
