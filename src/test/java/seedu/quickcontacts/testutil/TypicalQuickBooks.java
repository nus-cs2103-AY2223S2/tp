package seedu.quickcontacts.testutil;

import static seedu.quickcontacts.testutil.TypicalMeetings.getTypicalMeetings;

import seedu.quickcontacts.model.QuickBook;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.model.person.Person;

/**
 * A utility class containing a list of {@code QuickBook} objects to be used in tests.
 */
public class TypicalQuickBooks {
    /**
     * Prevents initialisation
     */
    private TypicalQuickBooks() {}

    /**
     * Returns an {@code QuickBook} with all the typical persons only.
     */
    public static QuickBook getTypicalQuickBookPersonsOnly() {
        QuickBook ab = new QuickBook();
        for (Person person : TypicalPersons.getTypicalPersons()) {
            ab.addPerson(person);
        }

        return ab;
    }

    /**
     * Returns an {@code QuickBook} with all the typical meetings only.
     */
    public static QuickBook getTypicalQuickBookMeetingsOnly() {
        QuickBook ab = new QuickBook();
        for (Meeting meeting : getTypicalMeetings()) {
            ab.addMeeting(meeting);
        }

        return ab;
    }

    /**
     * Returns an {@code QuickBook} with all the typical persons and meetings.
     */
    public static QuickBook getTypicalQuickBook() {
        QuickBook ab = new QuickBook();
        for (Person person : TypicalPersons.getTypicalPersons()) {
            ab.addPerson(person);
        }

        for (Meeting meeting : getTypicalMeetings()) {
            ab.addMeeting(meeting);
        }

        return ab;
    }
}
