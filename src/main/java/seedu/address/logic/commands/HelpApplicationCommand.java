package seedu.address.logic.commands;

import seedu.address.model.ApplicationModel;

/**
 * Provides program usage instructions to user.
 */
public class HelpApplicationCommand extends ApplicationCommand {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(ApplicationModel model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
