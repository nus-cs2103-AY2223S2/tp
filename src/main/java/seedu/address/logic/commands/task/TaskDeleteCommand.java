package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Deletes a {@code Task} identified using it's displayed index from the {@code TaskList}.
 */
public class TaskDeleteCommand extends TaskCommand {

    public static final String TASK_COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + TASK_COMMAND_WORD
            + ": Deletes the Task identified by the index number used in the displayed Task List.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + TASK_COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";

    private final Index targetIndex;

    /**
     * Constructs an {@code TaskDeleteCommand} to delete an existing {@code Task}.
     *
     * @param targetIndex The index of the {@code Task} to delete.
     */
    public TaskDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_TASK_INDEX_OUTOFBOUNDS);
        }

        Task taskToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTask(taskToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((TaskDeleteCommand) other).targetIndex)); // state check
    }
}

