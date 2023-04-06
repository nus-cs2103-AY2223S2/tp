package seedu.address.model.entity.shop.exception;

/**
 * Thrown when customer not found in the shop
 */
public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(int id) {
        super(String.format("Customer %d does not exist", id));
    }
}
