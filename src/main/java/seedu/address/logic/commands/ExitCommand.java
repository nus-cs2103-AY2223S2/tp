package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.ui.ConfirmationDialog;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String EXIT_CONFIRMATION = "Are you sure you want to exit InternEase?";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting InternEase as requested ...";

    public static final String MESSAGE_STAY_ACKNOWLEDGEMENT = "So glad that you're still here!";

    private CommandResult resultMessage;

    /**
     * Returns result message with respect to the user's action to {@code confirm}.
     */
    public CommandResult getResultString(boolean confirm) {
        if (confirm) {
            resultMessage = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        } else {
            resultMessage = new CommandResult(MESSAGE_STAY_ACKNOWLEDGEMENT);
        }
        return resultMessage;
    }

    @Override
    public CommandResult execute(Model model) {
        ConfirmationDialog confirmationDialog = new ConfirmationDialog(EXIT_CONFIRMATION);

        return getResultString(confirmationDialog.getConfirmationStatus());
    }

}
