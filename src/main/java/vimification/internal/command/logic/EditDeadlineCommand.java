package vimification.internal.command.logic;

import vimification.commons.core.Index;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;


public class EditDeadlineCommand extends UndoableLogicCommand{
    public static final String COMMAND_WORD = "e -d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edit the deadline of a task.\n"
            + "Parameters: Index (index number of the target task in the displayed task list)\n"
            + "          : Deadline\n"
            + "Conditions: Index must be positive integer and cannot exceed total number of tasks.\n"
            + "          : Date time must be valid in the format of YYYY-MM-DD.\n"
            + "Example: " + COMMAND_WORD + " 1" + " 2023-01-01";

    public static final String SUCCESS_MESSAGE_FORMAT =
            "Deadline of task %1$s updated.";
    public static final String UNDO_MESSAGE =
            "The command has been undone. The deadline of the task has been changed back.";

    private final Index targetIndex;
    private final LocalDateTime newDate;
    private LocalDateTime oldDate;


    public EditDeadlineCommand(Index targetIndex, LocalDateTime newDate) {
        this.targetIndex = targetIndex;
        this.newDate = newDate;
        this.oldDate = null;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList)
            throws IndexOutOfBoundsException, CommandException {
        requireNonNull(taskList);
        int zero_based_index = targetIndex.getZeroBased();
        oldDate = taskList.getDeadline(zero_based_index);
        taskList.setDeadline(zero_based_index, newDate);
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, targetIndex.getOneBased()));
    }

    @Override
    public CommandResult undo(LogicTaskList taskList)
            throws IndexOutOfBoundsException, CommandException {
        requireNonNull(taskList);
        int zero_based_index = targetIndex.getZeroBased();
        taskList.setDeadline(zero_based_index, oldDate);
        return new CommandResult(UNDO_MESSAGE);
    }
}
