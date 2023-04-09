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
import trackr.model.menu.MenuItem;
import trackr.model.order.Order;
import trackr.model.order.OrderDeadline;
import trackr.model.order.OrderDescriptor;
import trackr.model.order.OrderQuantity;
import trackr.model.order.OrderStatus;
import trackr.model.person.Customer;
import trackr.model.person.PersonAddress;
import trackr.model.person.PersonName;
import trackr.model.person.PersonPhone;

/**
 * Edits the details of an existing order in the order list.
 */
public class EditOrderCommand extends EditItemCommand<Order> {
    public static final String COMMAND_WORD = "edit_order";
    public static final String COMMAND_WORD_SHORTCUT = "edit_o";
    public static final String MESSAGE_EDIT_ITEM_SUCCESS = "Edited %s: %1$s";
    public static final String MESSAGE_NO_MENU_ITEM = "No such item in your menu.";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ITEM = "This %s already exists in the %s list.";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the order identified "
            + "by the index number used in the displayed order list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_ORDERNAME + "ORDER NAME] "
            + "[" + PREFIX_DEADLINE + "ORDER DEADLINE] "
            + "[" + PREFIX_STATUS + "ORDER STATUS] "
            + "[" + PREFIX_ORDERQUANTITY + "ORDER QUANTITY] "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "Example: " + COMMAND_WORD + " 2 "
            + PREFIX_NAME + "Birthday Cake"
            + PREFIX_STATUS + "D";

    /**
     * Creates an EditOrderCommand to edit the order at the given index.
     *
     * @param index The index of the order to be edited.
     * @param editOrderDescriptor The details to edit the order with.
     */
    public EditOrderCommand(Index index, OrderDescriptor editOrderDescriptor) {
        super(index, new OrderDescriptor(editOrderDescriptor), ModelEnum.ORDER);
    }

    @Override
    protected Order createEditedItem(Order itemToEdit, ItemDescriptor<? super Order> itemDescriptor) {
        assert itemToEdit != null;

        OrderDescriptor orderDescriptor = (OrderDescriptor) itemDescriptor;
        MenuItem updatedOrderItem =
                orderDescriptor.getOrderItem().orElse(itemToEdit.getOrderItem());
        OrderDeadline updatedOrderDeadline =
                orderDescriptor.getOrderDeadline().orElse(itemToEdit.getOrderDeadline());
        OrderStatus updatedOrderStatus =
                orderDescriptor.getOrderStatus().orElse(itemToEdit.getOrderStatus());
        OrderQuantity updatedOrderQuantity =
                orderDescriptor.getOrderQuantity().orElse(itemToEdit.getOrderQuantity());
        PersonName updatedCustomerName =
                orderDescriptor.getCustomerName().orElse(itemToEdit.getCustomer().getCustomerName());
        PersonPhone updatedCustomerPhone =
                orderDescriptor.getCustomerPhone().orElse(itemToEdit.getCustomer().getCustomerPhone());
        PersonAddress updatedCustomerAddress =
                orderDescriptor.getCustomerAddress().orElse(itemToEdit.getCustomer().getCustomerAddress());
        Customer updatedCustomer = new Customer(updatedCustomerName, updatedCustomerPhone, updatedCustomerAddress);

        return new Order(updatedOrderItem, updatedOrderDeadline, updatedOrderStatus,
                updatedOrderQuantity, updatedCustomer);
    }
}
