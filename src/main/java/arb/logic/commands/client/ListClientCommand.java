package arb.logic.commands.client;

import static arb.model.Model.PREDICATE_SHOW_ALL_CLIENTS;
import static java.util.Objects.requireNonNull;

import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.model.ListType;
import arb.model.Model;

/**
 * Lists all clients in the address book to the user.
 */
public class ListClientCommand extends Command {

    public static final String COMMAND_WORD = "list-client";

    public static final String MESSAGE_SUCCESS = "Listed all clients";

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) {
        requireNonNull(model);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        return new CommandResult(MESSAGE_SUCCESS, ListType.CLIENT);
    }
}
