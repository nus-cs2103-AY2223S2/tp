package trackr.model.person;

/**
 * Represents a Customer's Name
 * Guaruntees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class CustomerName extends PersonName {

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public CustomerName(String name) {
        super(name);
    }

}
