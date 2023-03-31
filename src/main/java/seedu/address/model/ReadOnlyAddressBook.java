package seedu.address.model;

import javafx.collections.ObservableList;
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
     * Returns size of the persons list.
     */
    int size();

    /**
     * Returns the sum of the potential earnings.
     */
    long getPotentialEarnings();

    /**
     * Returns the a string containing all tags.
     */
    String getTags();

    /**
     * Returns the a string containing all companies.
     */
    String getCompanies();

}
