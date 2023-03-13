package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.ui.ConfirmationDialog;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the specified application from the list of internships applied.\n"
            + "Deletes the application of internship at the specified INDEX.\n"
            + "The index refers to the index number shown in the displayed internship list.\n"
            + "Parameters: INDEX (must be a positive integer 1, 2, 3, ...)\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_DELETE_APPLICATION_SUCCESS = "Deleted Application: %1$s";
    public static final String MESSAGE_DELETE_APPLICATION_FAILED = "Application: %1$s Not Deleted";
    public static final String MESSAGE_DELETE_EXECUTE_ERROR = "Delete command executed but no result!";
    public static final String MESSAGE_DELETE_CONFIRMATION = "Are you sure you want to delete this: application-%1$s";

    private final Index targetIndex;

    private CommandResult resultMessage;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.resultMessage = new CommandResult(MESSAGE_DELETE_EXECUTE_ERROR);
    }

    public CommandResult getResultString(Model model, boolean confirm, Person internshipToDelete) {
        if (confirm) {
            model.deleteInternship(internshipToDelete);
            resultMessage = new CommandResult(String.format(MESSAGE_DELETE_APPLICATION_SUCCESS, internshipToDelete));
        } else {
            resultMessage = new CommandResult(String.format(MESSAGE_DELETE_APPLICATION_FAILED, internshipToDelete));
        }
        return resultMessage;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredInternshipList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        Person internshipToDelete = lastShownList.get(targetIndex.getZeroBased());

        ConfirmationDialog confirmationDialog = new ConfirmationDialog((
                String.format(MESSAGE_DELETE_CONFIRMATION, internshipToDelete.getName())));

        return getResultString(model, confirmationDialog.getConfirmationStatus(), internshipToDelete);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
