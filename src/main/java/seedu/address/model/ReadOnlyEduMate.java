package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.meetup.MeetUp;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;

import java.util.Set;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyEduMate {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns a view of the user object.
     * This will always be non-null.
     */
    User getUser();

    //todo double check new addition
    /**
     * Returns an unmodifiable view of the meet ups lists.
     */
    ObservableList<MeetUp> getMeetUpList();

    //todo double check new addition
    Set<ContactIndex> getParticipantList();

}
