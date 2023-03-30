package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;

import seedu.address.model.AddressBook;

public class VersionedAddressBook extends AddressBook {
    private final List<ReadOnlyAddressBook> versionStateHistory;
    private int currentStatePointer;
    public static final String UNDO_VERSION_FAILURE = "Dont have older version to restore";
    public static final String REDO_VERSION_FAILURE = "Dont have new version to restore";

    public VersionedAddressBook(ReadOnlyAddressBook addressbook) {
        super(addressbook); // first version of versionaddressbook
        versionStateHistory = new ArrayList<>();
        versionStateHistory.add(new AddressBook(addressbook)); // add initial state
        currentStatePointer = 0;
    }

    public void commit() {
        for (int i = currentStatePointer + 1; i < versionStateHistory.size(); i++) {
            versionStateHistory.remove(i);
        }
        versionStateHistory.add(new AddressBook(this));
        currentStatePointer++;
    }

    public void undo() {
        if (checkUndoable()) {
            currentStatePointer--;
            resetData(versionStateHistory.get(currentStatePointer));
        } else {
            throw new RuntimeException(UNDO_VERSION_FAILURE);
        }
    }

    public void redo() {
        // can only redo after an undo is done
        if (checkRedoable()) {
            currentStatePointer++;
            resetData(versionStateHistory.get(currentStatePointer));
        } else {
            throw new RuntimeException(REDO_VERSION_FAILURE);
        }
    }

    public boolean checkUndoable() {
        return currentStatePointer > 0;
    }

    public boolean checkRedoable() {
        int numberOfStates = versionStateHistory.size();
        return numberOfStates - 1 > currentStatePointer;
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
                && currentStatePointer == otherVersionedAddressBook.currentStatePointer;
    }
}
