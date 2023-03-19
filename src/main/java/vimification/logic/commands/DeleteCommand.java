package vimification.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import vimification.commons.core.Messages;
import vimification.commons.core.index.Index;
import vimification.logic.commands.exceptions.CommandException;
import vimification.model.Model;
import vimification.model.task.Task;

/**
 * Deletes a task identified using it's displayed index from the address book.
 */
public class DeleteCommand extends LogicCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";
    public static final String UNDO_MESSAGE =
            "The command has been undoed. The deleted task has been added back.";

    private final Index targetIndex;
    private Task taskToDelete;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.taskToDelete = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        checkBeforeExecuting();
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        taskToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTask(taskToDelete);
        setUndoable(true);
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        requireNonNull(model);
        checkBeforeUndoing();

        if (taskToDelete == null) { // This guard clause might be useless
            throw new CommandException(NOT_UNDOABLE_MESSAGE);
        }

        model.addTask(taskToDelete);
        setUndoable(true);
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                        && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
