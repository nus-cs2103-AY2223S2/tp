package vimification.internal.command.logic;

import static java.util.Objects.requireNonNull;

import java.util.List;

import vimification.internal.command.CommandResult;
import vimification.model.CommandStack;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

public class DeleteAllCommand extends UndoableLogicCommand {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes all tasks in the displayed task list.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SUCCESS_MESSAGE_FORMAT = "Deleted all task: %1$s";
    public static final String UNDO_MESSAGE =
            "The command has been undone. The tasks has been added back.";

    public List<Task> deletedTasks;

    public DeleteAllCommand() {
        deletedTasks = null;
    }

    @Override
    public CommandResult execute(LogicTaskList taskList, CommandStack commandStack) {
        requireNonNull(taskList);
        deletedTasks = taskList.getInternalList();
        taskList.clear();
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT));
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) {
        requireNonNull(taskList);
        taskList.addAll(deletedTasks);
        return new CommandResult(String.format(UNDO_MESSAGE));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof DeleteTaskCommand;
    }

}


