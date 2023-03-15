package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_LOCATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBooks.getTypicalAddressBook;
import static seedu.address.testutil.TypicalMeetings.MEETING_A;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.exceptions.DuplicateMeetingException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
            .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons, Collections.singletonList(MEETING_A));

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateMeetings_throwsDuplicateMeetingException() {
        // Two meetings with the same identity fields
        Meeting editedMeetingA = new MeetingBuilder(MEETING_A).withLocation(VALID_MEETING_LOCATION)
            .withDescription(VALID_MEETING_DESCRIPTION).build();
        List<Meeting> newMeetings = Arrays.asList(MEETING_A, editedMeetingA);
        AddressBookStub newData = new AddressBookStub(Collections.singletonList(ALICE), newMeetings);

        assertThrows(DuplicateMeetingException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasMeeting_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasMeeting(null));
    }

    @Test
    public void getPersonByName_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.getPersonByName(null));
    }

    @Test
    public void getPersonByName_personNotInAddressBook_returnsNull() {
        assertNull(addressBook.getPersonByName(ALICE.getName()));
    }

    @Test
    public void getPersonByName_personInAddressBook_returnsPerson() {
        addressBook.addPerson(ALICE);
        assertEquals(ALICE, addressBook.getPersonByName(ALICE.getName()));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasMeeting_meetingNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasMeeting(MEETING_A));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasMeeting_meetingInAddressBook_returnsTrue() {
        addressBook.addMeeting(MEETING_A);
        assertTrue(addressBook.hasMeeting(MEETING_A));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
            .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void hasMeeting_meetingWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addMeeting(MEETING_A);
        Meeting editedMeeting = new MeetingBuilder(MEETING_A).withLocation(VALID_MEETING_LOCATION)
            .withDescription(VALID_MEETING_DESCRIPTION).build();
        assertTrue(addressBook.hasMeeting(editedMeeting));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void getMeetingList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getMeetingList().remove(0));
    }


    /**
     * A stub ReadOnlyAddressBook whose persons/meetings list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Meeting> meetings = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons, Collection<Meeting> meetings) {
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
