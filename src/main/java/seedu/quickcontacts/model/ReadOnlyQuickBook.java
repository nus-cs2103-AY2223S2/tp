package seedu.quickcontacts.model;

import javafx.collections.ObservableList;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyQuickBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the meetings list.
     * This list will not contain any duplicate meetings.
     */
    ObservableList<Meeting> getMeetingList();
}
