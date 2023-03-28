package ezschedule.model;

import ezschedule.model.event.Event;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of the scheduler
 */
public interface ReadOnlyScheduler {

    /**
     * Returns an unmodifiable view of the events list.
     * This list will not contain any duplicate events.
     */
    ObservableList<Event> getEventList();
}
