package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Tutorial;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the tutorials list.
     * This list will not contain any duplicate tutorials.
     */
    ObservableList<Tutorial> getTutorialList();

    /**
     * Returns an unmodifiable view of the labs list.
     * This list will not contain any duplicate labs.
     */
    ObservableList<Lab> getLabList();
}
