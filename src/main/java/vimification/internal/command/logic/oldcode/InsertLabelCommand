package vimification.internal.command.logic;

import static java.util.Objects.requireNonNull;
import static vimification.commons.util.CollectionUtil.requireAllNonNull;

import vimification.commons.core.Index;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;

public class InsertLabelCommand extends UndoableLogicCommand {

    public static final String COMMAND_WORD = "add_tag";
    public static final String SUCCESS_MESSAGE_FORMAT =
            "Task %2$d has been added with the %1$s tag.";
    public static final String UNDO_MESSAGE =
            "The command has been undoed. The new tag has been removed.";

    private final String label;
    private final Index targetIndex;

    /**
     * Creates an AddTagCommand to add the specified {@code Task}
     */
    public InsertLabelCommand(String label, Index targetIndex) {
        requireAllNonNull(label, targetIndex);
        this.label = label;
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) throws CommandException {
        requireNonNull(taskList);
        taskList.get(targetIndex.getZeroBased()).removeLabel(label);
        return new CommandResult(UNDO_MESSAGE);
    }

    @Override
    public CommandResult execute(LogicTaskList taskList) throws CommandException {
        requireNonNull(taskList);
        taskList.get(targetIndex.getZeroBased()).addLabel(label);
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, label, targetIndex));
    }
}
