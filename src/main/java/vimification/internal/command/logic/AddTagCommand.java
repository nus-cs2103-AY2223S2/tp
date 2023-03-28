package vimification.internal.command.logic;

import static java.util.Objects.requireNonNull;
import static vimification.commons.util.CollectionUtil.requireAllNonNull;

import vimification.commons.core.Index;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;

public class AddTagCommand extends UndoableLogicCommand {
    public static final String COMMAND_WORD = "add_tag";
    public static final String SUCCESS_MESSAGE_FORMAT =
            "Task %2$d has been added with the %1$s tag.";
    public static final String UNDO_MESSAGE =
            "The command has been undoed. The new tag has been removed.";

    private final String newTag;
    private final Index targetIndex;

    /**
     * Creates an AddTagCommand to add the specified {@code Task}
     */
    public AddTagCommand(String tag, Index targetIndex) {
        requireAllNonNull(tag, targetIndex);
        this.newTag = tag;
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) throws CommandException {
        requireNonNull(taskList);
        taskList.get(targetIndex.getZeroBased()).removeTag(newTag);
        return new CommandResult(UNDO_MESSAGE);
    }

    @Override
    public CommandResult execute(LogicTaskList taskList) throws CommandException {
        requireNonNull(taskList);
        taskList.get(targetIndex.getZeroBased()).addTag(newTag);
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, newTag, targetIndex));
    }
}
