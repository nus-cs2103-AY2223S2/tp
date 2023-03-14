package arb.logic.commands.client;

import static arb.logic.parser.CliSyntax.PREFIX_EMAIL;
import static arb.logic.parser.CliSyntax.PREFIX_NAME;
import static arb.logic.parser.CliSyntax.PREFIX_PHONE;
import static arb.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.logic.commands.exceptions.CommandException;
import arb.model.ListType;
import arb.model.Model;
import arb.model.client.Client;

/**
 * Adds a client to the address book.
 */
public class AddClientCommand extends Command {

    public static final String COMMAND_WORD = "add-client";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a client to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New client added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This client already exists in the address book";

    private final Client toAdd;

    /**
     * Creates an AddClientCommand to add the specified {@code Client}
     */
    public AddClientCommand(Client client) {
        requireNonNull(client);
        toAdd = client;
    }

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) throws CommandException {
        requireNonNull(model);

        if (model.hasClient(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        model.addClient(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), ListType.CLIENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddClientCommand // instanceof handles nulls
                && toAdd.equals(((AddClientCommand) other).toAdd));
    }
}
