package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.jobs.DeliveryJob;

/**
 * ReadOnlyDeliveryJobSystem
 */
public interface ReadOnlyDeliveryJobSystem {

    ObservableList<DeliveryJob> getDeliveryJobList();

}
