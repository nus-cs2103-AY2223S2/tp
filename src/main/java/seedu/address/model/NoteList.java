package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.task.Note;
import seedu.address.model.task.UniqueNoteList;

/**
 * Wraps all data at the note-list level
 * Duplicates are not allowed (by .isSameNote comparison)
 */
public class NoteList implements ReadOnlyNote {

    private final UniqueNoteList notes;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * NoteList that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     * among constructors.
     */
    {
        notes = new UniqueNoteList();
    }

    public NoteList() {}

    /**
     * Creates a NoteList using the Notes in the {@code toBeCopied}
     */
    public NoteList(ReadOnlyNote toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the note list with {@code notes}.
     * {@code notes} must not contain duplicate notes.
     */
    public void setNote(List<Note> notes) {
        this.notes.setNotes(notes);
    }

    /**
     * Replaces the given note {@code target} with {@code editedNote}.
     * {@code target} must exist in the tracker.
     * The identity of {@code editedNote} must not be the same as another existing Note in the tracker.
     */
    public void setNote(Note target, Note editedNote) {
        requireNonNull(editedNote);

        notes.setNotes(target, editedNote);
    }

    /**
     * Resets the existing data of this {@code NoteList} with {@code newData}.
     */
    public void resetData(ReadOnlyNote newData) {
        requireNonNull(newData);

        setNote(newData.getNoteList());
    }

    /**
     * Returns true if a note with the same identity
     * as {@code note} exists in the tracker.
     */
    public boolean hasNote(Note note) {
        requireNonNull(note);
        return notes.containsNote(note);
    }

    /**
     * Adds a note to the tracker.
     * The note must not already exist in the tracker.
     */
    public void addNote(Note note) {
        notes.addNote(note);
    }

    /**
     * Removes {@code key} from this {@code NoteList}.
     * {@code key} must exist in the note list.
     */
    public void removeNote(Note key) {
        notes.remove(key);
    }

    /**
     * Clear note list.
     */
    public void clearNote(ReadOnlyNote newData) {
        setNote(newData.getNoteList());
    }

    //// util methods
    @Override
    public String toString() {
        return notes.asUnmodifiableObservableList().size() + " notes";
    }

    @Override
    public ObservableList<Note> getNoteList() {
        return notes.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NoteList // instanceof handles nulls
                && notes.equals(((NoteList) other).notes));
    }

    @Override
    public int hashCode() {
        return notes.hashCode();
    }
}
