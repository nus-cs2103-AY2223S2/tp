package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.application.exceptions.ApplicationNotFoundException;
import seedu.address.model.application.exceptions.DuplicateApplicationException;

/**
 * A list of Applications that enforces uniqueness between its elements and does not allow nulls.
 * An Application is considered unique by comparing using {@code Application#equals(Object)}.
 * As such, adding and updating of Applications uses Application#equals(Object) for equality to ensure that
 * the Application being added or updated is unique in terms of identity in the UniqueApplicationList.
 *
 * Supports a minimal set of list operations.
 *
 * @see Application#equals(Object)
 */

public class UniqueApplicationList implements Iterable<Application> {
    private final ObservableList<Application> internalList = FXCollections.observableArrayList();
    private final ObservableList<Application> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Application as the given argument.
     */
    public boolean contains(Application toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds an Application to the list.
     * The Application must not already exist in the list.
     */
    public void add(Application toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateApplicationException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Application {@code target} in the list with {@code editedApplication}.
     * {@code target} must exist in the list.
     * The edited Application {@code editedApplication} must not be the same as another existing Application
     * in the list.
     */
    public void setApplication(Application target, Application editedApplication) {
        requireAllNonNull(target, editedApplication);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ApplicationNotFoundException();
        }

        if (contains(editedApplication)) {
            throw new DuplicateApplicationException();
        }

        internalList.set(index, editedApplication);
    }

    /**
     * Removes the equivalent Application from the list.
     * The Application must exist in the list.
     */
    public void remove(Application toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ApplicationNotFoundException();
        }
    }

    public void setApplications(UniqueApplicationList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code applications}.
     * {@code applications} must not contain duplicate Applications.
     */
    public void setApplications(List<Application> applications) {
        requireAllNonNull(applications);
        if (!applicationsAreUnique(applications)) {
            throw new DuplicateApplicationException();
        }

        internalList.setAll(applications);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Application> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Application> iterator() {
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
     * Returns true if {@code applications} contains only unique Applications.
     */
    private boolean applicationsAreUnique(List<Application> applications) {
        for (int i = 0; i < applications.size() - 1; i++) {
            for (int j = i + 1; j < applications.size(); j++) {
                if (applications.get(i).equals(applications.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
