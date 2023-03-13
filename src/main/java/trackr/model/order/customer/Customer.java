package trackr.model.order.customer;

import static trackr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Customer {

	private final CustomerName customerName;
	private final CustomerPhone customerPhone;
	private final CustomerAddress customerAddress;

	/**
     * Every field must be present and not null.
     */
	public Customer(CustomerName customerName, CustomerPhone customerPhone, CustomerAddress customer) {
		requireAllNonNull(customerName, customerPhone, customer);
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddress = customer;
	}

	public CustomerName getCustomerName() {
        return customerName;
    }

	public CustomerPhone getCustomerPhone() {
        return customerPhone;
    }

	public CustomerAddress getCustomerAddress() {
        return customerAddress;
    }

	/**
     * Returns true if both customers have the same name.
     * This defines a weaker notion of equality between two customers.
     */
	public boolean isSameCustomer(Customer otherCustomer) {
		if (otherCustomer == this) {
			return true;
		}
		return otherCustomer!= null
				&& otherCustomer.getCustomerName().equals(getCustomerName());
	}

	/**
     * Returns true if both customers have the same identity and data fields.
     * This defines a stronger notion of equality between two customers.
     */
	@Override
    public boolean equals(Object other) {
		if (other == this) {
			return true;
		}

		if (!(other instanceof Customer)) {
			return false;
		}

		Customer otherCustomer = (Customer) other;
		return otherCustomer.getCustomerName().equals(getCustomerName())
				&& otherCustomer.getCustomerPhone().equals(getCustomerPhone())
				&& otherCustomer.getCustomerAddress().equals(getCustomerAddress());
	}

	@Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(customerName, customerPhone, customerAddress);
    }

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append(getCustomerName())
				.append("; Phone: ")
				.append(getCustomerPhone())
				.append("; Address: ")
				.append(getCustomerAddress());
		return builder.toString();
	}

}
