package vimification.internal.command.logic;

import java.util.Objects;

import vimification.commons.core.Index;
import vimification.internal.command.CommandResult;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

/**
 * Edits a task identified using its display index.
 */
public class EditCommand extends UndoableLogicCommand {

    public static final String COMMAND_WORD = "e";
    public static final String SUCCESS_MESSAGE_FORMAT = "Field(s) of task %s have been updated.";
    public static final String UNDO_MESSAGE =
            "The command has been undone. Edited field(s) have been restored.";

    private final Index targetIndex;
    private final EditRequest request;
    private Task oldTask = null;

    public EditCommand(Index targetIndex, EditRequest request) {
        this.targetIndex = targetIndex;
        this.request = request;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList, CommandStack commandStack) {
        int index = targetIndex.getZeroBased();
        Task oldTask = taskList.get(index);
        Task newTask = oldTask.clone();
        this.oldTask = oldTask;
        if (request.getEditedTitle() != null) {
            newTask.setTitle(request.getEditedTitle());
        }
        if (request.getEditedDeadline() != null) {
            newTask.setDeadline(request.getEditedDeadline());
        }
        if (request.getEditedPriority() != null) {
            newTask.setPriority(request.getEditedPriority());
        }
        if (request.getEditedStatus() != null) {
            newTask.setStatus(request.getEditedStatus());
        }
        request.getEditedLabels().forEach(pair -> {
            newTask.removeLabel(pair.getFirst());
            newTask.addLabel(pair.getSecond());
        });
        taskList.set(index, newTask);
        commandStack.push(this);
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, oldTask));
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) {
        taskList.set(targetIndex.getZeroBased(), oldTask);
        return new CommandResult(UNDO_MESSAGE);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EditCommand)) {
            return false;
        }
        EditCommand otherCommand = (EditCommand) other;
        return Objects.equals(targetIndex, otherCommand.targetIndex)
                && Objects.equals(request, otherCommand.request)
                && Objects.equals(oldTask, otherCommand.oldTask);
    }
}
