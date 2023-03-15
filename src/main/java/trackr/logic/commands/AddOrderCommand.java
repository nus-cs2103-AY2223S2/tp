package trackr.logic.commands;

import static java.util.Objects.requireNonNull;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERNAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERQUANTITY;
import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_PHONE;
import static trackr.logic.parser.CliSyntax.PREFIX_ADDRESS;

import trackr.logic.commands.exceptions.CommandException;
import trackr.model.Model;
import trackr.model.order.Order;

/**
 * Adds an order to the order list
 */
public class AddOrderCommand extends Command {
    public static final String COMMAND_WORD = "add_order";
    public static final String COMMAND_WORD_SHORTCUT = "add_o";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task list. "
            + "Parameters: "
            + PREFIX_ORDERNAME + "ORDER NAME "
            + PREFIX_ORDERQUANTITY + "ORDER QUANTITY"
            + PREFIX_DEADLINE + "DEADLINE "
            + "[" + PREFIX_STATUS + "STATUS]"
            + PREFIX_NAME + "CUSTOMER'S NAME"
            + PREFIX_PHONE + "CUSTOMER'S PHONE NUMBER"
            + PREFIX_ADDRESS + "CUSTOMER'S ADDRESS\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ORDERNAME + "CHOCOLATE COOKIES "
            + PREFIX_ORDERQUANTITY + "5"
            + PREFIX_DEADLINE + "01/01/2024 "
            + PREFIX_STATUS + "N "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "12345678 "
            + PREFIX_ADDRESS + "123 Smith Street ";

    public static final String MESSAGE_SUCCESS = "New order added: %1$s";
    public static final String MESSAGE_DUPLICATE_ORDER = "This order already exists in the task list";

    private final Order toAdd;

    /**
     * Creates an AddOrderCommand to add the specified {@code Order}
     */
    public AddOrderCommand(Order order) {
        requireNonNull(order);
        toAdd = order;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasOrder(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORDER);
        }

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
