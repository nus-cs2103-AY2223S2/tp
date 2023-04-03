package arb.logic.commands.client;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import arb.commons.core.Messages;
import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.logic.commands.exceptions.CommandException;
import arb.model.ListType;
import arb.model.Model;

/**
 * Clears the client list of the address book.
 */
public class ClearClientCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Client list has been cleared!";

    private static final String MAIN_COMMAND_WORD = "clear-client";
    private static final String ALIAS_COMMAND_WORD = "cc";
    private static final Set<String> COMMAND_WORDS =
            new HashSet<>(Arrays.asList(MAIN_COMMAND_WORD, ALIAS_COMMAND_WORD));

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) throws CommandException {
        requireNonNull(model);

        if (currentListBeingShown != ListType.CLIENT) {
            throw new CommandException(Messages.MESSAGE_INVALID_LIST_CLIENT);
        }

        model.resetClientList();
        return new CommandResult(MESSAGE_SUCCESS, ListType.CLIENT);
    }

    /** Get all valid command words as an unmodifiable set. */
    public static Set<String> getCommandWords() {
        return Collections.unmodifiableSet(COMMAND_WORDS);
    }
}
