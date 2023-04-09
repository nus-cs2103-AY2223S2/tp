package seedu.loyaltylift.testutil;

import java.time.LocalDate;
import java.util.List;

import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.attribute.Note;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.order.CreatedDate;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.model.order.Quantity;
import seedu.loyaltylift.model.order.Status;
import seedu.loyaltylift.model.order.StatusUpdate;
import seedu.loyaltylift.model.order.StatusValue;
import seedu.loyaltylift.model.util.SampleDataUtil;

/**
 * A utility class to help with building Order objects.
 */
public class OrderBuilder {

    public static final Customer DEFAULT_CUSTOMER = TypicalCustomers.ALICE;
    public static final String DEFAULT_NAME = "Banana Split";
    public static final int DEFAULT_QUANTITY = 2;
    public static final List<StatusUpdate> DEFAULT_STATUS = List.of(
            new StatusUpdate(StatusValue.PENDING, LocalDate.of(2022, 12, 20)));
    public static final String DEFAULT_ADDRESS = "11 Fabordrive, Singapore 3001298";
    public static final LocalDate DEFAULT_DATE = LocalDate.of(2022, 12, 20);
    public static final String DEFAULT_NOTE = "";

    private Customer customer;
    private Name name;
    private Quantity quantity;
    private Address address;
    private Status status;
    private CreatedDate createdDate;
    private Note note;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        customer = DEFAULT_CUSTOMER;
        name = new Name(DEFAULT_NAME);
        quantity = new Quantity(DEFAULT_QUANTITY);
        status = new Status(DEFAULT_STATUS);
        address = new Address(DEFAULT_ADDRESS);
        createdDate = new CreatedDate(DEFAULT_DATE);
        note = new Note(DEFAULT_NOTE);
    }

    /**
     * Initializes the OrderBuilder with the data of {@code orderToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        customer = orderToCopy.getCustomer();
        name = orderToCopy.getName();
        quantity = orderToCopy.getQuantity();
        address = orderToCopy.getAddress();
        status = orderToCopy.getStatus();
        createdDate = orderToCopy.getCreatedDate();
        note = orderToCopy.getNote();
    }

    /**
     * Sets the {@code Customer} association of the {@code Order} that we are building.
     */
    public OrderBuilder withCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Order} that we are building.
     */
    public OrderBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code Order} that we are building.
     */
    public OrderBuilder withQuantity(String quantity) {
        this.quantity = SampleDataUtil.getQuantity(quantity);
        return this;
    }

    /**
     * Sets a new {@code Status} of the {@code Order} that we are building.
     * The status will be at 'Pending' stage.
     */
    public OrderBuilder withInitialStatus(String date) {
        this.status = SampleDataUtil.getInitialStatus(date);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Order} that we are building.
     * Status will
     */
    public OrderBuilder withNextStatus(String date) {
        this.status = SampleDataUtil.getNextStatus(status, date);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Order} that we are building.
     * Status will
     */
    public OrderBuilder withPrevStatus() {
        this.status = SampleDataUtil.getPrevStatus(status);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Order} that we are building.
     */
    public OrderBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code CreatedDate} of the {@code Order} that we are building.
     */
    public OrderBuilder withCreatedDate(String createdDate) {
        this.createdDate = SampleDataUtil.getCreatedDate(createdDate);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Order} that we are building.
     */
    public OrderBuilder withNote(String note) {
        this.note = new Note(note);
        return this;
    }

    public Order build() {
        return new Order(customer, name, quantity, address, status, createdDate, note);
    }

}
