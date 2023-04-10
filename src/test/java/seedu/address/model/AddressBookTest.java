package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_OWE_MONEY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.time.ScheduleWeek;
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
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_OWE_MONEY)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_OWE_MONEY)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void hasGroupInPerson_returnsTrue() {
        Group group = new Group("2103T");
        Person aliceCopy = new PersonBuilder(ALICE).build();
        addressBook.addPersonInGroup(aliceCopy, group);
        assertTrue(aliceCopy.getGroups().contains(group));
    }

    @Test
    public void doNotHaveGroupInPerson_returnsTrue() {
        Group group = new Group("2103T");
        Person aliceCopy = new PersonBuilder(ALICE).build();
        addressBook.addPersonInGroup(aliceCopy, group);
        addressBook.removePersonFromGroup(aliceCopy, group);
        assertTrue(!aliceCopy.getGroups().contains(group));
    }

    @Test
    public void hasGroup_groupInAddressBook_returnsTrue() {
        Group group = new Group("2103T");
        addressBook.addGroup(group);
        assertTrue(addressBook.hasGroup(group));
    }

    @Test
    public void doNotHaveGroup_groupInAddressBook_returnsTrue() {
        Group group = new Group("2103T");
        addressBook.addGroup(group);
        addressBook.deleteGroup(group);
        assertTrue(!addressBook.hasGroup(group));
    }

    @Test
    public void deleteGroup_groupInAddressBook_returnsTrue() {
        Group group = new Group("CS2101");
        addressBook.addGroup(group);
        Person person = new PersonBuilder(ALICE).withGroups("CS2101").build();
        addressBook.addPerson(person);
        addressBook.deleteGroup(group);
        assertFalse(person.getGroups().contains(group));

    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Group> groups = FXCollections.observableArrayList();

        private final ScheduleWeek scheduleWeek = new ScheduleWeek();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Group> getGroupList() {
            return groups;
        }

        /**
         * Returns current filtered schedule of the address book.
         */
        @Override
        public ScheduleWeek getSchedule() {
            return scheduleWeek;
        }
    }

}
