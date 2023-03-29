package vimification.internal.command.logic;

import vimification.commons.core.Index;
import vimification.internal.command.CommandResult;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

import static java.util.Objects.requireNonNull;

public class EditCommand extends UndoableLogicCommand {

    public static final String COMMAND_WORD = "e -d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edit the deadline of a task.\n"
            + "Parameters: INDEX (index number of the target task in the displayed task list)\n"
            + "          : DEADLINE\n"
            + "Conditions: Index must be positive integer and cannot exceed total number of tasks.\n"
            + "          : Date time must be valid in the format of YYYY-MM-DD.\n"
            + "Example: " + COMMAND_WORD + " 1" + " 2023-01-01";

    public static final String SUCCESS_MESSAGE_FORMAT = "Field(s) of task %1$s updated.";
    public static final String UNDO_MESSAGE =
            "The command has been undone. Field(s) of the updated task has been restored.";

    private final Index targetIndex;
    private final EditRequest request;
    private Task oldTask = null;

    public EditCommand(Index targetIndex, EditRequest request) {
        this.targetIndex = targetIndex;
        this.request = request;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList, CommandStack commandStack) {
        requireNonNull(taskList);
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
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, targetIndex.getOneBased()));
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) {
        taskList.set(targetIndex.getZeroBased(), oldTask);
        return null;
    }
}
