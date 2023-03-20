package trackr.logic.commands.order;

import static trackr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERNAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERQUANTITY;
import static trackr.logic.parser.CliSyntax.PREFIX_PHONE;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;

import trackr.commons.core.index.Index;
import trackr.logic.commands.EditItemCommand;
import trackr.model.ModelEnum;
import trackr.model.item.ItemDescriptor;
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
public class EditOrderCommand extends EditItemCommand<Order> {
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

    /**
     * @param index               of the order in the filtered order list to edit
     * @param editOrderDescriptor details to edit the order with
     */

    public EditOrderCommand(Index index, OrderDescriptor editOrderDescriptor) {
        super(index, editOrderDescriptor, ModelEnum.ORDER);
    }

    protected Order createEditedItem(Order itemToEdit, ItemDescriptor<? super Order> itemDescriptor) {
        assert itemToEdit != null;

        OrderDescriptor orderDescriptor = (OrderDescriptor) itemDescriptor;

        OrderName updatedOrderName =
                orderDescriptor.getOrderName().orElse(itemToEdit.getOrderName());
        OrderDeadline updatedOrderDeadline =
                orderDescriptor.getOrderDeadline().orElse(itemToEdit.getOrderDeadline());
        OrderStatus updatedOrderStatus =
                orderDescriptor.getOrderStatus().orElse(itemToEdit.getOrderStatus());
        OrderQuantity updatedOrderQuantity =
                orderDescriptor.getOrderQuantity().orElse(itemToEdit.getOrderQuantity());
        CustomerName updatedCustomerName =
                orderDescriptor.getCustomerName().orElse(itemToEdit.getCustomer().getCustomerName());
        CustomerPhone updatedCustomerPhone =
                orderDescriptor.getCustomerPhone().orElse(itemToEdit.getCustomer().getCustomerPhone());
        CustomerAddress updatedCustomerAddress =
                orderDescriptor.getCustomerAddress().orElse(itemToEdit.getCustomer().getCustomerAddress());
        Customer updatedCustomer = new Customer(updatedCustomerName, updatedCustomerPhone, updatedCustomerAddress);

        return new Order(updatedOrderName, updatedOrderDeadline, updatedOrderStatus,
                updatedOrderQuantity, updatedCustomer);
    }
}
