package vimification.internal.command.logic;

import java.util.Objects;

import vimification.common.core.Index;
import vimification.internal.command.CommandResult;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

/**
 * Deletes some fields from a task identified using display index.
 */
public class DeleteFieldsCommand extends DeleteCommand {

    public static final String COMMAND_KEYWORD = "d";
    public static final String SUCCESS_MESSAGE_FORMAT = "Field(s) of task %d have been deleted.";
    public static final String UNDO_MESSAGE =
            "The command has been undone. Deleted field(s) have been restored.";

    private final Index targetIndex;
    private final DeleteFieldsRequest request;

    private Task oldTask = null;
    private int actualIndex = 0;

    /**
     * Creates a new {@code DeleteFieldsCommand} instance.
     *
     * @param targetIndex index of the task to be modified
     * @param request a structure that contains the relevant information for this command
     */
    public DeleteFieldsCommand(Index targetIndex, DeleteFieldsRequest request) {
        this.targetIndex = targetIndex;
        this.request = request;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList, CommandStack commandStack) {
        actualIndex = taskList.getLogicSourceIndex(targetIndex.getZeroBased());
        Task oldTask = taskList.get(actualIndex);
        Task newTask = oldTask.clone();
        this.oldTask = oldTask;
        if (request.shouldDeleteDeadline()) {
            newTask.deleteDeadline();
        }
        request.getDeletedLabels().forEach(newTask::removeLabel);
        taskList.set(actualIndex, newTask);
        commandStack.push(this);
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, targetIndex.getOneBased()));
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) {
        taskList.set(actualIndex, oldTask);
        return new CommandResult(UNDO_MESSAGE);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeleteFieldsCommand)) {
            return false;
        }
        DeleteFieldsCommand otherCommand = (DeleteFieldsCommand) other;
        return actualIndex == otherCommand.actualIndex
                && Objects.equals(targetIndex, otherCommand.targetIndex)
                && Objects.equals(request, otherCommand.request)
                && Objects.equals(oldTask, otherCommand.oldTask);
    }
}
