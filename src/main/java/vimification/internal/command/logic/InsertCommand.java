package vimification.internal.command.logic;

import static java.util.Objects.requireNonNull;

import vimification.commons.core.Index;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

public class InsertCommand extends UndoableLogicCommand {

    public static final String COMMAND_WORD = "i -d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": inserts deadline to the task.\n"
            + "Parameters: INDEX (index number of the target task in the displayed task list)\n"
            + "          : DEADLINE\n"
            + "Conditions: Index must be positive integer and cannot exceed total number of tasks.\n"
            + "          : Date time must be valid in the format of YYYY-MM-DD.\n"
            + "Example: " + COMMAND_WORD + " 1" + "2023-01-01";

    public static final String SUCCESS_MESSAGE_FORMAT =
            "deadline of task %1$s inserted.";
    public static final String UNDO_MESSAGE =
            "The command has been undone. The deadline of the task has been changed back.";

    private final Index targetIndex;
    private final InsertRequest request;
    private Task oldTask = null;

    public InsertCommand(Index targetIndex, InsertRequest request) {
        this.targetIndex = targetIndex;
        this.request = request;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList) {
        requireNonNull(taskList);
        int index = targetIndex.getZeroBased();
        Task oldTask = taskList.get(index);
        Task newTask = oldTask.clone();
        this.oldTask = oldTask;
        if (request.getInsertedDeadline() != null) {
            newTask.setDeadline(request.getInsertedDeadline());
        }
        request.getInsertedLabels().forEach(newTask::addLabel);
        taskList.set(index, newTask);
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT, targetIndex.getOneBased()));
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) {
        requireNonNull(taskList);
        taskList.set(targetIndex.getZeroBased(), oldTask);
        return new CommandResult(UNDO_MESSAGE);
    }
}
