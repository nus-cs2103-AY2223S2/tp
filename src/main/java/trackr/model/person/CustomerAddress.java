package trackr.model.person;

/**
 * Represents a Customer's Address
 * Guaruntees: immutable; is valid as declared in {@link #isValidPersonAddress(String)}
 */
public class CustomerAddress extends PersonAddress {

    /**
     * Constructs an {@code CustomerAddress}.
     *
     * @param address A valid customer address.
     */
    public CustomerAddress(String address) {
        super(address);
    }

}
