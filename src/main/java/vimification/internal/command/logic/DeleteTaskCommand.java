package vimification.internal.command.logic;

import java.util.Objects;

import vimification.commons.core.Index;
import vimification.internal.command.CommandResult;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

/**
 * Deletes a task identified using its displayed index.
 */
public class DeleteTaskCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "d";
    public static final String SUCCESS_MESSAGE_FORMAT = "Task %s has been deleted.";
    public static final String UNDO_MESSAGE =
            "The command has been undone. The deleted task has been added back.";

    private final Index targetIndex;
    private Task deletedTask = null;

    public DeleteTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList, CommandStack commandStack) {
        deletedTask = taskList.remove(targetIndex.getZeroBased());
        commandStack.push(this);
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, deletedTask.forDisplay()));
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) {
        taskList.add(targetIndex.getZeroBased(), deletedTask);
        return new CommandResult(UNDO_MESSAGE);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeleteCommand)) {
            return false;
        }
        DeleteTaskCommand otherCommand = (DeleteTaskCommand) other;
        return Objects.equals(targetIndex, otherCommand.targetIndex)
                && Objects.equals(deletedTask, otherCommand.deletedTask);
    }
}
