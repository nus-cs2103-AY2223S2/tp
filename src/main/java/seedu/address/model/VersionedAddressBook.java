package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

//@@author wendy0107-reused
// Reused from https://github.com/AY2223S1-CS2103T-W17-4 and adapted from proposed implementation in AB3's Developer
// Guide https://se-education.org/addressbook-level3/DeveloperGuide.html
// with minor modifications such as renaming and some different implementation.

public class VersionedAddressBook extends AddressBook {
    public static final String UNDO_VERSION_FAILURE = "Dont have older version to restore";
    public static final String REDO_VERSION_FAILURE = "Dont have new version to restore";

    private final List<ReadOnlyAddressBook> versionStateHistory;
    private int currentVersionPointer;

    public VersionedAddressBook(ReadOnlyAddressBook addressbook) {
        super(addressbook); // first version of versionaddressbook
        versionStateHistory = new ArrayList<>();
        versionStateHistory.add(new AddressBook(addressbook)); // add initial state
        currentVersionPointer = 0;
    }

    public void commit() {
        for (int i = versionStateHistory.size() - 1; i > currentVersionPointer; i--) {
            versionStateHistory.remove(i);
        }
        versionStateHistory.add(new AddressBook(this));
        currentVersionPointer++;
    }

    public void undo() {
        if (checkUndoable()) {
            currentVersionPointer--;
            resetData(versionStateHistory.get(currentVersionPointer));
        } else {
            throw new RuntimeException(UNDO_VERSION_FAILURE);
        }
    }

    public void redo() {
        // can only redo after an undo is done
        if (checkRedoable()) {
            currentVersionPointer++;
            resetData(versionStateHistory.get(currentVersionPointer));
        } else {
            throw new RuntimeException(REDO_VERSION_FAILURE);
        }
    }

    public boolean checkUndoable() {
        return currentVersionPointer > 0;
    }

    public boolean checkRedoable() {
        int numberOfStates = versionStateHistory.size();
        return numberOfStates - 1 > currentVersionPointer;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof VersionedAddressBook)) {
            return false;
        }

        VersionedAddressBook otherVersionedAddressBook = (VersionedAddressBook) other;

        return super.equals(otherVersionedAddressBook)
                && currentVersionPointer == otherVersionedAddressBook.currentVersionPointer;
    }
    //@@author
}
