package seedu.address.logic.commands;

import seedu.address.logic.commands.results.HelpCommandResult;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public HelpCommandResult execute(Model model) {
        return new HelpCommandResult(SHOWING_HELP_MESSAGE);
    }
}
