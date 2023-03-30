package trackr.model.person;

import java.util.HashSet;
import java.util.Set;

import trackr.model.ModelEnum;
import trackr.model.commons.Tag;

/**
 * Represents a Customer.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Customer extends Person {

    private static final PersonEmail EMPTY_EMAIL = new PersonEmail("dummy@dummy");
    private static final Set<Tag> EMPTY_TAGS = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Customer(CustomerName customerName, CustomerPhone customerPhone, CustomerAddress customerAddress) {
        super(customerName, customerPhone, EMPTY_EMAIL, customerAddress, EMPTY_TAGS, ModelEnum.CUSTOMER);
    }

    public CustomerName getCustomerName() {
        return (CustomerName) getPersonName();
    }

    public CustomerPhone getCustomerPhone() {
        return (CustomerPhone) getPersonPhone();
    }

    public CustomerAddress getCustomerAddress() {
        return (CustomerAddress) getPersonAddress();
    }
}
