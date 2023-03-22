package seedu.internship.model;


import javafx.collections.ObservableList;
import seedu.internship.model.event.Event;
import seedu.internship.model.internship.Internship;

/**
 * Unmodifiable view of an Event Catalouge
 */
public interface ReadOnlyEventCatalogue {

    /**
     * Returns an unmodifiable view of the event list.
     * This list will not contain any duplicate events.
     */
    ObservableList<Event> getEventList();
}
