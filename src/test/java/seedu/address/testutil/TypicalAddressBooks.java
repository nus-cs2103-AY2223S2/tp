package seedu.address.testutil;

import static seedu.address.testutil.TypicalMeetings.getTypicalMeetings;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import seedu.address.model.AddressBook;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code AddressBook} objects to be used in tests.
 */
public class TypicalAddressBooks {
    /**
     * Prevents initialisation
     */
    private TypicalAddressBooks() {}

    /**
     * Returns an {@code AddressBook} with all the typical persons only.
     */
    public static AddressBook getTypicalAddressBookPersonsOnly() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }

        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical meetings only.
     */
    public static AddressBook getTypicalAddressBookMeetingsOnly() {
        AddressBook ab = new AddressBook();
        for (Meeting meeting : getTypicalMeetings()) {
            ab.addMeeting(meeting);
        }

        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical persons and meetings.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }

        for (Meeting meeting : getTypicalMeetings()) {
            ab.addMeeting(meeting);
        }

        return ab;
    }
}
