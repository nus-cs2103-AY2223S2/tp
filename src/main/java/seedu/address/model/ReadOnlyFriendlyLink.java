package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyFriendlyLink {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    // TODO: Update the return type of the following methods to their corresponding classes
    ObservableList<Person> getElderlyList();
    ObservableList<Person> getVolunteerList();
}
