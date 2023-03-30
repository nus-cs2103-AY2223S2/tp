package seedu.sprint.logic.commands;

import seedu.sprint.logic.CommandHistory;
import seedu.sprint.model.Model;

/**
 * Provides program usage instructions to user.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = "Format: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened Help window.";

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
