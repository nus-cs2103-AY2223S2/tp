package arb.logic.commands.client;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import arb.commons.core.Messages;
import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.model.ListType;
import arb.model.Model;
import arb.model.client.Client;

/**
 * Finds and lists all clients in address book whose name contains any of the argument keywords given
 * and contains any of the tags given. Keyword matching is case insensitive.
 */
public class FindClientCommand extends Command {

    private static final String MAIN_COMMAND_WORD = "find-client";
    private static final String ALIAS_COMMAND_WORD = "fc";
    private static final Set<String> COMMAND_WORDS =
            new HashSet<>(Arrays.asList(MAIN_COMMAND_WORD, ALIAS_COMMAND_WORD));

    public static final String MESSAGE_USAGE = MAIN_COMMAND_WORD + ": Finds all clients whose names contain any of "
            + "the specified keywords (case-insensitive) and contains any of the given tags (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: [name/NAME]* [tag/TAG]*\n"
            + "Example: " + MAIN_COMMAND_WORD + " name/alice name/bob name/charlie tag/friend";

    private final Predicate<Client> predicate;

    public FindClientCommand(Predicate<Client> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) {
        requireNonNull(model);
        model.updateFilteredClientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW, model.getFilteredClientList().size())
                + "\n" + predicate,
                ListType.CLIENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindClientCommand // instanceof handles nulls
                && predicate.equals(((FindClientCommand) other).predicate)); // state check
    }

    /** Get all valid command words as an unmodifiable set. */
    public static Set<String> getCommandWords() {
        return Collections.unmodifiableSet(COMMAND_WORDS);
    }

}
