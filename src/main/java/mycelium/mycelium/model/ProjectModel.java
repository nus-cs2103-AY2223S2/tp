package mycelium.mycelium.model;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import mycelium.mycelium.model.person.Person;
import mycelium.mycelium.model.project.Project;

/**
 * The API for operations related to projects.
 */
public interface ProjectModel {
    Predicate<Person> PREDICATE_SHOW_ALL_PROJECTS = unused -> true;

    /**
     * Finds a single project that matches the specified predicate. Expects to
     * find either zero or one projects. If more than one project matches the
     * provided predicate, throws {@code DuplicateProjectException}.
     */
    Optional<Project> getUniqueProject(Predicate<Project> predicate);

    /**
     * Checks if a project with the same name exists in Mycelium.
     */
    boolean hasProject(Project project); // TODO check if this should have a project's name overload

    /**
     * Deletes the project from Mycelium. If it does not exist, then this is a no-op.
     *
     * @param project The project to delete
     */
    void deleteProject(Project project);

    /**
     * Adds a project to Mycelium. If a project with the same name already exists, then a {@code
     * DuplicateProjectException} is thrown.
     *
     * @param project The new project
     */
    void addProject(Project project);

    /**
     * Replaces the project with the same name as {@code project} with {@code newProject}.
     */
    void setProject(Project project, Project newProject);

    /**
     * Retrieves a list of all the projects in Mycelium.
     *
     * @return The list of existing projects
     */
    ObservableList<Project> getFilteredProjectList();

    /**
     * Retrieves a list of all due projects within this or next week in Mycelium.
     *
     * @return The list of all due projects within this or next week
     */
    ObservableList<Project> getDueProjectList();

    /**
     * Retrieves a list of all overdue projects in Mycelium.
     *
     * @return The list of all overdue projects
     */
    ObservableList<Project> getOverdueProjectList();

    /**
     * Retrieves a hash map of status name and corresponding count of projects with that status.
     *
     * @return The hashmap of status name with corresponding project counts
     */
    HashMap<String, Long> getProjectStatistics();

    /**
     * Mutates the list of projects by filtering with some predicate.
     *
     * @param predicate The predicate to filter by
     */
    void updateFilteredProjectList(Predicate<Project> predicate);
}
