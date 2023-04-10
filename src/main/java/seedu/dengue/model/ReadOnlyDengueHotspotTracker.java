package seedu.dengue.model;

import javafx.collections.ObservableList;
import seedu.dengue.model.person.Person;

/**
 * Unmodifiable view of a Dengue Hotspot Tracker
 */
public interface ReadOnlyDengueHotspotTracker {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Generates a deep copy of a {@code ReadOnlyDengueHotspotTracker}.
     * @return A deep copy of the {@code ReadOnlyDengueHotspotTracker}.
     */
    ReadOnlyDengueHotspotTracker generateDeepCopy();


}
