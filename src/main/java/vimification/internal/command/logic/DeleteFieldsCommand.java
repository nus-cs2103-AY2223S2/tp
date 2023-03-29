package vimification.internal.command.logic;

import vimification.commons.core.Index;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

public class DeleteFieldsCommand extends DeleteCommand {

    private final Index targetIndex;
    private final DeleteFieldsRequest request;
    private Task oldTask = null;

    public DeleteFieldsCommand(Index targetIndex, DeleteFieldsRequest request) {
        this.targetIndex = targetIndex;
        this.request = request;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList) {
        int index = targetIndex.getZeroBased();
        Task oldTask = taskList.get(index);
        Task newTask = oldTask.clone();
        if (request.deadline) {
            // delete the deadline
            newTask.deleteDeadline();
        }
        // delete the label
        request.labels.forEach(newTask::removeLabel);
        taskList.set(index, newTask);
        this.oldTask = oldTask;
        return null;
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) {
        taskList.set(targetIndex.getZeroBased(), oldTask);
        return null;
    }
}
