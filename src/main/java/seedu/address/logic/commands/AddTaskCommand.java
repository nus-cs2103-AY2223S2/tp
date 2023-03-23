package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_TASK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Task;

/**
 * Changes the task of an existing person in the address book.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "addtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the task of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing task will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_ADD_TASK + "[TASK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ADD_TASK + "Time to swim.";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "AddTask command not implemented yet";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Task: %2$s";

    private final Index index;
    private final Task task;

    /**
     * @param index of the person in the filtered person list to edit the task
     * @param task of the person to be updated to
     */
    public AddTaskCommand(Index index, Task task) {
        requireAllNonNull(index, task);

        this.index = index;
        this.task = task;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, index.getOneBased(), task));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        // state check
        AddTaskCommand e = (AddTaskCommand) other;
        return index.equals(e.index)
                && task.equals(e.task);
    }
}