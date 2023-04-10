package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.session.Session;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class VersionedAddressBookTest {

    private final AddressBook addressBook = new AddressBook();
    private final VersionedAddressBook versionedAddressBook = new VersionedAddressBook(addressBook);
    private final Tag test = new Tag("bob");
    private final Set<Tag> testList = new HashSet<>(Arrays.asList(test, new Tag("ben")));

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), versionedAddressBook.getPersonList());
    }

    @Test
    public void commitWipesRedundantStates() throws CommandException {
        AddressBook addressBookTest = getTypicalAddressBook();
        VersionedAddressBook versionedAddressBookTest = new VersionedAddressBook(addressBookTest);
        int initialLength = versionedAddressBookTest.getStateList().size();
        versionedAddressBookTest.removePerson(ALICE);
        versionedAddressBookTest.commit();
        versionedAddressBookTest.undo();
        versionedAddressBookTest.commit();
        int finalLength = versionedAddressBookTest.getStateList().size();
        assertNotEquals(initialLength, finalLength);
    }

    @Test
    public void redoWorks() throws CommandException {
        AddressBook addressBookTest = new AddressBook();
        VersionedAddressBook versionedAddressBookTest = new VersionedAddressBook(addressBookTest);
        versionedAddressBookTest.addPerson(ALICE);
        versionedAddressBookTest.commit();
        int initialLength = versionedAddressBookTest.getStateList().size();
        versionedAddressBookTest.undo();
        versionedAddressBookTest.redo();
        int finalLength = versionedAddressBookTest.getStateList().size();
        assertEquals(initialLength, finalLength);
    }

    @Test
    public void statePointerMovesCorrectly() throws CommandException {
        AddressBook addressBookTest = new AddressBook();
        VersionedAddressBook versionedAddressBookTest = new VersionedAddressBook(addressBookTest);
        int initialLength = versionedAddressBookTest.getStateList().size();
        assertEquals(initialLength, 1);
        assertEquals(versionedAddressBookTest.getCurrentStatePointer(), 0);
        versionedAddressBookTest.addPerson(ALICE);
        versionedAddressBookTest.commit();
        int finalLength = versionedAddressBookTest.getStateList().size();
        assertEquals(finalLength, 2);
        assertEquals(versionedAddressBookTest.getCurrentStatePointer(), 1);
        assertTrue(versionedAddressBookTest.getStateList().contains(versionedAddressBookTest.getCurrentState()));
    }

    @Test
    public void cannotUndoOrRedoInitially() {
        AddressBook addressBookTest = getTypicalAddressBook();
        VersionedAddressBook versionedAddressBookTest = new VersionedAddressBook(addressBookTest);
        assertFalse(versionedAddressBookTest.canUndo());
        assertFalse(versionedAddressBookTest.canRedo());
        assertThrows(CommandException.class, versionedAddressBookTest::undo);
        assertThrows(CommandException.class, versionedAddressBookTest::redo);
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> versionedAddressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        versionedAddressBook.resetData(newData);
        assertEquals(newData, versionedAddressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> versionedAddressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> versionedAddressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(versionedAddressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        versionedAddressBook.addPerson(ALICE);
        assertTrue(versionedAddressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        versionedAddressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(versionedAddressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> versionedAddressBook.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Tag> tags = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Session> getSessionList() {
            return null;
        }
    }

}
