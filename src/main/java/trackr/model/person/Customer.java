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
    public Customer(PersonName customerName, PersonPhone customerPhone, PersonAddress customerAddress) {
        super(customerName, customerPhone, EMPTY_EMAIL, customerAddress, EMPTY_TAGS, ModelEnum.CUSTOMER);
    }

    public PersonName getCustomerName() {
        return getPersonName();
    }

    public PersonPhone getCustomerPhone() {
        return getPersonPhone();
    }

    public PersonAddress getCustomerAddress() {
        return getPersonAddress();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getPersonName())
                .append("; Phone: ")
                .append(getPersonPhone())
                .append("; Address: ")
                .append(getPersonAddress());
        return builder.toString();
    }
}
