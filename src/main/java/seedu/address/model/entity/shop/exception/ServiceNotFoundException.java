package seedu.address.model.entity.shop.exception;


/**
 * Thrown when service not found in the shop
 */
public class ServiceNotFoundException extends Exception {
    /**
     * Constructs a new ServiceNotFoundException.
     * @param id ID of the service that was not found.
     */
    public ServiceNotFoundException(int id) {
        super(String.format("Service %d does not exist", id));
    }
}
