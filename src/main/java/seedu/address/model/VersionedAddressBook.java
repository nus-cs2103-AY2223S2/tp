package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

//@@author wendy0107-reused
// Reused from https://github.com/AY2223S1-CS2103T-W17-4 and adapted from proposed implementation in AB3's Developer
// Guide https://se-education.org/addressbook-level3/DeveloperGuide.html
// with minor modifications such as renaming, having some different implementation,
// and the inclusion of a variable to track the undone/redone commands 'commandHistory'.

/**
 * {@code AddressBook} that keeps track of its own version history.
 */
public class VersionedAddressBook extends AddressBook {
    public static final String UNDO_VERSION_FAILURE = "Dont have older version to restore";
    public static final String REDO_VERSION_FAILURE = "Dont have new version to restore";

    private final List<ReadOnlyAddressBook> versionStateHistory;
    private final List<String> commandHistory;
    private int currentVersionPointer;

    /**
     * Constructs a new instance of VersionedAddressBook.
     * @param addressbook addressbook is the initial state of AddressBook.
     */
    public VersionedAddressBook(ReadOnlyAddressBook addressbook) {
        super(addressbook); // first version of versionaddressbook
        versionStateHistory = new ArrayList<>();
        commandHistory = new ArrayList<>();
        versionStateHistory.add(new AddressBook(addressbook)); // add initial state
        currentVersionPointer = 0;
    }

    /**
     * Saves a copy of the current {@code AddressBook} state and the last
     * executed command, and clears any outdated undone versions.
     *
     * @param lastExecutedCommand The last executed command.
     */
    public void commit(String lastExecutedCommand) {
        for (int i = versionStateHistory.size() - 1; i > currentVersionPointer; i--) {
            versionStateHistory.remove(i);
        }
        versionStateHistory.add(new AddressBook(this));
        commandHistory.subList(currentVersionPointer, commandHistory.size()).clear();
        commandHistory.add(lastExecutedCommand);
        assert commandHistory.size() == versionStateHistory.size() - 1;
        currentVersionPointer++;
    }

    /**
     * Restores the previous state of AddressBook, and returns the name of the
     * command that was undone.
     *
     * @return The name of the command that was undone.
     */
    public String undo() {
        assert commandHistory.size() == versionStateHistory.size() - 1;

        if (checkUndoable()) {
            currentVersionPointer--;
            resetData(versionStateHistory.get(currentVersionPointer));
            return commandHistory.get(currentVersionPointer);
        } else {
            throw new RuntimeException(UNDO_VERSION_FAILURE);
        }
    }

    /**
     * Restores an undone AddressBook state, and returns the name of the command
     * that was redone.
     *
     * @return The name of the command that was redone.
     */
    public String redo() {
        assert commandHistory.size() == versionStateHistory.size() - 1;

        // can only redo after an undo is done
        if (checkRedoable()) {
            currentVersionPointer++;
            resetData(versionStateHistory.get(currentVersionPointer));
            return commandHistory.get(currentVersionPointer - 1);
        } else {
            throw new RuntimeException(REDO_VERSION_FAILURE);
        }
    }

    /**
     * Checks if versionStateHistory has states to undo.
     * @return true if there are states to undo and false otherwise.
     */
    public boolean checkUndoable() {
        return currentVersionPointer > 0;
    }

    /**
     * Checks if versionStateHistory has undone states to redo.
     * @return true if there are undone states to redo and false otherwise.
     */
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
