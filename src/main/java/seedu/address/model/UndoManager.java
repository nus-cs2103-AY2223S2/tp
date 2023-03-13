package seedu.address.model;

import java.util.LinkedList;

import static java.util.Objects.requireNonNull;


/**
 * Saves an archive of past ModCheck states, used to implement the Undo Command.
 */
public class UndoManager {
    private final int maxSavedHistory;
    private LinkedList<AddressBook> history;
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
        history = new LinkedList<>();
        history.add(new AddressBook(startState));
        versionTracker = 0;
    }

    public UndoManager resetHistory(AddressBook startState) {
        return new UndoManager(startState, this.maxSavedHistory);
    }

    public void addToHistory(AddressBook ab) {
        // For the case where changes are made after undoing, UndoManager will no longer track the overwritten changes
        if (versionTracker != 0) {
            deleteUntrackedHead();
        }
        if (history.size() == maxSavedHistory) {
            history.removeLast();
        }
        //a copy of addressBook argument has to be created as ModelManager edits the AddressBook in place
        history.offerFirst(new AddressBook(ab));
    }

    public void deleteUntrackedHead() {
        for (int i = 0; i < versionTracker; i++) {
            history.pollFirst();
        }
        versionTracker = 0;
    }

    public boolean hasUndoableCommand() {
        return versionTracker != history.size() - 1;
    }

    public AddressBook getHistory() {
        versionTracker++;
        return history.get(versionTracker);
    }

}
