package arb.model.project;

import static arb.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import arb.model.client.Client;
import arb.model.project.exceptions.DuplicateProjectException;
import arb.model.project.exceptions.ProjectNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of projects that enforces uniqueness between its elements and does not allow nulls.
 * A project is considered unique by comparing using {@code Project#isSameProject(Project)}. As such,
 * adding and updating of projects uses Project#isSameProject(Project) for equality so as to ensure that
 * the project being added or updated is unique in terms of identity in the UniqueProjectList. However, the
 * removal of a project uses Project#equals(Object) so as to ensure that the project with exactly the same
 * fields will be removed.
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
     * Links {@code client} to {@code project}.
     */
    public void linkProjectToClient(Project project, Client client) {
        requireAllNonNull(project, client);
        project.linkToClient(client);
        setProject(project, project);
    }

    /**
     * Unlinks the linked client from {@code project}.
     */
    public void unlinkProjectFromClient(Project project) {
        requireNonNull(project);
        project.unlinkFromClient();
        setProject(project, project);
    }

    /**
     * Unlinks all linked clients from the projects in the list.
     */
    public void resetClientLinkings() {
        internalList.stream().forEach(p -> unlinkProjectFromClient(p));
    }

    /**
     * Transfers all projects linked to {@code original} to {@code target}.
     */
    public void transferLinkedProjects(Client original, Client target) {
        requireAllNonNull(original, target);
        Iterator<Project> linkedProjects = original.getLinkedProjects().iterator();
        while (linkedProjects.hasNext()) {
            Project linkedProject = linkedProjects.next();
            linkedProject.linkToClient(target);
            target.linkProject(linkedProject);
            setProject(linkedProject, linkedProject);
        }
    }

    /**
     * Unlinks all linked projects from {@code client}.
     */
    public void removeAllLinks(Client client) {
        requireNonNull(client);
        Iterator<Project> linkedProjectsIterator = client.getLinkedProjects().iterator();
        while (linkedProjectsIterator.hasNext()) {
            Project toRemove = linkedProjectsIterator.next();
            toRemove.unlinkFromClient();
            setProject(toRemove, toRemove);
        }
    }

    /**
     * Marks {@code project} as done.
     */
    public void markProjectAsDone(Project project) {
        requireNonNull(project);
        project.markAsDone();
        setProject(project, project);
    }

    /**
     * Marks {@code project} as not done.
     */
    public void markProjectAsNotDone(Project project) {
        requireNonNull(project);
        project.markAsUndone();
        setProject(project, project);
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
}
