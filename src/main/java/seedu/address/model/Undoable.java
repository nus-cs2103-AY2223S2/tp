package seedu.address.model;

/**
 * The interface for supporting undo and redo functionality.
 */
public interface Undoable {
    /**
     * Returns if there is an undoable command in model.
     * @return True if there is an undoable command in model, false otherwise.
     */
    boolean hasUndoableCommand();

    /**
     * Undoes the changes made by the last modification command used
     * @return The string representation of the last modification command used
     */
    String executeUndo();
    /**
     * Returns if there is a redoable command in model.
     * @return True if there is a redoable command in model, false otherwise.
     */
    boolean hasRedoableCommand();

    /**
     * Redoes the changes unmade by the last undo command
     * @return The string representation of the command redone
     */
    String executeRedo();
}
