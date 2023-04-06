package trackr.logic.commands.order;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.CollectionUtil.requireAllNonNull;
import static trackr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERNAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERQUANTITY;
import static trackr.logic.parser.CliSyntax.PREFIX_PHONE;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;
import static trackr.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.List;

import trackr.commons.core.Messages;
import trackr.commons.core.index.Index;
import trackr.logic.commands.Command;
import trackr.logic.commands.CommandResult;
import trackr.logic.commands.exceptions.CommandException;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.item.ItemDescriptor;
import trackr.model.menu.MenuItem;
import trackr.model.order.Order;
import trackr.model.order.OrderDeadline;
import trackr.model.order.OrderDescriptor;
import trackr.model.order.OrderName;
import trackr.model.order.OrderQuantity;
import trackr.model.order.OrderStatus;
import trackr.model.person.Customer;
import trackr.model.person.PersonAddress;
import trackr.model.person.PersonName;
import trackr.model.person.PersonPhone;

/**
 * Edits the details of an existing order in the order list.
 */
public class EditOrderCommand extends Command {
    public static final String COMMAND_WORD = "edit_order";
    public static final String COMMAND_WORD_SHORTCUT = "edit_o";
    public static final String MESSAGE_EDIT_ITEM_SUCCESS = "Edited %s: %s";
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
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_ADDRESS + "ADDRESS "
            + "Example: " + COMMAND_WORD + " 2 "
            + PREFIX_NAME + "Birthday Cake"
            + PREFIX_STATUS + "D";

    private final Index index;
    private final OrderDescriptor editOrderDescriptor;
    /**
     * @param index               of the order in the filtered order list to edit
     * @param editOrderDescriptor details to edit the order with
     */

    public EditOrderCommand(Index index, OrderDescriptor editItemDescriptor) {
        requireAllNonNull(index, editItemDescriptor);
        this.index = index;
        this.editOrderDescriptor = editItemDescriptor;
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
        PersonName updatedCustomerName =
                orderDescriptor.getCustomerName().orElse(itemToEdit.getCustomer().getCustomerName());
        PersonPhone updatedCustomerPhone =
                orderDescriptor.getCustomerPhone().orElse(itemToEdit.getCustomer().getCustomerPhone());
        PersonAddress updatedCustomerAddress =
                orderDescriptor.getCustomerAddress().orElse(itemToEdit.getCustomer().getCustomerAddress());
        Customer updatedCustomer = new Customer(updatedCustomerName, updatedCustomerPhone, updatedCustomerAddress);


        return new Order(updatedOrderName, updatedOrderDeadline, updatedOrderStatus,
                updatedOrderQuantity, updatedCustomer);
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution
     */
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<MenuItem> currentMenuItems = model.getFilteredMenu();
        List<Order> currentOrders = model.getFilteredOrderList();

        if (this.index.getZeroBased() >= currentOrders.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX,
                    ModelEnum.ORDER.toString()));
        }

        Order orderToEdit = currentOrders.get(index.getZeroBased());
        Order editedOrder = createEditedItem(orderToEdit, editOrderDescriptor);
        // check if edited order name exists
        MenuItem existingItem = currentMenuItems.stream()
                .filter(item -> item.getItemName().getName()
                .equals(editedOrder.getOrderName().getName()))
                .findAny()
                .orElseThrow(() ->
                    new CommandException(MESSAGE_NO_MENU_ITEM));
        Order validOrder = new Order(existingItem, editedOrder.getOrderDeadline(), editedOrder.getOrderStatus(),
                            editedOrder.getOrderQuantity(), editedOrder.getCustomer());
        if (orderToEdit.isSameItem(validOrder) && model.hasItem(validOrder, ModelEnum.ORDER)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_ITEM, ModelEnum.ORDER.toString().toLowerCase()));
        }

        model.setItem(orderToEdit, validOrder, ModelEnum.ORDER);
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS, ModelEnum.ORDER);
        return new CommandResult(String.format(MESSAGE_EDIT_ITEM_SUCCESS, ModelEnum.ORDER.toString().toLowerCase(),
                validOrder.toString()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditOrderCommand)) {
            return false;
        }

        // state check
        EditOrderCommand e = (EditOrderCommand) other;
        return index.equals(e.index)
                && editOrderDescriptor.equals(e.editOrderDescriptor);
    }
}
