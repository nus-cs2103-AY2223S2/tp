package vimification.internal.command.logic;

import java.util.Objects;

import vimification.common.core.Index;
import vimification.internal.command.CommandResult;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

/**
 * Insert new field(s) into a task identified using its display index.
 */
public class InsertCommand extends UndoableLogicCommand {

    public static final String COMMAND_WORD = "i";
    public static final String SUCCESS_MESSAGE_FORMAT =
            "New field(s) have been inserted into task %d.";
    public static final String UNDO_MESSAGE =
            "The command has been undone. Inserted field(s) have been discarded.";

    private final Index targetIndex;
    private final InsertRequest request;

    private Task oldTask = null;
    private int actualIndex = 0;

    /**
     * Creates a new {@code InsertCommand} instance.
     *
     * @param targetIndex index of the task to be modified
     * @param request a structure that contains the relevant information for this command
     */
    public InsertCommand(Index targetIndex, InsertRequest request) {
        this.targetIndex = targetIndex;
        this.request = request;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList, CommandStack commandStack) {
        actualIndex = targetIndex.getZeroBased();
        Task oldTask = taskList.get(actualIndex);
        Task newTask = oldTask.clone();
        this.oldTask = oldTask;
        if (request.getInsertedDeadline() != null) {
            newTask.setDeadline(request.getInsertedDeadline());
        }
        request.getInsertedLabels().forEach(newTask::addLabel);
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
        if (!(other instanceof InsertCommand)) {
            return false;
        }
        InsertCommand otherCommand = (InsertCommand) other;
        return actualIndex == otherCommand.actualIndex
                && Objects.equals(targetIndex, otherCommand.targetIndex)
                && Objects.equals(request, otherCommand.request)
                && Objects.equals(oldTask, otherCommand.oldTask);
    }
}
