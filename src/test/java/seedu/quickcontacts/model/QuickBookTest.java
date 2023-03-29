package seedu.quickcontacts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.quickcontacts.testutil.Assert.assertThrows;
import static seedu.quickcontacts.testutil.TypicalMeetings.MEETING_A;
import static seedu.quickcontacts.testutil.TypicalPersons.ALICE;
import static seedu.quickcontacts.testutil.TypicalQuickBooks.getTypicalQuickBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.quickcontacts.logic.commands.CommandTestUtil;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.model.meeting.exceptions.DuplicateMeetingException;
import seedu.quickcontacts.model.person.Person;
import seedu.quickcontacts.model.person.exceptions.DuplicatePersonException;
import seedu.quickcontacts.testutil.MeetingBuilder;
import seedu.quickcontacts.testutil.PersonBuilder;

public class QuickBookTest {

    private final QuickBook quickBook = new QuickBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), quickBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> quickBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyQuickBook_replacesData() {
        QuickBook newData = getTypicalQuickBook();
        quickBook.resetData(newData);
        assertEquals(newData, quickBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(CommandTestUtil.VALID_ADDRESS_BOB)
            .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        QuickBookStub newData = new QuickBookStub(newPersons, Collections.singletonList(MEETING_A));

        assertThrows(DuplicatePersonException.class, () -> quickBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateMeetings_throwsDuplicateMeetingException() {
        // Two meetings with the same identity fields
        Meeting editedMeetingA = new MeetingBuilder(MEETING_A).withLocation(CommandTestUtil.VALID_MEETING_LOCATION)
            .withDescription(CommandTestUtil.VALID_MEETING_DESCRIPTION).build();
        List<Meeting> newMeetings = Arrays.asList(MEETING_A, editedMeetingA);
        QuickBookStub newData = new QuickBookStub(Collections.singletonList(ALICE), newMeetings);

        assertThrows(DuplicateMeetingException.class, () -> quickBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> quickBook.hasPerson(null));
    }

    @Test
    public void hasMeeting_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> quickBook.hasMeeting(null));
    }

    @Test
    public void getPersonByName_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> quickBook.getPersonByName(null));
    }

    @Test
    public void getPersonByName_personNotInQuickBook_returnsNull() {
        assertNull(quickBook.getPersonByName(ALICE.getName()));
    }

    @Test
    public void getPersonByName_personInQuickBook_returnsPerson() {
        quickBook.addPerson(ALICE);
        assertEquals(ALICE, quickBook.getPersonByName(ALICE.getName()));
    }

    @Test
    public void hasPerson_personNotInQuickBook_returnsFalse() {
        assertFalse(quickBook.hasPerson(ALICE));
    }

    @Test
    public void hasMeeting_meetingNotInQuickBook_returnsFalse() {
        assertFalse(quickBook.hasMeeting(MEETING_A));
    }

    @Test
    public void hasPerson_personInQuickBook_returnsTrue() {
        quickBook.addPerson(ALICE);
        assertTrue(quickBook.hasPerson(ALICE));
    }

    @Test
    public void hasMeeting_meetingInQuickBook_returnsTrue() {
        quickBook.addMeeting(MEETING_A);
        assertTrue(quickBook.hasMeeting(MEETING_A));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInQuickBook_returnsTrue() {
        quickBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(CommandTestUtil.VALID_ADDRESS_BOB)
            .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        assertTrue(quickBook.hasPerson(editedAlice));
    }

    @Test
    public void hasMeeting_meetingWithSameIdentityFieldsInQuickBook_returnsTrue() {
        quickBook.addMeeting(MEETING_A);
        Meeting editedMeeting = new MeetingBuilder(MEETING_A).withLocation(CommandTestUtil.VALID_MEETING_LOCATION)
            .withDescription(CommandTestUtil.VALID_MEETING_DESCRIPTION).build();
        assertTrue(quickBook.hasMeeting(editedMeeting));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> quickBook.getPersonList().remove(0));
    }

    @Test
    public void getMeetingList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> quickBook.getMeetingList().remove(0));
    }


    /**
     * A stub ReadOnlyQuickBook whose persons/meetings list can violate interface constraints.
     */
    private static class QuickBookStub implements ReadOnlyQuickBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Meeting> meetings = FXCollections.observableArrayList();

        QuickBookStub(Collection<Person> persons, Collection<Meeting> meetings) {
            this.persons.setAll(persons);
            this.meetings.setAll(meetings);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Meeting> getMeetingList() {
            return meetings;
        }
    }

}
