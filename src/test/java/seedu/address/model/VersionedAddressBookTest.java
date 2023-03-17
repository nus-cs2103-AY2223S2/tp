package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import java.util.Collections;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

class VersionedAddressBookTest {
    private final VersionedAddressBook  versionedAddressBook= new VersionedAddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), versionedAddressBook.getAddressBookStateList());
    }

    @Test
    public void commit_cloneAddressBook() {
        AddressBook newData = getTypicalAddressBook();
        versionedAddressBook.commit(newData);
        assertFalse(newData == versionedAddressBook.getAddressBookStateList().get(versionedAddressBook.getSize() - 1));
        assertEquals(newData,versionedAddressBook.getAddressBookStateList().get(versionedAddressBook.getSize() - 1));
    }

    @Test
    public void commit_newest() {
        AddressBook newData = getTypicalAddressBook();
        versionedAddressBook.commit(newData);
        assertTrue(versionedAddressBook.getCurrentStatePointer() == versionedAddressBook.getSize() - 1);
    }

    @Test
    public void commit_after_undo() {
        int commitTime = 200;
        int undoTime = 100;
        AddressBook newData = getTypicalAddressBook();
        for (int i = 0; i < commitTime; i++) {
            versionedAddressBook.commit(newData);
        }
        for (int i = 0; i < undoTime; i++) {
            versionedAddressBook.undo();
        }
        assertTrue(versionedAddressBook.getCurrentStatePointer() ==
                commitTime - undoTime -1);
        versionedAddressBook.commit(newData);
        assertTrue(versionedAddressBook.getCurrentStatePointer() ==
                versionedAddressBook.getSize() - 1);
    }

    @Test
    void undo() {

    }

    @Test
    void redo() {
    }

    @Test
    void canUndo() {
    }

    @Test
    void canRedo() {
    }
}
