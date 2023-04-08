package seedu.task.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.task.commons.core.index.IndexList;
import seedu.task.logic.commands.exceptions.CommandException;
import seedu.task.model.Model;
import seedu.task.model.task.Task;

/**
 * Deletes a task identified using displayed index from the task book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task(s):";

    private final IndexList targetList;

    /**
     * Creates a new {@code DeleteCommand} with indices user input.
     */
    public DeleteCommand(IndexList targetList) {
        this.targetList = targetList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        targetList.isValidIndex(lastShownList.size());
        targetList.modifyForDelete();
        String output = MESSAGE_DELETE_TASK_SUCCESS;
        int n = targetList.size();

        for (int i = 0; i < n; i++) {
            Task taskToDelete = lastShownList.get(targetList.getZeroBasedIndex(i));
            model.deleteTask(taskToDelete);
            output = output + "\n" + taskToDelete;
        }

        return new CommandResult(output);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetList.equals(((DeleteCommand) other).targetList)); // state check
    }
}
