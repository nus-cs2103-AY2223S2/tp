package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.CannotRedoAddressBookException;
import seedu.address.commons.exceptions.CannotUndoAddressBookException;

class VersionedAddressBookTest {

    private final VersionedAddressBook addressBook = new VersionedAddressBook(new AddressBook());

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void constructor_withNonEmptyAddressBookAndEmptyStateList() {
        VersionedAddressBook versionedAddressBook = new VersionedAddressBook(getTypicalAddressBook(),
                Collections.emptyList());
        assertEquals(versionedAddressBook.getPersonList(), getTypicalAddressBook().getPersonList());
    }

    @Test
    public void constructor_withEmptyStateList() {
        VersionedAddressBook versionedAddressBook = new VersionedAddressBook(Collections.emptyList());
        assertEquals(Collections.emptyList(), versionedAddressBook.getPersonList());
    }

    @Test
    void commit_newAddressBook_noStatesRemovedCurrentStateSaved() {
        addressBook.commit();
        assertEquals(addressBook.getAddressBookStateList().size(), 2);
    }

    @Test
    void undo_initialState_throwsCannotUndoAddressBookException() {
        assertThrows(CannotUndoAddressBookException.class, () -> addressBook.undo());
    }

    @Test
    void redo_initialState_throwsCannotRedoAddressBookException() {
        assertThrows(CannotRedoAddressBookException.class, () -> addressBook.redo());
    }

    @Test
    void canUndo_initialState_returnsFalse() {
        assertEquals(addressBook.canUndo(), false);
    }

    @Test
    void canRedo_initialState_returnsFalse() {
        assertEquals(addressBook.canRedo(), false);
    }
}
