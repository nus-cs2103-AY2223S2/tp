package seedu.medinfo.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.medinfo.commons.core.Messages;
import seedu.medinfo.commons.core.index.Index;
import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.Model;
import seedu.medinfo.model.ward.Ward;

/**
 * Deletes a ward identified using its displayed index from MedInfo.
 */
public class DeleteWardCommand extends Command {

    public static final String COMMAND_WORD = "deleteward";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the ward identified by the index number used in the displayed ward list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_WARD_SUCCESS = "Deleted Ward: %1$s";

    private final Index targetIndex;

    /**
     * Constructs a new {@code DeleteWardCommand} to delete the {@code Ward} at the specified index.
     * @param targetIndex Index of the {@code Ward} to be deleted in the list.
     */
    public DeleteWardCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the {@code DeleteWardCommand} on the given model.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult which is the result of the operation.
     * @throws CommandException
     */
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
