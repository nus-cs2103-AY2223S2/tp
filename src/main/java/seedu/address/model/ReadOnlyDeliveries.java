package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.deliveryjobs.DeliveryJob;

/**
 * Unmodifiable view of delivery jobs.
 */
public interface ReadOnlyDeliveries {
    /**
     * Returns an unmodifiable view of the Delivery list.
     * This list will not contain any duplicate deliveries.
     */
    ObservableList<DeliveryJob> getDeliveryJobList();

}
