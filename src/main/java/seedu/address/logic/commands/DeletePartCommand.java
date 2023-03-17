package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a vehicle identified using it's displayed index from viewpart.
 */
public class DeletePartCommand extends RedoableCommand {

    public static final String COMMAND_WORD = "deletepart";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the part identified by the id number displayed by the list command.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PART_SUCCESS = "Deleted Part: %1$s";

    private final Index targetIndex;

    public DeletePartCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult executeUndoableCommand(Model model) throws CommandException {
        //        requireNonNull(model);
        //        List<Part> lastShownList = model.getFilteredPart();
        //
        //        Part partToDelete = lastShownList.get(targetIndex.getZeroBased());
        //        model.deletePart(partToDelete);
        //        return new CommandResult(String.format(MESSAGE_DELETE_PART_SUCCESS, partToDelete));
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePartCommand // instanceof handles nulls
                && targetIndex.equals(((DeletePartCommand) other).targetIndex)); // state check
    }
}
