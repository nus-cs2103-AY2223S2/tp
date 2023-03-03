package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CareFlowModel;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

//    @Override
//    public CommandResult execute(Model model) {
//        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
//    }

    @Override
    public CommandResult execute(CareFlowModel model) throws CommandException {
        return null;
    }
}
