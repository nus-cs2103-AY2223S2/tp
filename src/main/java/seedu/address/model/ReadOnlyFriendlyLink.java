package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of FriendlyLink.
 */
public interface ReadOnlyFriendlyLink extends ReadOnlyElderly, ReadOnlyVolunteer {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the pairs list.
     * This list will not contain any duplicate pairs.
     */
    ObservableList<Pair> getPairList();
}
