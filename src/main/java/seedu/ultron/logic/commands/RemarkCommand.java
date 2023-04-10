package seedu.ultron.logic.commands;

import static seedu.ultron.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.ultron.model.Model.PREDICATE_SHOW_ALL_OPENINGS;

import java.util.List;

import seedu.ultron.commons.core.Messages;
import seedu.ultron.commons.core.index.Index;
import seedu.ultron.logic.commands.exceptions.CommandException;
import seedu.ultron.model.Model;
import seedu.ultron.model.opening.Opening;
import seedu.ultron.model.opening.Remark;

/**
 * Changes the remark of an existing opening in the address book.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the remark of the opening identified "
            + "by the index number used in the last opening listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "r/REMARK\n"
            + "Example: " + COMMAND_WORD + " 1 "

            + "r/Need to prepare for Interview.";


    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";
    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Opening: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Opening: %1$s";


    private final Index index;
    private final Remark remark;

    /**
     * @param index of the opening in the filtered opening list to edit the remark
     * @param remark of the opening to be updated to
     */
    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Opening> lastShownList = model.getFilteredOpeningList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_OPENING_DISPLAYED_INDEX);
        }

        Opening openingToEdit = lastShownList.get(index.getZeroBased());
        Opening editedOpening = new Opening(
                openingToEdit.getPosition(), openingToEdit.getCompany(), openingToEdit.getEmail(),
                openingToEdit.getStatus(), remark, openingToEdit.getKeydates());

        model.setOpening(openingToEdit, editedOpening);
        model.updateFilteredOpeningList(PREDICATE_SHOW_ALL_OPENINGS);
        model.setSelectedIndex(null);
        return new CommandResult(generateSuccessMessage(editedOpening));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code openingToEdit}.
     */
    private String generateSuccessMessage(Opening openingToEdit) {
        String message = !remark.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, openingToEdit);
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
        RemarkCommand e = (RemarkCommand) other;
        return index.equals(e.index)
                && remark.equals(e.remark);
    }
}
