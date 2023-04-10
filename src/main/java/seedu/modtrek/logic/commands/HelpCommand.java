package seedu.modtrek.logic.commands;

import seedu.modtrek.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the usage of command(s).\n\n"
            + "Parameters: (COMMAND)\n\n"
            + "Example 1: " + COMMAND_WORD
            + "\nExample 2: " + COMMAND_WORD + " add";

    public static final String SHOWING_HELP_MESSAGE = "Here is a list of all our help commands. "
            + "If you require more information, type in the command to find out more.\n";

    public static final String SHOWING_ALL_MESSAGE_USAGE = SHOWING_HELP_MESSAGE
            + "\n" + COMMAND_WORD + " " + AddCommand.COMMAND_WORD
            + "\n" + COMMAND_WORD + " " + EditCommand.COMMAND_WORD
            + "\n" + COMMAND_WORD + " " + DeleteCommand.COMMAND_WORD
            + "\n" + COMMAND_WORD + " " + TagCommand.COMMAND_WORD
            + "\n" + COMMAND_WORD + " " + ViewCommand.COMMAND_WORD
            + "\n" + COMMAND_WORD + " " + FindCommand.COMMAND_WORD
            + "\n" + COMMAND_WORD + " " + SortCommand.COMMAND_WORD
            + "\n" + COMMAND_WORD + " " + ExitCommand.COMMAND_WORD;

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
        return new CommandResult(selectedMessage, false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpCommand // instanceof handles nulls
                && selectedMessage.equals(((HelpCommand) other).selectedMessage)); // state check
    }
}
