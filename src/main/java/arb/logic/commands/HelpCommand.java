package arb.logic.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import arb.model.ListType;
import arb.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private static final String MAIN_COMMAND_WORD = "help";
    private static final Set<String> COMMAND_WORDS = new HashSet<>(Arrays.asList(MAIN_COMMAND_WORD));

    public static final String MESSAGE_USAGE = MAIN_COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + MAIN_COMMAND_WORD;

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false, false, ListType.NONE);
    }

    /** Get all valid command words as an unmodifiable set. */
    public static Set<String> getCommandWords() {
        return Collections.unmodifiableSet(COMMAND_WORDS);
    }
}
