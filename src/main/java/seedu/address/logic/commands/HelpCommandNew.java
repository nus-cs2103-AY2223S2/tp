package seedu.address.logic.commands;

import seedu.address.model.ModelNew;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommandNew extends CommandNew {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResultNew execute(ModelNew model) {
        return new CommandResultNew(SHOWING_HELP_MESSAGE, true, false);
    }
}
