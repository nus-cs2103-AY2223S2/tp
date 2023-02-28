package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Person;
import seedu.address.model.person.Volunteer;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyFriendlyLink {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();
    /**
     * Returns an unmodifiable view of the volunteer list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Volunteer> getVolunteerList();
    /**
     * Returns an unmodifiable view of the elderly list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Elderly> getElderlyList();

    /**
     * Returns an unmodifiable view of the pairs list.
     * This list will not contain any duplicate pairs.
     */
    ObservableList<Pair> getPairList();
}
