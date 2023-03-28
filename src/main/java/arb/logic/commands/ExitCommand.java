package arb.logic.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import arb.model.ListType;
import arb.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    private static final String MAIN_COMMAND_WORD = "exit";
    private static final Set<String> COMMAND_WORDS = new HashSet<>(Arrays.asList(MAIN_COMMAND_WORD));

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false, ListType.NONE);
    }

    public static boolean isCommandWord(String commandWord) {
        return COMMAND_WORDS.contains(commandWord);
    }

    public static List<String> getCommandWords() {
        return new ArrayList<>(COMMAND_WORDS);
    }
}
