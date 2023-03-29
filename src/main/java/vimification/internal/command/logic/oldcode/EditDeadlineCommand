package vimification.internal.command.logic;

import vimification.commons.core.Index;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

public class EditDeadlineCommand extends UndoableLogicCommand {
    public static final String COMMAND_WORD = "e -d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edit the deadline of a task.\n"
            + "Parameters: INDEX (index number of the target task in the displayed task list)\n"
            + "          : DEADLINE\n"
            + "Conditions: Index must be positive integer and cannot exceed total number of tasks.\n"
            + "          : Date time must be valid in the format of YYYY-MM-DD.\n"
            + "Example: " + COMMAND_WORD + " 1" + " 2023-01-01";

    public static final String SUCCESS_MESSAGE_FORMAT =
            "Deadline of task %1$s updated.";
    public static final String UNDO_MESSAGE =
            "The command has been undone. The deadline of the task has been changed back.";

    private final Index targetIndex;
    private final LocalDateTime newDeadline;
    private LocalDateTime oldDeadline;


    public EditDeadlineCommand(Index targetIndex, LocalDateTime newDeadline) {
        this.targetIndex = targetIndex;
        this.newDeadline = newDeadline;
        this.oldDeadline = null;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList)
            throws IndexOutOfBoundsException, CommandException {
        requireNonNull(taskList);
        Task editedTask = taskList.get(targetIndex.getZeroBased());
        oldDeadline = editedTask.getDeadline();
        editedTask.setDeadline(newDeadline);
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, targetIndex.getOneBased()));
    }

    @Override
    public CommandResult undo(LogicTaskList taskList)
            throws IndexOutOfBoundsException, CommandException {
        requireNonNull(taskList);
        taskList.get(targetIndex.getZeroBased()).setDeadline(oldDeadline);
        return new CommandResult(UNDO_MESSAGE);
    }
}
