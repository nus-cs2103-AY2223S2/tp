package arb.logic.commands.client;

import static arb.model.Model.CLIENT_NO_COMPARATOR;
import static arb.model.Model.PREDICATE_SHOW_ALL_CLIENTS;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.model.ListType;
import arb.model.Model;

/**
 * Lists all clients in the address book to the user.
 */
public class ListClientCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Listed all clients";

    private static final String MAIN_COMMAND_WORD = "list-client";
    private static final String ALIAS_COMMAND_WORD = "lc";
    private static final Set<String> COMMAND_WORDS =
            new HashSet<>(Arrays.asList(MAIN_COMMAND_WORD, ALIAS_COMMAND_WORD));

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) {
        requireNonNull(model);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        model.updateSortedClientList(CLIENT_NO_COMPARATOR);
        return new CommandResult(MESSAGE_SUCCESS, ListType.CLIENT);
    }

    public static boolean isCommandWord(String commandWord) {
        return COMMAND_WORDS.contains(commandWord);
    }

    public static List<String> getCommandWords() {
        return new ArrayList<>(COMMAND_WORDS);
    }
}
