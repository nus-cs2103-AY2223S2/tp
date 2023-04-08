package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.exceptions.DuplicateLabException;
import seedu.address.model.event.exceptions.LabNotFoundException;

/**
 * Lists the lab that enforces uniqueness between its elements and does not allow nulls.
 * A lab is considered unique by comparing using {@code Lab#isSameLab(Lab)}.
 * As such, adding and updating of lab uses Lab#isSameLab(Lab) for equality to
 * ensure that the lab being added or updated is unique in terms of identity in the UniqueLabList.
 * However, the removal of a lab uses Lab#equals(Object) to ensure that the lab with
 * exactly the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see Lab#isSameLab(Lab)
 */
public class UniqueLabList implements Iterable<Lab> {

    private final ObservableList<Lab> internalList = FXCollections.observableArrayList();
    private final ObservableList<Lab> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent lab as the given argument.
     *
     * @param toCheck the lab to be checked on.
     * @return        the boolean result if the current list of labs contain the lab.
     */
    public boolean contains(Lab toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameLab);
    }

    /**
     * Checks whether an event is contained in the list universally.
     *
     * @param nameOfEvent Name of event.
     * @return A boolean indicator.
     */
    public boolean containsEventName(String nameOfEvent) {
        requireNonNull(nameOfEvent);
        return internalList.stream().anyMatch(x -> x.hasMatchByName(nameOfEvent));
    }

    /**
     * Checks whether a note is contained universally.
     *
     * @param note Note to check upon.
     * @return A boolean indicator.
     */
    public boolean containsNote(Note note) {
        requireNonNull(note);
        Optional<NoteList> mergedList = internalList.stream().map(Event::getNoteList).reduce(NoteList::merge);
        return mergedList.map(noteList -> noteList.getNotes().stream().anyMatch(note::equals)).orElse(false);
    }

    // todo: probably try to remove get and size methods to preserve abstraction barrier
    public Lab get(int index) {
        return this.internalList.get(index);
    }

    /**
     * Gets the number of labs.
     *
     * @return the number of labs.
     */
    public int size() {
        return this.internalList.size();
    }

    /**
     * Adds a lab to the list.
     * The lab must not already exist in the list.
     *
     * @param toAdd the lab to be added.
     */
    public void add(Lab toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateLabException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the lab {@code target} in the list with {@code editedLab}.
     * {@code target} must exist in the list.
     * The lab identity of {@code editedLab} must not be the same as another existing lab in the list.
     *
     * @param target        the target lab to be replaced.
     * @param editedLab     the edited lab to replace the target lab.
     */
    public void setLab(Lab target, Lab editedLab) {
        requireAllNonNull(target, editedLab);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new LabNotFoundException();
        }

        if (!target.isSameLab(editedLab) && contains(editedLab)) {
            throw new DuplicateLabException();
        }

        internalList.set(index, editedLab);
    }

    /**
     * Removes the equivalent lab from the list.
     * The lab must exist in the list.
     *
     * @param toRemove the lab to be removed.
     */
    public void remove(Lab toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new LabNotFoundException();
        }
    }

    /**
     * Sets the list of unique labs to a new list of unique labs.
     *
     * @param replacement the new list of unique labs.
     */
    public void setLabs(UniqueLabList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code labs}.
     * {@code labs} must not contain duplicate labs.
     *
     * @param labs the new list of labs.
     */
    public void setLabs(List<Lab> labs) {
        requireAllNonNull(labs);
        if (!labsAreUnique(labs)) {
            throw new DuplicateLabException();
        }

        internalList.setAll(labs);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Lab> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Lab> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueLabList // instanceof handles nulls
                && internalList.equals(((UniqueLabList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code labs} contains only unique labs.
     *
     * @param labs the labs to be checked for uniqueness.
     * @return     the boolean status of whether any of the labs are unique.
     */
    private boolean labsAreUnique(List<Lab> labs) {
        for (int i = 0; i < labs.size() - 1; i++) {
            for (int j = i + 1; j < labs.size(); j++) {
                if (labs.get(i).isSameLab(labs.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
