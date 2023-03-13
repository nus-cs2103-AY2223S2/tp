package mycelium.mycelium.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.logic.parser.CliSyntax;
import mycelium.mycelium.model.Model;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.person.Email;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteClientCommand extends Command {

    public static final String COMMAND_ACRONYM = "dc";

    public static final String MESSAGE_USAGE = COMMAND_ACRONYM
        + ": Deletes the client with the given email address.\n"
        + "Parameters: EMAIL \n"
        + "Example: " + COMMAND_ACRONYM + " " + CliSyntax.PREFIX_EMAIL + " alice_baker@bakers.com";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Client: %1$s";

    private final Email targetEmail;

    public DeleteClientCommand(Email targetEmail) {
        this.targetEmail = targetEmail;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client>
            listWithTargetClient =
            model.getFilteredClientList().filtered(c -> c.getEmail().equals(targetEmail));

        if (listWithTargetClient.size() == 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT);
        }

        Client clientToDelete = listWithTargetClient.get(0);
        model.deleteClient(clientToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, clientToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteClientCommand // instanceof handles nulls
            && targetEmail.equals(((DeleteClientCommand) other).targetEmail)); // state check
    }
}
