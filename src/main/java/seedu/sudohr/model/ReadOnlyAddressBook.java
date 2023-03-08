package seedu.sudohr.model;

import javafx.collections.ObservableList;
import seedu.sudohr.model.person.Person;

/**
 * Unmodifiable view of an sudohr book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
