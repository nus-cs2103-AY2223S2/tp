package trackr.logic.commands;

import static java.util.Objects.requireNonNull;
import static trackr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_EMAIL;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_ORDERNAME;
import static trackr.logic.parser.CliSyntax.PREFIX_PHONE;
import static trackr.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;
import static trackr.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.util.List;
import java.util.Optional;

import trackr.commons.core.Messages;
import trackr.commons.core.index.Index;
import trackr.commons.util.CollectionUtil;
import trackr.logic.commands.exceptions.CommandException;
import trackr.model.Model;
import trackr.model.order.Order;
import trackr.model.order.OrderDeadline;
import trackr.model.order.OrderName;
import trackr.model.order.OrderQuantity;
import trackr.model.order.OrderStatus;
import trackr.model.order.customer.Customer;

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
            + "[" + PREFIX_QUANTITY + "ORDER QUANTITY]"
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "Example: " + COMMAND_WORD + " 2 "
            + PREFIX_NAME + "Birthday Cake"
            + PREFIX_STATUS + "D";

    public static final String MESSAGE_EDIT_ORDER_SUCCESS = "Edited Order: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ORDER = "This order already exists in the order list.";

    private final Index index;
    private final EditOrderCommand.EditOrderDescriptor editOrderDescriptor;

    /**
     * @param index of the order in the filtered order list to edit
     * @param editOrderDescriptor details to edit the order with
     */

    public EditOrderCommand(Index index, EditOrderCommand.EditOrderDescriptor editOrderDescriptor) {
        requireNonNull(index);
        requireNonNull(editOrderDescriptor);

        this.index = index;
        this.editOrderDescriptor = new EditOrderCommand.EditOrderDescriptor(editOrderDescriptor);
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


    private static Order createEditedOrder(Order orderToEdit, EditOrderDescriptor editOrderDescriptor) {
        assert orderToEdit != null;

        OrderName updatedOrderName =
                editOrderDescriptor.getOrderName().orElse(orderToEdit.getOrderName());
        OrderDeadline updatedOrderDeadline =
                editOrderDescriptor.getOrderDeadline().orElse(orderToEdit.getOrderDeadline());
        OrderStatus updatedOrderStatus =
                editOrderDescriptor.getOrderStatus().orElse(orderToEdit.getOrderStatus());
        OrderQuantity updatedOrderQuantity =
                editOrderDescriptor.getOrderQuantity().orElse(orderToEdit.getOrderQuantity());
        Customer updatedCustomer =
                editOrderDescriptor.getCustomer().orElse(orderToEdit.getCustomer());

        return new Order(updatedOrderName, updatedOrderDeadline, updatedOrderStatus, updatedOrderQuantity,
                updatedCustomer);
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

    /**
     * Stores the details to edit the order with. Each non-empty field value will replace the
     * corresponding field value of the order.
     */
    public static class EditOrderDescriptor {
        private OrderName orderName;
        private OrderDeadline orderDeadline;
        private OrderStatus orderStatus;
        private OrderQuantity orderQuantity;
        private Customer customer;

        public EditOrderDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditOrderDescriptor(EditOrderDescriptor toCopy) {
            setOrderName(toCopy.orderName);
            setOrderDeadline(toCopy.orderDeadline);
            setOrderStatus(toCopy.orderStatus);
            setOrderQuantity(toCopy.orderQuantity);
            setCustomer(toCopy.customer);
        }

        private void setOrderQuantity(OrderQuantity orderQuantity) {
            this.orderQuantity = orderQuantity;
        }

        private void setCustomer(Customer customer) {
            this.customer = customer;
        }


        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(orderName, orderDeadline, orderStatus);
        }

        public void setOrderName(OrderName orderName) {
            this.orderName = orderName;
        }

        public Optional<OrderName> getOrderName() {
            return Optional.ofNullable(orderName);
        }

        public void setOrderDeadline(OrderDeadline orderDeadline) {
            this.orderDeadline = orderDeadline;
        }

        public Optional<OrderDeadline> getOrderDeadline() {
            return Optional.ofNullable(orderDeadline);
        }

        public void setOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
        }

        public Optional<OrderStatus> getOrderStatus() {
            return Optional.ofNullable(orderStatus);
        }

        public Optional<Customer> getCustomer() {
            return Optional.ofNullable(customer);
        }

        public Optional<OrderQuantity> getOrderQuantity() {
            return Optional.ofNullable(orderQuantity);
        }


        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditOrderDescriptor)) {
                return false;
            }

            EditOrderDescriptor e = (EditOrderDescriptor) other;

            return getOrderName().equals(e.getOrderName())
                    && getOrderDeadline().equals(e.getOrderDeadline())
                    && getOrderStatus().equals(e.getOrderStatus())
                    && getOrderQuantity().equals(e.getOrderQuantity())
                    && getCustomer().equals(e.getCustomer());
        }


    }
}
