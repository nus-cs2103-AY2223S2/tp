package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTEES;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutee.Tutee;
import seedu.address.model.tutee.Remark;

import java.util.List;


/**
 * Changes the remark of an existing tutee in the address book.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";
    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Tutee: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Tutee: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the remark of the tutee identified "
            + "by the index number used in the last tutee listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "r/ [REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "r/ Likes to swim.";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";

    private final Index index;
    private final Remark remark;

    /**
     * @param index of the tutee in the filtered tutee list to edit the remark
     * @param remark of the tutee to be updated to
     */
    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Tutee> lastShownList = model.getFilteredTuteeList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Tutee tuteeToEdit = lastShownList.get(index.getZeroBased());
        Tutee editedTutee = new Tutee(
                        tuteeToEdit.getName(), tuteeToEdit.getPhone(), tuteeToEdit.getEmail(),
                        tuteeToEdit.getAddress(), remark, tuteeToEdit.getSubject(),
                tuteeToEdit.getSchedule(), tuteeToEdit.getTags());

        model.setTutee(tuteeToEdit, editedTutee);
        model.updateFilteredTuteeList(PREDICATE_SHOW_ALL_TUTEES);

        return new CommandResult(generateSuccessMessage(editedTutee));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code tuteeToEdit}.
     */
    private String generateSuccessMessage(Tutee tuteeToEdit) {
        String message = !remark.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, tuteeToEdit);
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
