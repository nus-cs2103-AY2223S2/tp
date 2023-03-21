package seedu.address.logic.commands;

import java.util.HashMap;
import java.util.LinkedHashMap;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;

/**
 * Displays the help window.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final HashMap<Prefix, String> COMMAND_PROMPTS = new LinkedHashMap<>();
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
