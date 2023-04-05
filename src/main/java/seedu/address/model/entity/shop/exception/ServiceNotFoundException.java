package seedu.address.model.entity.shop.exception;


// Thrown when service not found in the shop
public class ServiceNotFoundException extends Exception {
    public ServiceNotFoundException(int id) {
        super(String.format("Service %d does not exist", id));
    }
}
