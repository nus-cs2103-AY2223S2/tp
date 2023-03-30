package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.model.ApplicationModel;

/**
 * Provides program usage instructions to user.
 */
public class HelpApplicationCommand extends ApplicationCommand {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = "Format: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened Help window.";

    @Override
    public CommandResult execute(ApplicationModel model, CommandHistory commandHistory) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
