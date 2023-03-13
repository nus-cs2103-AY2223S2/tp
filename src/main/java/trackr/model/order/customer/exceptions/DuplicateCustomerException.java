package trackr.model.order.customer.exceptions;

public class DuplicateCustomerException extends RuntimeException {
    public DuplicateCustomerException() {
        super("Operation would result in duplicate customers");
    }
}
