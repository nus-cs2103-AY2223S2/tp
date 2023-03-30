package trackr.model.person;

/**
 * Represents the Customer's Phone Number
 * Guaruntees: immutable; is valid as declared in {@link #isValidPersonPhone(String)}
 */
public class CustomerPhone extends PersonPhone {
    /**
     * Constructs a {@code CustomerPhone}.
     *
     * @param phone A valid customer phone number.
     */
    public CustomerPhone(String phone) {
        super(phone);
    }
}
