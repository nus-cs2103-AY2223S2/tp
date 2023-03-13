package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.ui.ConfirmationDialog;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_CONFIRMATION = "Are you sure you want to clear all the entries? ";
    public static final String MESSAGE_SUCCESS = "All internship application has been cleared!";
    public static final String MESSAGE_FAILED = "Clear command is not executed!";
    public static final String MESSAGE_NULL = "There is nothing to clear!";

    private CommandResult resultMessage;

    /**
     * Clears the {@code model} data and returns result message with respect to the user's action to {@code confirm}.
     */
    public CommandResult getResultString(Model model, boolean confirm) {

        if (confirm) {
            model.setInternEase(new AddressBook());
            resultMessage = new CommandResult(MESSAGE_SUCCESS);
        } else {
            resultMessage = new CommandResult(MESSAGE_FAILED);
        }
        return resultMessage;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (model.getFilteredInternshipList().size() == 0) {
            return new CommandResult(MESSAGE_NULL);
        }

        ConfirmationDialog confirmationDialog = new ConfirmationDialog(MESSAGE_CONFIRMATION);

        return getResultString(model, confirmationDialog.getConfirmationStatus());
    }
}
