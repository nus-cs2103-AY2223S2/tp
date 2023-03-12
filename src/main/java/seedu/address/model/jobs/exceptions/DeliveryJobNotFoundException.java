package seedu.address.model.jobs.exceptions;

/**
 * DeliveryJobNotFoundException
 */
public class DeliveryJobNotFoundException extends RuntimeException {
    public DeliveryJobNotFoundException() {
        super("Operation would result in duplicate delivery jobs");
    }
}
