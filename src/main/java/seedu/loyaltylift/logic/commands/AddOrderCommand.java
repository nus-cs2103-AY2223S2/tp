package seedu.loyaltylift.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.loyaltylift.logic.commands.exceptions.CommandException;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.order.Order;

/**
 * Adds a customer to the address book.
 */
public class AddOrderCommand extends Command {

    public static final String COMMAND_WORD = "addo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a customer to the address book. \n"
            + "Parameters: "
            + "1"
            + PREFIX_NAME + "ORDER_NAME "
            + "[" + PREFIX_QUANTITY + "QUANTITY] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_STATUS + "STATUS] ";

    public static final String MESSAGE_SUCCESS = "New order added: \n%1$s";

    private final Order toAdd;

    /**
     * Creates an AddCustomerCommand to add the specified {@code Customer}
     */
    public AddOrderCommand(Order order) {
        requireNonNull(order);
        toAdd = order;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addOrder(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddOrderCommand // instanceof handles nulls
                && toAdd.equals(((AddOrderCommand) other).toAdd));
    }
}

