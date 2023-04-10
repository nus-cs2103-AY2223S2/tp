package seedu.techtrack.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.techtrack.commons.core.Messages;
import seedu.techtrack.commons.core.index.Index;
import seedu.techtrack.logic.commands.exceptions.CommandException;
import seedu.techtrack.model.Model;
import seedu.techtrack.model.role.Role;

/**
 * Deletes a role identified using it's displayed index from the role book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the role identified by the index number used in the displayed role list.\n \n"
            + "Parameters: INDEX (must be a positive integer)\n \n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Role: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult<?> execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Role> lastShownList = model.getFilteredRoleList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Role roleToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteRole(roleToDelete);
        return new CommandResult<>(String.format(MESSAGE_DELETE_PERSON_SUCCESS, roleToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
