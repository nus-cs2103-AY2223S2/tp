package arb.logic.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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

    public static boolean isCommandWord(String commandWord) {
        return COMMAND_WORDS.contains(commandWord);
    }

    public static List<String> getCommandWords() {
        return new ArrayList<>(COMMAND_WORDS);
    }
}
