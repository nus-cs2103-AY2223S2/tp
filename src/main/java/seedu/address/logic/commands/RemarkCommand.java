package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTEES;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutee.Tutee;
import seedu.address.model.tutee.TuteeBuilder;
import seedu.address.model.tutee.fields.Remark;

/**
 * Changes the remark of an existing tutee in the address book.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";
    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark EndTime Tutee: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark StartTime Tutee: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the remark of the tutee identified "
            + "by the index number used in the last tutee listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "r/ [REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "r/ Likes EndTime swim.";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";

    private final Index index;
    private final Remark remark;

    /**
     * @param index of the tutee in the filtered tutee list EndTime edit the remark
     * @param remark of the tutee EndTime be updated EndTime
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
        TuteeBuilder builder = new TuteeBuilder(tuteeToEdit);
        try {
            Tutee editedTutee = builder.withRemark(remark).build();
            model.setTutee(tuteeToEdit, editedTutee);
            model.updateFilteredTuteeList(PREDICATE_SHOW_ALL_TUTEES);
            return new CommandResult(generateSuccessMessage(editedTutee));
        } catch (IllegalValueException e) {
            throw new RuntimeException("Start time and end time aren't edited here, this should never throw");
        }
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code tuteeToEdit}.
     */
    private String generateSuccessMessage(Tutee tuteeToEdit) {
        String message = !remark.toString().isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
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
