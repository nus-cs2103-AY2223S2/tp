package vimification.internal.command.logic;

import static java.util.Objects.requireNonNull;

import vimification.commons.core.Index;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;
import vimification.model.task.Priority;
import vimification.model.task.Task;

public class EditPriorityCommand extends UndoableLogicCommand {
    public static final String COMMAND_WORD = "-p";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edit priority level of a task.\n"
            + "Parameters: INDEX (index number of the target task in the displayed task list)\n"
            + "          : PRIORITY LEVEL (high, med or low)\n"
            + "Conditions: Index must be positive integer and cannot exceed total number of tasks.\n"
            + "Example: " + COMMAND_WORD + " high" + " 1";

    public static final String SUCCESS_MESSAGE_FORMAT =
            "Priority of task %1$s updated.";
    public static final String UNDO_MESSAGE =
            "The command has been undone. The priority of the task has been changed back.";

    // targetIndex is ZERO-BASED
    private final Index targetIndex;
    private final Priority newPriority;
    private Priority oldPriority;


    public EditPriorityCommand(Index targetIndex, Priority newPriority) {
        this.targetIndex = targetIndex;
        this.newPriority = newPriority;
        this.oldPriority = null;
    }

    public EditPriorityCommand(Index targetIndex, int newLevel) {
        this(targetIndex, Priority.fromInt(newLevel));
    }

    @Override
    public CommandResult execute(LogicTaskList taskList) throws CommandException {
        requireNonNull(taskList);
        Task editedTask = taskList.get(targetIndex.getZeroBased());
        oldPriority = editedTask.getPriority();
        editedTask.setPriority(newPriority);
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, targetIndex.getOneBased()));
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) throws CommandException {
        requireNonNull(taskList);
        taskList.get(targetIndex.getZeroBased()).setPriority(oldPriority);
        return new CommandResult(UNDO_MESSAGE);
    }
}
