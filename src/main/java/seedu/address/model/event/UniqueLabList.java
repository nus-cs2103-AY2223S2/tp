package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.exceptions.DuplicateLabException;
import seedu.address.model.event.exceptions.LabNotFoundException;
import seedu.address.model.person.Person;

/**
 * A list of lab that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}.
 * As such, adding and updating of lab uses Person#isSamePerson(Person) for equality to
 * ensure that the person being added or updated is unique in terms of identity in the UniquePersonList.
 * However, the removal of a person uses Person#equals(Object) to ensure that the person with exactly the
 * same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniqueLabList implements Iterable<Lab> {

    private final ObservableList<Lab> internalList = FXCollections.observableArrayList();
    private final ObservableList<Lab> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent lab as the given argument.
     */
    public boolean contains(Lab toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameLab);
    }

    // todo: probably try remove get and size methods to preserve abstraction barrier
    public Lab get(int index) {
        return this.internalList.get(index);
    }

    public int size() {
        return this.internalList.size();
    }

    /**
     * Adds a lab to the list.
     * The lab must not already exist in the list.
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
     */
    public void remove(Lab toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new LabNotFoundException();
        }
    }

    public void setLabs(UniqueLabList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code labs}.
     * {@code labs} must not contain duplicate labs.
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
