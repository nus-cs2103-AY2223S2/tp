package seedu.loyaltylift.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.List;
import java.util.Optional;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.logic.commands.exceptions.CommandException;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.model.order.Quantity;

/**
 * Adds an order to LoyaltyLift.
 */
public class AddOrderCommand extends Command {

    public static final String COMMAND_WORD = "addo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an order to LoyaltyLift. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NAME + "ORDER_NAME "
            + "[" + PREFIX_QUANTITY + "QUANTITY] "
            + "[" + PREFIX_ADDRESS + "ADDRESS]";

    public static final String MESSAGE_SUCCESS = "New order added: \n%1$s";
    public static final String MESSAGE_DUPLICATE_ORDER = "This order already exists for the customer today";

    private final Index customerIndex;
    private final AddOrderDescriptor addOrderDescriptor;

    /**
     * Creates an AddOrderCommand to add the specified {@code Order}
     */
    public AddOrderCommand(Index customerIndex, AddOrderDescriptor addOrderDescriptor) {
        requireNonNull(customerIndex);
        requireNonNull(addOrderDescriptor);

        this.customerIndex = customerIndex;
        this.addOrderDescriptor = addOrderDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Customer> lastShownList = model.getFilteredCustomerList();

        if (customerIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX, MESSAGE_USAGE));
        }

        Customer taggedCustomer = lastShownList.get(customerIndex.getZeroBased());
        Order createdOrder = createOrder(taggedCustomer, addOrderDescriptor);

        if (model.hasOrder(createdOrder)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORDER);
        }

        model.addOrder(createdOrder);
        return new CommandResult(String.format(MESSAGE_SUCCESS, createdOrder));
    }

    /**
     * Creates and returns a {@code Order} with the details of {@code orderToAdd}
     * created with {@code addOrderDescriptor}.
     */
    private static Order createOrder(
            Customer taggedCustomer, AddOrderDescriptor addOrderDescriptor) {
        assert taggedCustomer != null;

        Name name = addOrderDescriptor.getName();
        Address address = addOrderDescriptor.getAddress().orElse(taggedCustomer.getAddress());
        Quantity quantity = addOrderDescriptor.getQuantity().orElse(new Quantity(1));

        return new Order(taggedCustomer, name, quantity, address);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddOrderCommand)) {
            return false;
        }

        // state check
        AddOrderCommand e = (AddOrderCommand) other;
        return customerIndex.equals(e.customerIndex)
                && addOrderDescriptor.equals(e.addOrderDescriptor);
    }

    /**
     * Stores the details to add the order with. Each non-empty field value will replace the
     * corresponding field value of the order.
     */
    public static class AddOrderDescriptor {
        private Quantity quantity;
        private Name name;
        private Address address;

        public AddOrderDescriptor() {}

        public void setName(Name name) {
            this.name = name;
        }

        public Name getName() {
            return this.name;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setQuantity(Quantity quantity) {
            this.quantity = quantity;
        }

        public Optional<Quantity> getQuantity() {
            return Optional.ofNullable(quantity);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof AddOrderDescriptor)) {
                return false;
            }

            // state check
            AddOrderDescriptor e = (AddOrderDescriptor) other;

            return getName().equals(e.getName())
                    && getAddress().equals(e.getAddress())
                    && getQuantity().equals(e.getQuantity());
        }
    }
}

