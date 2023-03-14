package seedu.dengue.model;

import javafx.collections.ObservableList;
import seedu.dengue.model.person.Person;

/**
 * Unmodifiable view of an Dengue Hotspot Tracker
 */
public interface ReadOnlyDengueHotspotTracker {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
