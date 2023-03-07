package mycelium.mycelium.model.project;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mycelium.mycelium.model.project.exceptions.DuplicateProjectException;
import mycelium.mycelium.model.project.exceptions.ProjectNotFoundException;

/**
 * A list of projects with invariant that every project is unique. Whether two projects are identical is determined
 * by a call to {@code Project#isSameProject}. Any attempt to add a duplicate project to the list results in {@code
 * DuplicateProjectException} being thrown.
 */
public class UniqueProjectList implements Iterable<Project> {

    private final ObservableList<Project> internalList = FXCollections.observableArrayList();
    private final ObservableList<Project> internalUnmodifiableList = FXCollections
        .unmodifiableObservableList(internalList);

    /**
     * Checks if a project is in this list.
     */
    public boolean contains(Project toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameProject);
    }

    /**
     * Adds a project to the list. If the same project already exists, throws {@code DuplicateProjectException}.
     */
    public void add(Project toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateProjectException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes a project from the list. If the project is not found, throws {@code ProjectNotFoundException}.
     */
    public void remove(Project toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ProjectNotFoundException();
        }
    }

    /**
     * Returns an immutable reference to the internal list.
     */
    public ObservableList<Project> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Project> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UniqueProjectList projects = (UniqueProjectList) o;
        return Objects.equals(internalList, projects.internalList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalList);
    }
}
