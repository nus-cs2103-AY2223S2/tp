package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ward.Ward;

/**
 * Deletes a ward identified using it's displayed index from MedInfo.
 */
public class DeleteWardCommand extends Command {

    public static final String COMMAND_WORD = "deleteward";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the ward identified by the index number used in the displayed ward list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_WARD_SUCCESS = "Deleted Ward: %1$s";

    private final Index targetIndex;

    public DeleteWardCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Ward> lastShownList = model.getFilteredWardList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_WARD_DISPLAYED_INDEX);
        }

        if (targetIndex.getZeroBased() == 0) {
            throw new CommandException(Messages.MESSAGE_DELETE_WAITING_ROOM);
        }

        Ward wardToDelete = lastShownList.get(targetIndex.getZeroBased());

        if (wardToDelete.getOccupancy() > 0) {
            throw new CommandException(Messages.MESSAGE_DELETE_WARD_WITH_PATIENTS);
        }


        model.deleteWard(wardToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_WARD_SUCCESS, wardToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteWardCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteWardCommand) other).targetIndex)); // state check
    }
}
