package seedu.address.logic.commands.general;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.ui.ScreenType;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand implements GeneralCommand {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(Model dataModel) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false, ScreenType.EXPENSE_SCREEN);
    }
}
