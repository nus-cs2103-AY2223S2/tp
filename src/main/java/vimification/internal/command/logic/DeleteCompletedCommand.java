package vimification.internal.command.logic;

import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class DeleteCompletedCommand extends UndoableLogicCommand{

    public static final String COMMAND_WORD = "d a";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes all completed tasks in the displayed task list.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SUCCESS_MESSAGE_FORMAT = "Deleted all completed task: %1$s";
    public static final String UNDO_MESSAGE =
            "The command has been undone. The deleted tasks has been added back.";

    public List<Task> deletedTasks;

    public DeleteCompletedCommand() {
        deletedTasks = null;
    };


    @Override
    public CommandResult execute(LogicTaskList taskList)
            throws IndexOutOfBoundsException, CommandException {
        requireNonNull(taskList);
        deletedTasks = taskList.removeAllDone();
        return new CommandResult(String.format(SUCCESS_MESSAGE_FORMAT));
    }

    @Override
    public CommandResult undo(LogicTaskList taskList) throws CommandException {
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


