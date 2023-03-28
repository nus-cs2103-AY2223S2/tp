package vimification.internal.command.logic;

import static java.util.Objects.requireNonNull;

import vimification.commons.core.Index;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;
import vimification.model.task.Priority;

public class SetPriorityCommand extends UndoableLogicCommand {
    public static final String COMMAND_WORD = "set_priority";

    public static final String SUCCESS_MESSAGE_FORMAT =
            "The priority of Task %1$s has been updated.";
    public static final String UNDO_MESSAGE =
            "The command has been undoed. The deleted task has been added back.";

    // targetIndex is ZERO-BASED
    private final Index targetIndex;
    private final Priority newPriority;
    private Priority oldPriority;


    public SetPriorityCommand(Index targetIndex, Priority newPriority) {
        this.targetIndex = targetIndex;
        this.newPriority = newPriority;
        this.oldPriority = null;
    }

    public SetPriorityCommand(Index targetIndex, int newLevel) {
        this(targetIndex, Priority.fromInt(newLevel));
    }

    @Override
    public CommandResult execute(LogicTaskList taskList)
            throws IndexOutOfBoundsException, CommandException {
        requireNonNull(taskList);
        int zero_based_index = targetIndex.getZeroBased();
        oldPriority = taskList.getPriority(zero_based_index);
        taskList.setPriority(zero_based_index, newPriority);
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
