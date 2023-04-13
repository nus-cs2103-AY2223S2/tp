package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list and tasks list.
     * This list will not contain any duplicate persons or tasks.
     */
    ObservableList<Person> getPersonList();
}
