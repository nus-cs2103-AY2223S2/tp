package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Displays reminder for upcoming interview.
 */
public class RemindCommand extends Command {
    public static final String COMMAND_WORD = "remind";

    public static final String SHOWING_REMINDER_MESSAGE = "Opened reminder window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_REMINDER_MESSAGE, false, false, true);
    }
}
