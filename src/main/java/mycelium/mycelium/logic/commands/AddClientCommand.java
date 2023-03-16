package mycelium.mycelium.logic.commands;


import static java.util.Objects.requireNonNull;

import java.util.Objects;

import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.logic.parser.CliSyntax;
import mycelium.mycelium.model.Model;
import mycelium.mycelium.model.client.Client;

/**
 * Adds a new client to Mycelium.
 */
public class AddClientCommand extends Command {

    public static final String COMMAND_ACRONYM = "c";
    public static final String MESSAGE_USAGE =
        COMMAND_ACRONYM + ": Adds a client to Mycelium.\n"

            + "Compulsory Arguments: "
            + CliSyntax.PREFIX_CLIENT_NAME + "CLIENT NAME "
            + CliSyntax.PREFIX_CLIENT_EMAIL + "CLIENT EMAIL\n"
            + "Optional Arguments: "
            + CliSyntax.PREFIX_SOURCE + "CLIENT SOURCE "
            + CliSyntax.PREFIX_CLIENT_MOBILE_NUMBER + "MOBILE NUMBER "
            + CliSyntax.PREFIX_CLIENT_YEAR_OF_BIRTH + "YEAR OF BIRTH\n"

            + "Example: " + COMMAND_ACRONYM + " "
            + CliSyntax.PREFIX_CLIENT_NAME + "Alice Baker "
            + CliSyntax.PREFIX_CLIENT_EMAIL + "alice_baker@bakers.com "
            + CliSyntax.PREFIX_SOURCE + "Fiverr "
            + CliSyntax.PREFIX_CLIENT_MOBILE_NUMBER + "98765432 "
            + CliSyntax.PREFIX_CLIENT_YEAR_OF_BIRTH + "2000";


    public static final String MESSAGE_SUCCESS = "New client added: %1$s";

    public static final String MESSAGE_DUPLICATE_CLIENT_NAME = "This client already exists in your client list";

    private final Client toAdd;

    /**
     * Creates a command which adds the new client.
     *
     * @param client The new client.
     */
    public AddClientCommand(Client client) {
        requireNonNull(client);
        toAdd = client;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasClient(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT_NAME);
        }

        model.addClient(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddClientCommand that = (AddClientCommand) o;
        return toAdd.isSame(that.toAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toAdd);
    }
}
