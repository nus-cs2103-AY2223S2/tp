package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.exceptions.DuplicateNoteException;
import seedu.address.model.task.exceptions.NoteNotFoundException;

/**
 * A list of Note that enforces uniqueness between its elements and does not allow nulls.
 * A Note is considered unique by comparing using {@code Note#isSameNote(Note)}. As such, adding and updating of
 * notes uses Note#isSameNote(Note) for equality to ensure that the note being added or updated is unique in terms of
 * identity in the UniqueNoteList. However, the removal of a note uses Note#equals(Object) to ensure that the Note with
 * exactly the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see Note#isSameNote(Note)
 */
public class UniqueNoteList implements Iterable<Note> {
    private final ObservableList<Note> internalNoteList = FXCollections.observableArrayList();
    private final ObservableList<Note> internalUnmodifiableNoteList =
            FXCollections.unmodifiableObservableList(internalNoteList);

    /**
     * Returns true if the list contains an equivalent note as the given {@code toCheck}.
     */
    public boolean containsNote(Note toCheck) {
        requireNonNull(toCheck);
        return internalNoteList.stream().anyMatch(toCheck::isSameNote);
    }

    /**
     * Adds a note {@code toAdd} to the list.
     * The note must not already exist in the list.
     */
    public void addNote(Note toAdd) {
        requireNonNull(toAdd);
        if (containsNote(toAdd)) {
            throw new DuplicateNoteException();
        }
        internalNoteList.add(toAdd);
    }

    /**
     * Replaces the note {@code target} in the list with {@code editedNote}.
     * {@code target} must exist in the list.
     * The identity of {@code editedNote} must not be the same as another existing note in the list.
     */
    public void setNotes(Note target, Note editedNote) {
        requireAllNonNull(target, editedNote);

        int index = internalNoteList.indexOf(target);
        if (index == -1) {
            throw new NoteNotFoundException();
        }

        if (!target.isSameNote(editedNote) && containsNote(editedNote)) {
            throw new DuplicateNoteException();
        }

        internalNoteList.set(index, editedNote);
    }

    /**
     * Replaces the contents of this list with {@code replacement}.
     * {@code replacement} must not contain duplicate notes.
     */
    public void setNotes(UniqueNoteList replacement) {
        requireNonNull(replacement);
        internalNoteList.setAll(replacement.internalNoteList);
    }

    /**
     * Replaces the contents of this list with {@code notes}.
     * {@code notes} must not contain duplicate notes.
     */
    public void setNotes(List<Note> notes) {
        requireAllNonNull(notes);
        if (!notesAreUnique(notes)) {
            throw new DuplicateNoteException();
        }

        internalNoteList.setAll(notes);
    }

    /**
     * Removes the equivalent note from the list.
     * The note must exist in the list.
     */
    public void remove(Note toRemove) {
        requireNonNull(toRemove);
        if (!internalNoteList.remove(toRemove)) {
            throw new NoteNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Note> asUnmodifiableObservableList() {
        return internalUnmodifiableNoteList;
    }

    @Override
    public Iterator<Note> iterator() {
        return internalNoteList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueNoteList // instanceof handles nulls
                && internalNoteList.equals(((UniqueNoteList) other).internalNoteList));
    }

    @Override
    public int hashCode() {
        return internalNoteList.hashCode();
    }

    /**
     * Returns true if {@code notes} contains only unique notes.
     */
    private boolean notesAreUnique(List<Note> notes) {
        for (int i = 0; i < notes.size() - 1; i++) {
            for (int j = i + 1; j < notes.size(); j++) {
                if (notes.get(i).isSameNote(notes.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
