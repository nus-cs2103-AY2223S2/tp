package vimification.internal.command.logic;

import vimification.commons.core.Index;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;


public class EditTitleCommand extends UndoableLogicCommand{
    public static final String COMMAND_WORD = "e -t";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edit the title of a task.\n"
            + "Parameters: Index (index number of the target task in the displayed task list)\n"
            + "          : Title\n"
            + "Conditions: Index must be positive integer and cannot exceed total number of tasks.\n"
            + "          : Title must not be empty.\n"
            + "Example: " + COMMAND_WORD + " 1" + " 2023-01-01";

    public static final String SUCCESS_MESSAGE_FORMAT =
            "Title of task %1$s updated.";
    public static final String UNDO_MESSAGE =
            "The command has been undone. The title of the task has been changed back.";

    private final Index targetIndex;
    private final String newTitle;
    private String oldTitle;


    public EditTitleCommand(Index targetIndex, String newTitle) {
        this.targetIndex = targetIndex;
        this.newTitle = newTitle;
        this.oldTitle = null;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList)
            throws IndexOutOfBoundsException, CommandException {
        requireNonNull(taskList);
        int zero_based_index = targetIndex.getZeroBased();
        oldTitle = taskList.getTitle(zero_based_index);
        taskList.setTitle(zero_based_index, newTitle);
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, targetIndex.getOneBased()));
    }

    @Override
    public CommandResult undo(LogicTaskList taskList)
            throws IndexOutOfBoundsException, CommandException {
        requireNonNull(taskList);
        int zero_based_index = targetIndex.getZeroBased();
        taskList.setTitle(zero_based_index, newTitle);
        return new CommandResult(UNDO_MESSAGE);
    }
}
