package seedu.careflow.logic.commands.generalcommand;

import seedu.careflow.logic.commands.Command;
import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.model.CareFlowModel;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: g " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(CareFlowModel model) throws CommandException {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
