package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.task.Note;

/**
 * Unmodifiable view of a note list
 */
public interface ReadOnlyNote {

    /**
     * Returns an unmodifiable view of the notes list.
     * This list will not contain any duplicate notes.
     */
    ObservableList<Note> getNoteList();

}
