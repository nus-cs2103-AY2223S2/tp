package vimification.internal.command.logic;

import static java.util.Objects.requireNonNull;

import vimification.commons.core.Index;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;

public class MarkCommand extends UndoableLogicCommand {
    public static final String COMMAND_WORD = "mark";
    public static final String SUCCESS_MESSAGE_FORMAT = "Task %1$d has been marked as done.";
    public static final String UNDO_MESSAGE =
            "The command has been undoed. The Task %1$d has been unmarked.";

    // targetIndex is ZERO-BASED
    private final Index targetIndex;

    public MarkCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList)
            throws IndexOutOfBoundsException, CommandException {
        requireNonNull(taskList);
        taskList.get(targetIndex.getZeroBased()).mark();
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, targetIndex.getOneBased()));
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) throws CommandException {
        requireNonNull(taskList);
        taskList.get(targetIndex.getZeroBased()).unmark();
        return new CommandResult(String.format(UNDO_MESSAGE, targetIndex.getOneBased()));
    }
}
