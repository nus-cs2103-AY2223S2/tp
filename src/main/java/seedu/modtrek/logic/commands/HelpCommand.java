package seedu.modtrek.logic.commands;

import seedu.modtrek.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the usage of command(s).\n"
            + "Parameters: COMMAND (Optional)\n"
            + "Example: " + COMMAND_WORD + " add";

    public static final String SHOWING_HELP_MESSAGE = "Here is a summary of all our commands:\n\n";

    public static final String SHOWING_ALL_MESSAGE_USAGE = SHOWING_HELP_MESSAGE + AddCommand.MESSAGE_USAGE
            + "\n\n" + EditCommand.MESSAGE_USAGE
            + "\n\n" + DeleteCommand.MESSAGE_USAGE
            + "\n\n" + TagCommand.MESSAGE_USAGE
            + "\n\n" + ListCommand.MESSAGE_USAGE
            + "\n\n" + FindCommand.MESSAGE_USAGE
            + "\n\n" + ExitCommand.MESSAGE_USAGE;

    private final String selectedMessage;

    /**
     * Instantiates a new Help command.
     *
     * @param message Message usage for the specified command
     */
    public HelpCommand(String message) {
        this.selectedMessage = message;
    }

    @Override
    public CommandResult execute(Model model) {
        if (selectedMessage.isEmpty()) {
            return new CommandResult(SHOWING_ALL_MESSAGE_USAGE);
        }
        return new CommandResult(selectedMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpCommand // instanceof handles nulls
                && selectedMessage.equals(((HelpCommand) other).selectedMessage)); // state check
    }
}
