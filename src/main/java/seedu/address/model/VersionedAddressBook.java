package seedu.address.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.address.model.AddressBook;

public class VersionedAddressBook extends AddressBook {
    private final List<ReadOnlyAddressBook> versionStateHistory;
    private int currentStatePointer;

    public VersionedAddressBook(ReadOnlyAddressBook addressbook) {
        super(addressbook); // first version of versionaddressbook
        versionStateHistory = new ArrayList<>();
        versionStateHistory.add(new AddressBook(addressbook)); // add initial state
        currentStatePointer = 0;
    }

    public void commit() {
        versionStateHistory.add(new AddressBook(this));
        currentStatePointer++;
    }

    public void undo() {
        if (checkUndoable()) {
            currentStatePointer--;
            resetData(versionStateHistory.get(currentStatePointer));
        } else {
            // throw exception
        }
    }

    public void redo() {
        // can only redo after an undo is done
        if (checkRedoable()) {
            currentStatePointer++;
            resetData(versionStateHistory.get(currentStatePointer));
        }
    }

    public boolean checkUndoable() {
        return currentStatePointer > 0;
    }

    public boolean checkRedoable() {
        int numberOfStates = versionStateHistory.size();
        return numberOfStates > currentStatePointer;
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
