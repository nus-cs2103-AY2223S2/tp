package trackr.logic.commands.order;

import static trackr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERNAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERQUANTITY;
import static trackr.logic.parser.CliSyntax.PREFIX_PHONE;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;

import trackr.logic.commands.AddItemCommand;
import trackr.model.ModelEnum;
import trackr.model.order.Order;

/**
 * Adds an order to the order list
 */
public class AddOrderCommand extends AddItemCommand<Order> {
    public static final String COMMAND_WORD = "add_order";
    public static final String COMMAND_WORD_SHORTCUT = "add_o";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a order to the order list. "
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

    /**
     * Creates an AddOrderCommand to add the specified {@code Order}
     */
    public AddOrderCommand(Order order) {
        super(order, ModelEnum.ORDER);
    }
}
