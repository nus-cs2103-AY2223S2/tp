package mycelium.mycelium.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import mycelium.mycelium.model.project.Project;

/**
 * The API for operations related to projects.
 */
public interface ProjectModel {
    /**
     * Checks if a project with the same name exists in Mycelium.
     */
    boolean hasProject(Project project);

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
     * Retrieves a list of all the projects in Mycelium.
     *
     * @return The list of existing projects
     */
    ObservableList<Project> getFilteredProjectList();


    /**
     * Mutates the list of projects by filtering with some predicate.
     *
     * @param predicate The predicate to filter by
     */
    void updateFilteredProjectList(Predicate<Project> predicate);
}
