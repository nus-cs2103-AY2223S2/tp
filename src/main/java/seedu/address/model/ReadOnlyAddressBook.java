package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.MeetingWithPerson;
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
     * Returns an unmodifiable view of the meeting list based on the meeting fields in each person.
     * <p> Meetings are very dependent on the list of {@code Person} that {@code AddressBook} contains.
     * This method will look through every Person and collect all meetings that each {@code Person} has.
     * Current implementation has to look through the whole {@code Person} list each time the complete
     * Meeting List is requested, for safety and correctness reasons.
     * This should not have significant impact on runtime since the complete Meeting List is only requested
     * on initialisation. <strong>Use this method efficiently and try not to call it too many times.</strong>
     */
    ObservableList<MeetingWithPerson> getMeetingList();

}
