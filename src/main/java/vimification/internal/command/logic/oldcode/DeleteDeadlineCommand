package vimification.internal.command.logic;

import vimification.commons.core.Index;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

public class DeleteDeadlineCommand extends UndoableLogicCommand {
    public static final String COMMAND_WORD = "d -d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": deletes the deadline of the task.\n"
            + "Parameters: INDEX (index number of the target task in the displayed task list)\n"
            + "Conditions: Index must be positive integer and cannot exceed total number of tasks.\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String SUCCESS_MESSAGE_FORMAT =
            "deadline of task %1$s deleted.";
    public static final String UNDO_MESSAGE =
            "The command has been undone. The deadline of the task has been changed back.";

    private final Index targetIndex;
    private LocalDateTime oldDeadline;

    public DeleteDeadlineCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.oldDeadline = null;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList) throws CommandException {
        requireNonNull(taskList);
        Task editedTask = taskList.get(targetIndex.getZeroBased());
        oldDeadline = editedTask.getDeadline();
        editedTask.deleteDeadline();
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, targetIndex.getOneBased()));
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) throws CommandException {
        requireNonNull(taskList);
        taskList.get(targetIndex.getZeroBased()).setDeadline(oldDeadline);
        return new CommandResult(UNDO_MESSAGE);
    }

}
