package arb.logic.commands.client;

import static java.util.Objects.requireNonNull;

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

    public static final String COMMAND_WORD = "clear-client";
    public static final String MESSAGE_SUCCESS = "Client list has been cleared!";


    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) throws CommandException {
        requireNonNull(model);

        if (currentListBeingShown != ListType.CLIENT) {
            throw new CommandException(Messages.MESSAGE_INVALID_LIST_CLIENT);
        }

        model.resetClientList();
        return new CommandResult(MESSAGE_SUCCESS, ListType.CLIENT);
    }
}
