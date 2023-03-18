package seedu.address.model.todo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicateNoteException;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A list of InternshipApplications that enforces uniqueness between its elements and does not allow nulls.
 * An InternshipApplication is considered unique by comparing using
 * {@code InternshipApplication#isSameApplication(InternshipApplication)}. As such, adding and updating of
 * persons uses InternshipApplication#isSameApplication(InternshipApplication) for equality
 * so as to ensure that the person being added or updated is unique in terms of identity in the UniquePersonList.
 * However, the removal of a person uses InternshipApplication#equals(Object) so
 * as to ensure that the InternshipApplication with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniqueNoteList implements Iterable<Note> {
    private final ObservableList<Note> internalNoteList = FXCollections.observableArrayList();
    private final ObservableList<Note> internalUnmodifiableNoteList =
            FXCollections.unmodifiableObservableList(internalNoteList);

    /**
     * Returns true if the list contains an equivalent note as the given argument.
     */
    public boolean containsNote(Note toCheck) {
        requireNonNull(toCheck);
        return internalNoteList.stream().anyMatch(toCheck::isSameNote);
    }

    /**
     * Adds a note to the list.
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
     * Replaces the application {@code target} in the list with {@code editedApplication}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedApplication} must not be the same
     * as another existing application in the list.
     * @param target
     * @param editedNote
     */
    public void setNotes(Note target, Note editedNote) {
        requireAllNonNull(target, editedNote);

        int index = internalNoteList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameNote(editedNote) && containsNote(editedNote)) {
            throw new DuplicatePersonException();
        }

        internalNoteList.set(index, editedNote);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Note toRemove) {
        requireNonNull(toRemove);
        if (!internalNoteList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setNotes(UniqueNoteList replacement) {
        requireNonNull(replacement);
        internalNoteList.setAll(replacement.internalNoteList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     * @param notes
     */
    public void setNotes(List<Note> notes) {
        requireAllNonNull(notes);
        if (!notesAreUnique(notes)) {
            throw new DuplicateNoteException();
        }

        internalNoteList.setAll(notes);
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
