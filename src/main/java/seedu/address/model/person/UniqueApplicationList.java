package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicateNoteException;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.DuplicateTodoException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.todo.InternshipTodo;
import seedu.address.model.todo.Note;

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
public class UniqueApplicationList implements Iterable<InternshipApplication> {
    private final ObservableList<InternshipApplication> internalList = FXCollections.observableArrayList();
    private final ObservableList<InternshipApplication> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent application as the given argument.
     */
    public boolean contains(InternshipApplication toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameApplication);
    }

    /**
     * Adds an application to the list.
     * The application must not already exist in the list.
     */
    public void add(InternshipApplication toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the application {@code target} in the list with {@code editedApplication}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedApplication} must not be the same
     * as another existing application in the list.
     */
    public void setApplication(InternshipApplication target, InternshipApplication editedPerson) {
        requireAllNonNull(target, editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameApplication(editedPerson) && contains(editedPerson)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedPerson);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(InternshipApplication toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setApplications(UniqueApplicationList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setApplications(List<InternshipApplication> applications) {
        requireAllNonNull(applications);
        if (!applicationsAreUnique(applications)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(applications);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<InternshipApplication> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<InternshipApplication> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueApplicationList // instanceof handles nulls
                && internalList.equals(((UniqueApplicationList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean applicationsAreUnique(List<InternshipApplication> persons) {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).isSameApplication(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
