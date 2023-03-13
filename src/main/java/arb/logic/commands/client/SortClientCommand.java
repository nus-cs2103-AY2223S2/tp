package arb.logic.commands.client;

import static arb.model.Model.CLIENT_NAME_COMPARATOR;
import static java.util.Objects.requireNonNull;

import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.model.ListType;
import arb.model.Model;

/**
 * Sorts all clients in the address book by name.
 */
public class SortClientCommand extends Command {

    public static final String COMMAND_WORD = "sort-client";

    public static final String MESSAGE_SUCCESS = "Sorted all clients by name!";

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) {
        requireNonNull(model);
        model.updateSortedClientList(CLIENT_NAME_COMPARATOR);
        return new CommandResult(MESSAGE_SUCCESS, ListType.CLIENT);
    }
}
