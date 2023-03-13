package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "New here? "
            + "Type 'help' into the command for more instructions.";

    public static final String HELP_CATEGORIES = "Help categories available: \n"
            + "help tutorial\n"
            + "help lab\n"
            + "help consultation";
    
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(HELP_CATEGORIES);
    }
}
