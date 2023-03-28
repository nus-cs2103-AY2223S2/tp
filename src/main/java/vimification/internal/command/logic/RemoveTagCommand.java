package vimification.internal.command.logic;

import static java.util.Objects.requireNonNull;
import static vimification.commons.util.CollectionUtil.requireAllNonNull;

import vimification.commons.core.Index;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;

public class RemoveTagCommand extends UndoableLogicCommand {
    public static final String COMMAND_WORD = "remove_tag";
    public static final String SUCCESS_MESSAGE_FORMAT =
            "The %1$s tag of Task %2$d has been removed.";
    public static final String UNDO_MESSAGE =
            "The command has been undoed. The deleted tag has been added back.";

    private final String deletedTag;
    private final Index targetIndex;

    /**
     * Creates a RemoveTagCommand to add the specified {@code Task}
     */
    public RemoveTagCommand(String tag, Index targetIndex) {
        requireAllNonNull(tag, targetIndex);
        this.deletedTag = tag;
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) throws CommandException {
        requireNonNull(taskList);
        taskList.get(targetIndex.getZeroBased()).addTag(deletedTag);
        return new CommandResult(UNDO_MESSAGE);
    }

    @Override
    public CommandResult execute(LogicTaskList taskList) throws CommandException {
        requireNonNull(taskList);
        taskList.get(targetIndex.getZeroBased()).removeTag(deletedTag);
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, deletedTag, targetIndex));
    }
}
