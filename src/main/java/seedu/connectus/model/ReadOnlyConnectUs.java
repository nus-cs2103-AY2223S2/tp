package seedu.connectus.model;

import javafx.collections.ObservableList;
import seedu.connectus.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyConnectUs {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
