package seedu.address.model;

import java.util.LinkedList;
import java.util.Objects;

import javafx.util.Pair;


/**
 * Saves snapshots of past ModCheck states, used to implement the Undo Command.
 */
public class UndoManager {
    private final int maxSavedHistory;
    private LinkedList<AddressBook> addressBookHistory;
    private LinkedList<String> commandHistory;
    //VersionTracker at 0 if ModCheck at most recent history (no undo commands), increments by 1 for each undo command
    private int versionTracker;

    /**
     * Initialises an undo manager with given saved states and start state.
     * @param startState The starting state of ModCheck; no undos will be possible at this state.
     * @param maxSavedHistory The number of saved histories of ModCheck, determines the number of consecutive undos
     *                        supported.
     */
    public UndoManager(AddressBook startState, int maxSavedHistory) {
        this.maxSavedHistory = maxSavedHistory;
        addressBookHistory = new LinkedList<>();
        addressBookHistory.add(new AddressBook(startState));
        commandHistory = new LinkedList<>();
        commandHistory.add("");
        versionTracker = 0;
    }

    /**
     * Adds a copy of an address book to ModCheck saved history.
     * @param ab The address book to be added to ModCheck's history.
     */
    public void addToHistory(AddressBook ab, String command) {
        // For the case where changes are made after undoing, UndoManager will no longer track the overwritten changes
        if (versionTracker != 0) {
            deleteUntrackedHead();
        }
        if (addressBookHistory.size() == maxSavedHistory) {
            addressBookHistory.removeLast();
            commandHistory.removeLast();
        }
        //a copy of addressBook argument has to be created as ModelManager edits the AddressBook in place
        addressBookHistory.offerFirst(new AddressBook(ab));
        commandHistory.offerFirst(command);
    }

    /**
     * Deletes an untracked head from the model manager.
     * This is for changes made after undoing to be tracked unambiguously.
     */
    public void deleteUntrackedHead() {
        for (int i = 0; i < versionTracker; i++) {
            addressBookHistory.pollFirst();
            commandHistory.pollFirst();
        }
        versionTracker = 0;
    }

    /**
     * Returns true if model has a command that can be undone, false otherwise
     */
    public boolean hasUndoableCommand() {
        return versionTracker != addressBookHistory.size() - 1;
    }

    /**
     * Returns true if model has a command that can be redone, false otherwise
     */
    public boolean hasRedoableCommand() {
        return versionTracker > 0;
    }

    /**
     * Returns an AddressBook containing a saved state of ModCheck.
     * Calling this method multiple times will return earlier saved states of ModCheck, to facilitate chained undoes.
     * @return An AddressBook containing an earlier saved state of ModCheck
     */
    public Pair<AddressBook, String> getPreviousHistory() {
        assert hasUndoableCommand();
        versionTracker++;
        //Note that commandHistory and addressBookHistory is off by one
        //ie: The most recent change command will lead to the second most recent address book state
        return new Pair<>(addressBookHistory.get(versionTracker), commandHistory.get(versionTracker - 1));
    }

    /**
     * Returns an AddressBook containing a later saved state of ModCheck.
     * @return An AddressBook containing a later saved state of ModCheck.
     */
    public Pair<AddressBook, String> getNextHistory() {
        assert hasRedoableCommand();
        versionTracker--;
        return new Pair<>(addressBookHistory.get(versionTracker), commandHistory.get(versionTracker));
    }

    /**
     * Returns an AddressBook with the current state of ModCheck.
     * @return An AddressBook with the current state of ModCheck.
     */
    public AddressBook getCurrentState() {
        return addressBookHistory.get(versionTracker);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UndoManager that = (UndoManager) o;

        if (maxSavedHistory != that.maxSavedHistory) {
            return false;
        }
        if (versionTracker != that.versionTracker) {
            return false;
        }
        if (!Objects.equals(addressBookHistory, that.addressBookHistory)) {
            return false;
        }
        return Objects.equals(commandHistory, that.commandHistory);
    }
}
