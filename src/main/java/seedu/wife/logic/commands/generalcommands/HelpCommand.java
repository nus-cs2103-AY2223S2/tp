package seedu.wife.logic.commands.generalcommands;

import seedu.wife.logic.commands.Command;
import seedu.wife.logic.commands.CommandResult;
import seedu.wife.model.Model;

/**
 * Format full help instructions for every command for display.
 *
 * Dynamic help functionality inspired by AY2223 S1 Team W16-2
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private final String helpMessage;

    /**
     * Creates a new HelpCommand object
     *
     * @param helpMessage String to be passed to the MainWindow
     *      via CommandResult which will be displayed on help window.
     */
    public HelpCommand(String helpMessage) {
        this.helpMessage = helpMessage;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, helpMessage, true, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HelpCommand)) {
            return false;
        }

        HelpCommand otherHelpCommand = (HelpCommand) other;
        return helpMessage.equals(otherHelpCommand.helpMessage);
    }
}
