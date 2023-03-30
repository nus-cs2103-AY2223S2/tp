package vimification.internal.command.logic;

import vimification.commons.core.Index;
import vimification.internal.command.CommandResult;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

public class DeleteFieldsCommand extends DeleteCommand {

    public static final String SUCCESS_MESSAGE_FORMAT = "Field(s) of task %1$s deleted.";
    public static final String UNDO_MESSAGE =
            "The command has been undone. Field(s) of task has been restored.";

    private final Index targetIndex;
    private final DeleteFieldsRequest request;
    private Task oldTask = null;

    public DeleteFieldsCommand(Index targetIndex, DeleteFieldsRequest request) {
        this.targetIndex = targetIndex;
        this.request = request;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList, CommandStack commandStack) {
        int index = targetIndex.getZeroBased();
        Task oldTask = taskList.get(index);
        Task newTask = oldTask.clone();
        this.oldTask = oldTask;
        if (request.shouldDeleteDeadline()) {
            newTask.deleteDeadline();
        }
        request.getDeletedLabels().forEach(newTask::removeLabel);
        taskList.set(index, newTask);
        commandStack.push(this);
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, oldTask));
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) {
        taskList.set(targetIndex.getZeroBased(), oldTask);
        return new CommandResult(UNDO_MESSAGE);
    }
}
