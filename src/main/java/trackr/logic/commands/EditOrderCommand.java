package trackr.logic.commands;

import static java.util.Objects.requireNonNull;
import static trackr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERNAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERQUANTITY;
import static trackr.logic.parser.CliSyntax.PREFIX_PHONE;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;
import static trackr.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.util.List;

import trackr.commons.core.Messages;
import trackr.commons.core.index.Index;
import trackr.logic.commands.exceptions.CommandException;
import trackr.model.Model;
import trackr.model.order.Order;
import trackr.model.order.OrderDeadline;
import trackr.model.order.OrderDescriptor;
import trackr.model.order.OrderName;
import trackr.model.order.OrderQuantity;
import trackr.model.order.OrderStatus;
import trackr.model.order.customer.Customer;
import trackr.model.order.customer.CustomerAddress;
import trackr.model.order.customer.CustomerName;
import trackr.model.order.customer.CustomerPhone;

/**
 * Edits the details of an existing order in the order list.
 */
public class EditOrderCommand extends Command {
    public static final String COMMAND_WORD = "edit_order";
    public static final String COMMAND_WORD_SHORTCUT = "edit_o";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the order identified "
            + "by the index number used in the displayed order list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_ORDERNAME + "ORDER NAME] "
            + "[" + PREFIX_DEADLINE + "ORDER DEADLINE] "
            + "[" + PREFIX_STATUS + "ORDER STATUS]"
            + "[" + PREFIX_ORDERQUANTITY + "ORDER QUANTITY]"
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_ADDRESS + "ADDRESS "
            + "Example: " + COMMAND_WORD + " 2 "
            + PREFIX_NAME + "Birthday Cake"
            + PREFIX_STATUS + "D";

    public static final String MESSAGE_EDIT_ORDER_SUCCESS = "Edited Order: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ORDER = "This order already exists in the order list.";

    private final Index index;
    private final OrderDescriptor editOrderDescriptor;

    /**
     * @param index of the order in the filtered order list to edit
     * @param editOrderDescriptor details to edit the order with
     */

    public EditOrderCommand(Index index, OrderDescriptor editOrderDescriptor) {
        requireNonNull(index);
        requireNonNull(editOrderDescriptor);

        this.index = index;
        this.editOrderDescriptor = new OrderDescriptor(editOrderDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownOrderList = model.getFilteredOrderList();

        if (index.getZeroBased() >= lastShownOrderList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToEdit = lastShownOrderList.get(index.getZeroBased());
        Order editedOrder = createEditedOrder(orderToEdit, editOrderDescriptor);

        if (!orderToEdit.isSameOrder(editedOrder) && model.hasOrder(editedOrder)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORDER);
        }

        model.setOrder(orderToEdit, editedOrder);
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        return new CommandResult(String.format(MESSAGE_EDIT_ORDER_SUCCESS, editedOrder));
    }


    private static Order createEditedOrder(Order orderToEdit, OrderDescriptor editOrderDescriptor) {
        assert orderToEdit != null;

        OrderName updatedOrderName =
                editOrderDescriptor.getOrderName().orElse(orderToEdit.getOrderName());
        OrderDeadline updatedOrderDeadline =
                editOrderDescriptor.getOrderDeadline().orElse(orderToEdit.getOrderDeadline());
        OrderStatus updatedOrderStatus =
                editOrderDescriptor.getOrderStatus().orElse(orderToEdit.getOrderStatus());
        OrderQuantity updatedOrderQuantity =
                editOrderDescriptor.getOrderQuantity().orElse(orderToEdit.getOrderQuantity());
        CustomerName updatedCustomerName =
                editOrderDescriptor.getCustomerName().orElse(orderToEdit.getCustomer().getCustomerName());
        CustomerPhone updatedCustomerPhone =
                editOrderDescriptor.getCustomerPhone().orElse(orderToEdit.getCustomer().getCustomerPhone());
        CustomerAddress updatedCustomerAddress =
                editOrderDescriptor.getCustomerAddress().orElse(orderToEdit.getCustomer().getCustomerAddress());
        Customer updatedCustomer = new Customer(updatedCustomerName, updatedCustomerPhone, updatedCustomerAddress);

        return new Order(updatedOrderName, updatedOrderDeadline, updatedOrderStatus,
                updatedOrderQuantity, updatedCustomer);

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof EditOrderCommand)) {
            return false;
        }
        EditOrderCommand e = (EditOrderCommand) other;
        return index.equals(e.index)
                && editOrderDescriptor.equals(e.editOrderDescriptor);
    }
}
