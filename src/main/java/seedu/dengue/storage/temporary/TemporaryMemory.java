package seedu.dengue.storage.temporary;


import java.util.EmptyStackException;

import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.model.ReadOnlyDengueHotspotTracker;

/**
 * A class that stores previous {@link ReadOnlyDengueHotspotTracker} objects as memory temporarily.
 * When this app is not in use, the memory is reset.
 * Only the past 10 iterations of the file will be stored, and older iterations are deleted.
 */
public class TemporaryMemory extends SpecialisedStackForMemory<ReadOnlyDengueHotspotTracker> {

    /**
     * Creates a temporary memory modelled after a {@link SpecialisedStackForMemory}.
     * @param latest The most current {@link ReadOnlyDengueHotspotTracker} data.
     */
    public TemporaryMemory(ReadOnlyDengueHotspotTracker latest) {
        super(latest.generateDeepCopy());
    }


    /**
     * Pushes the latest updated {@link ReadOnlyDengueHotspotTracker} into the memory stack.
     * If the memory stack is full, delete the oldest entry.
     * @param latest The newest iteration of {@link ReadOnlyDengueHotspotTracker}.
     */
    public void saveNewLatest(ReadOnlyDengueHotspotTracker latest) {
        push(latest.generateDeepCopy());
        clearRedoStorage();
        if (isFull()) {
            removeOld();
        }
    }

    /**
     * Loads the current {@link ReadOnlyDengueHotspotTracker}
     * @return An unmodifiable view of the tracker.
     */
    public ReadOnlyDengueHotspotTracker loadCurrent() {
        return super.peek();
    }

    /**
     * Checks if further undo operations are possible.
     */
    public boolean canUndo() {
        return this.getRedoHistory().size() > 1;
    }

    /**
     * Undo the latest operation.
     */
    public void undo() throws CommandException {
        if (canUndo()) {
            super.temporaryPop();
        } else {
            throw new CommandException("Cannot undo any further!");
        }
    }

    /**
     * Redo the last operation.
     */
    public void redo() throws CommandException {
        try {
            super.pushOneFromTemporaryPop();
        } catch (EmptyStackException err) {
            throw new CommandException("Reached the latest iteration!");
        }
    }

    /**
     * Delete all memory of future operations.
     */
    public void clearRedoStorage() {
        super.clearStorage();
    }


}
