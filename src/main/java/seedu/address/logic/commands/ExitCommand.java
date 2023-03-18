package seedu.address.logic.commands;

import java.util.HashMap;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final HashMap<Prefix, String> COMMAND_PROMPTS = new HashMap<>();

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting FriendlyLink as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
