package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
        assertNotEquals(newData,versionedAddressBook.getAddressBookStateList().get(versionedAddressBook.getSize() - 1));
    }

    @Test
    public void addCommit() {
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
