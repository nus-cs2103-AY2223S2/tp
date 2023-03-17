package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Deadline;
import seedu.address.model.module.Module;


/**
 * Changes the deadline of an existing module in the address book.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the deadline of the module identified "
            + "by the index number. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "d/ [DEADLINE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "d/ 24th January Written Assignment 1.";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Deadline: %2$s";

    public static final String MESSAGE_ADD_DEADLINE_SUCCESS = "Added deadline to Module: %1$s";
    public static final String MESSAGE_DELETE_DEADLINE_SUCCESS = "Removed deadline from Module: %1$s";

    private final Index index;
    private final Deadline deadline;



    /**
     * @param index of the module in the filtered module list to edit the deadline
     * @param deadline of the module to be updated to
     */
    public DeadlineCommand(Index index, Deadline deadline) {
        requireAllNonNull(index, deadline);
        this.index = index;
        this.deadline = deadline;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Module> lastShownList = model.getFilteredModuleList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module moduleToEdit = lastShownList.get(index.getZeroBased());
        Module editedModule = new Module(
                moduleToEdit.getName(), moduleToEdit.getType(), moduleToEdit.getTimeSlot(),
                moduleToEdit.getAddress(), moduleToEdit.getTags(), moduleToEdit.getRemark(), deadline,
                moduleToEdit.getTeacher());

        model.setModule(moduleToEdit, editedModule);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);

        return new CommandResult(generateSuccessMessage(editedModule));
    }

    /**
     * Generates a command execution success message based on whether
     * the deadline is added to or removed from
     * {@code moduleToEdit}.
     */
    private String generateSuccessMessage(Module moduleToEdit) {
        String message = !deadline.value.isEmpty() ? MESSAGE_ADD_DEADLINE_SUCCESS : MESSAGE_DELETE_DEADLINE_SUCCESS;
        return String.format(message, moduleToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeadlineCommand)) {
            return false;
        }

        // state check
        DeadlineCommand e = (DeadlineCommand) other;
        return index.equals(e.index)
                && deadline.equals(e.deadline);
    }
}
