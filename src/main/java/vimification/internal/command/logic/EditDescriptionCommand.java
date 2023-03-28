package vimification.internal.command.logic;

import vimification.commons.core.Index;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;

import static java.util.Objects.requireNonNull;

public class EditDescriptionCommand extends UndoableLogicCommand{
    public static final String COMMAND_WORD = "e -d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edit the description of a task.\n"
            + "Parameters: Index (index number of the target task in the displayed task list)\n"
            + "          : Description (new description of the task).\n"
            + "Conditions: Index must be positive integer and cannot exceed total number of tasks.\n"
            + "          : Description cannot be empty.\n"
            + "Example: " + COMMAND_WORD + " 1" + " quiz";

    public static final String SUCCESS_MESSAGE_FORMAT =
            "Description of task %1$s updated.";
    public static final String UNDO_MESSAGE =
            "The command has been undone. The description of the task has been changed back.";

    private final Index targetIndex;
    private final String newDescription;
    private String oldDescription;


    public EditDescriptionCommand(Index targetIndex, String newDescription) {
        this.targetIndex = targetIndex;
        this.newDescription = newDescription;
        this.oldDescription = null;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList)
            throws IndexOutOfBoundsException, CommandException {
        requireNonNull(taskList);
        int zero_based_index = targetIndex.getZeroBased();
        oldDescription = taskList.getDescription(zero_based_index);
        taskList.setDescription(zero_based_index, newDescription);
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, targetIndex.getOneBased()));
    }

    @Override
    public CommandResult undo(LogicTaskList taskList)
            throws IndexOutOfBoundsException, CommandException {
        requireNonNull(taskList);
        int zero_based_index = targetIndex.getZeroBased();
        taskList.setDescription(zero_based_index, oldDescription);
        return new CommandResult(UNDO_MESSAGE);
    }
}
