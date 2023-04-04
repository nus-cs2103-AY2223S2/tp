package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.logic.enums.LightDarkMode;
import seedu.address.ui.result.ResultDisplay;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE =
            ResultDisplay.formatMessage(COMMAND_WORD, "Shows usage instructions.")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_EXAMPLE, COMMAND_WORD);

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false, LightDarkMode.NO_CHANGE);
    }
}
