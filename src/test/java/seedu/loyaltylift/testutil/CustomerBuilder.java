package seedu.loyaltylift.testutil;

import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.attribute.Note;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.customer.CustomerType;
import seedu.loyaltylift.model.customer.Email;
import seedu.loyaltylift.model.customer.Marked;
import seedu.loyaltylift.model.customer.Phone;
import seedu.loyaltylift.model.customer.Points;

/**
 * A utility class to help with building Customer objects.
 */
public class CustomerBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final CustomerType DEFAULT_CUSTOMER_TYPE = CustomerType.INDIVIDUAL;
    public static final Integer DEFAULT_POINTS = 0;
    public static final Integer DEFAULT_CUMULATIVE_POINTS = 0;
    public static final Boolean DEFAULT_MARKED = false;
    public static final String DEFAULT_NOTE = "";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private CustomerType customerType;
    private Points points;
    private Marked marked;
    private Note note;

    /**
     * Creates a {@code CustomerBuilder} with the default details.
     */
    public CustomerBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        customerType = DEFAULT_CUSTOMER_TYPE;
        points = new Points(DEFAULT_POINTS, DEFAULT_CUMULATIVE_POINTS);
        marked = new Marked(DEFAULT_MARKED);
        note = new Note(DEFAULT_NOTE);
    }

    /**
     * Initializes the CustomerBuilder with the data of {@code customerToCopy}.
     */
    public CustomerBuilder(Customer customerToCopy) {
        name = customerToCopy.getName();
        phone = customerToCopy.getPhone();
        email = customerToCopy.getEmail();
        address = customerToCopy.getAddress();
        customerType = customerToCopy.getCustomerType();
        points = customerToCopy.getPoints();
        marked = customerToCopy.getMarked();
        note = customerToCopy.getNote();
    }

    /**
     * Sets the {@code Name} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code CustomerType} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withCustomerType(CustomerType customerType) {
        this.customerType = customerType;
        return this;
    }

    /**
     * Sets the {@code Points} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withPoints(Integer points, Integer cumulativePoints) {
        this.points = new Points(points, cumulativePoints);
        return this;
    }

    /**
     * Sets the {@code Marked} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withMarked(Boolean marked) {
        this.marked = new Marked(marked);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withNote(String note) {
        this.note = new Note(note);
        return this;
    }

    public Customer build() {
        return new Customer(customerType, name, phone, email, address, points, marked, note);
    }

}
