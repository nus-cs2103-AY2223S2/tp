package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.DeliveryJob;
import seedu.address.model.person.Person;

public interface ReadOnlyDeliveries {
    /**
     * Returns an unmodifiable view of the Delivery list.
     * This list will not contain any duplicate deliveries.
     */
    ObservableList<Person> getDeliveryJobList();

}
