package trackr.testutil;

import trackr.model.order.customer.Customer;
import trackr.model.order.customer.CustomerAddress;
import trackr.model.order.customer.CustomerName;
import trackr.model.order.customer.CustomerPhone;

/**
 * A utility class to help with building Customer objects.
 */
public class CustomerBuilder {

    public static final String DEFAULT_CUSTOMER_NAME = "John Smith";
    public static final String DEFAULT_CUSTOMER_PHONE = "12345678";
    public static final String DEFAULT_CUSTOMER_ADDRESS = "123 Smith Street";

    private CustomerName customerName;
    private CustomerPhone customerPhone;
    private CustomerAddress customerAddress;

    /**
     * Creates a {@code CustomerBuilder} with the default details.
     */
    public CustomerBuilder() {
        customerName = new CustomerName(DEFAULT_CUSTOMER_NAME);
        customerPhone = new CustomerPhone(DEFAULT_CUSTOMER_PHONE);
        customerAddress = new CustomerAddress(DEFAULT_CUSTOMER_ADDRESS);
    }

    /**
     * Initializes the CustomerBuilder with the data of {@code customerToCopy}.
     */
    public CustomerBuilder(Customer customerToCopy) {
        customerName = customerToCopy.getCustomerName();
        customerPhone = customerToCopy.getCustomerPhone();
        customerAddress = customerToCopy.getCustomerAddress();
    }

    /**
     * Sets the {@code CustomerName} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withCustomerName(String customerName) {
        this.customerName = new CustomerName(customerName);
        return this;
    }

    /**
     * Sets the {@code CustomerPhone} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withCustomerPhone(String customerPhone) {
        this.customerPhone = new CustomerPhone(customerPhone);
        return this;
    }

    /**
     * Sets the {@code CustomerAddress} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withCustomerAddress(String customerAddress) {
        this.customerAddress = new CustomerAddress(customerAddress);
        return this;
    }

    public Customer build() {
        return new Customer(customerName, customerPhone, customerAddress);
    }

}
