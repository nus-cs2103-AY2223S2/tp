package trackr.logic.commands.order;

import static java.util.Objects.requireNonNull;
import static trackr.commons.core.Messages.MESSAGE_DUPLICATE_ITEM;
import static trackr.commons.core.Messages.MESSAGE_NO_MENU_ITEM;
import static trackr.commons.core.Messages.MESSAGE_SUCCESS;
import static trackr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERNAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERQUANTITY;
import static trackr.logic.parser.CliSyntax.PREFIX_PHONE;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.List;

import trackr.logic.commands.Command;
import trackr.logic.commands.CommandResult;
import trackr.logic.commands.exceptions.CommandException;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.menu.MenuItem;
import trackr.model.order.Order;

/**
 * Adds an order to the order list.
 * May violate LSP. Does not extend AddItemCommand.
 */
public class AddOrderCommand extends Command {
    public static final String COMMAND_WORD = "add_order";
    public static final String COMMAND_WORD_SHORTCUT = "add_o";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a order to the order list. "
            + "Parameters: "
            + PREFIX_ORDERNAME + "ORDER NAME "
            + PREFIX_ORDERQUANTITY + "ORDER QUANTITY "
            + PREFIX_DEADLINE + "DEADLINE "
            + "[" + PREFIX_STATUS + "STATUS] "
            + PREFIX_NAME + "CUSTOMER'S NAME "
            + PREFIX_PHONE + "CUSTOMER'S PHONE NUMBER "
            + PREFIX_ADDRESS + "CUSTOMER'S ADDRESS\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ORDERNAME + "CHOCOLATE COOKIES "
            + PREFIX_ORDERQUANTITY + "5 "
            + PREFIX_DEADLINE + "01/01/2024 "
            + PREFIX_STATUS + "N "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "12345678 "
            + PREFIX_ADDRESS + "123 Smith Street ";

    private final ModelEnum modelEnum;
    private final Order toAdd;

    /**
     * Creates an AddOrderCommand to add the specified {@code Order}
     */
    public AddOrderCommand(Order order) {
        requireNonNull(order);
        this.modelEnum = ModelEnum.ORDER;
        this.toAdd = order;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<MenuItem> currentMenuItems = model.getFilteredMenu();
        MenuItem existingItem = currentMenuItems.stream()
                                .filter(item -> item.getItemName().getName()
                                            .equals(toAdd.getOrderName().getName()))
                                .findAny()
                                .orElseThrow(() ->
                                            new CommandException(MESSAGE_NO_MENU_ITEM));
        Order validOrder = new Order(existingItem, toAdd.getOrderDeadline(), toAdd.getOrderStatus(),
                                     toAdd.getOrderQuantity(), toAdd.getCustomer());
        if (model.hasItem(validOrder, modelEnum)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_ITEM, modelEnum, validOrder));
        }

        model.addItem(validOrder, modelEnum);
        return new CommandResult(String.format(MESSAGE_SUCCESS, modelEnum, validOrder));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddOrderCommand
                && toAdd.equals(((AddOrderCommand) other).toAdd));
    }
}
