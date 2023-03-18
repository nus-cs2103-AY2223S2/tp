package seedu.socket.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.socket.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.socket.model.project.Project.PROJ_DEADLINE;
import static seedu.socket.model.project.Project.PROJ_NAME;
import static seedu.socket.model.project.Project.PROJ_REPO_HOST;
import static seedu.socket.model.project.Project.PROJ_REPO_NAME;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.socket.model.project.exceptions.DuplicateProjectException;
import seedu.socket.model.project.exceptions.ProjectNotFoundException;

/**
 * A list of projects that enforces uniqueness between its elements and does not allow nulls.
 * A project is considered unique by comparing using {@code Project#isSameProject(Project)}. As such, adding and
 * updating of projects uses Project#isSameProject(Project) for equality so as to ensure that the project being added
 * or updated is unique in terms of identity in the UniqueProjectList. However, the removal of a project uses
 * Project#equals(Object) so as to ensure that the project with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Project#isSameProject(Project)
 */
public class UniqueProjectList implements Iterable<Project> {
    private final ObservableList<Project> internalList = FXCollections.observableArrayList();
    private final ObservableList<Project> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent project as the given argument.
     */
    public boolean contains(Project toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameProject);
    }

    /**
     * Adds a project to the list.
     * The project must not already exist in the list.
     */
    public void add(Project toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateProjectException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the project {@code target} in the list with {@code editedProject}.
     * {@code target} must exist in the list.
     * The project identity of {@code editedProject} must not be the same as another existing project in the list.
     */
    public void setProject(Project target, Project editedProject) {
        requireAllNonNull(target, editedProject);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ProjectNotFoundException();
        }

        if (!target.isSameProject(editedProject) && contains(editedProject)) {
            throw new DuplicateProjectException();
        }

        internalList.set(index, editedProject);
    }

    /**
     * Removes the equivalent project from the list.
     * The project must exist in the list.
     */
    public void remove(Project toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ProjectNotFoundException();
        }
    }

    public void setProjects(UniqueProjectList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code projects}.
     * {@code projects} must not contain duplicate projects.
     */
    public void setProjects(List<Project> projects) {
        requireAllNonNull(projects);
        if (!projectsAreUnique(projects)) {
            throw new DuplicateProjectException();
        }

        internalList.setAll(projects);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Project> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Project> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniqueProjectList // instanceof handles nulls
            && internalList.equals(((UniqueProjectList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code projects} contains only unique projects.
     */
    private boolean projectsAreUnique(List<Project> projects) {
        for (int i = 0; i < projects.size() - 1; i++) {
            for (int j = i + 1; j < projects.size(); j++) {
                if (projects.get(i).isSameProject(projects.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Sorts the list by given {@code category}.
     * @param category
     */
    public void sort(String category) {
        if (category.equals(PROJ_NAME)) {
            internalList.sort(Comparator.comparing((Project a) -> a.getName().toString().toLowerCase()));
        } else if (category.equals(PROJ_REPO_HOST)) {
            internalList.sort((Project a, Project b) -> {
                if (a.getRepoHost().toString().isEmpty() && b.getRepoHost().toString().isEmpty()) {
                    return a.getName().toString().toLowerCase().compareTo(b.getName().toString().toLowerCase());
                } else if (a.getRepoHost().toString().isEmpty()) {
                    return 1;
                } else if (b.getRepoHost().toString().isEmpty()) {
                    return -1;
                } else {
                    return a.getRepoHost().toString().toLowerCase().compareTo(b.getRepoHost().toString().toLowerCase());
                }
            });
        } else if (category.equals(PROJ_REPO_NAME)) {
            internalList.sort((Project a, Project b) -> {
                if (a.getRepoName().toString().isEmpty() && b.getRepoName().toString().isEmpty()) {
                    return a.getName().toString().toLowerCase().compareTo(b.getName().toString().toLowerCase());
                } else if (a.getRepoName().toString().isEmpty()) {
                    return 1;
                } else if (b.getRepoName().toString().isEmpty()) {
                    return -1;
                } else {
                    return a.getRepoName().toString().toLowerCase().compareTo(b.getRepoName().toString().toLowerCase());
                }
            });
        } else if (category.equals(PROJ_DEADLINE)) {
            internalList.sort((Project a, Project b) -> {
                if (a.getDeadline().toString().isEmpty() && b.getDeadline().toString().isEmpty()) {
                    return a.getName().toString().compareTo(b.getName().toString());
                } else if (a.getDeadline().toString().isEmpty()) {
                    return 1;
                } else if (b.getDeadline().toString().isEmpty()) {
                    return -1;
                } else {
                    return a.getDeadline().toLocalDateTime().compareTo(b.getDeadline().toLocalDateTime());
                }
            });
        }
    }
}
