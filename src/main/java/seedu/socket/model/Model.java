package seedu.socket.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.socket.commons.core.GuiSettings;
import seedu.socket.model.person.Person;
import seedu.socket.model.project.Project;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Project> PREDICATE_SHOW_ALL_PROJECTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' {@code Socket} file path.
     */
    Path getSocketFilePath();

    /**
     * Sets the user prefs' {@code Socket} file path.
     */
    void setSocketFilePath(Path socketFilePath);

    /**
     * Replaces {@code Socket} data with the data in {@code socket}.
     */
    void setSocket(ReadOnlySocket socket);

    /** Returns the {@code Socket} */
    ReadOnlySocket getSocket();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the {@code Socket}.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the {@code Socket}.
     */
    void deletePerson(Person target);

    /**
     * Deletes persons by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    boolean hasDeleteMultiplePerson(Predicate<Person> predicate);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the {@code Socket}.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the {@code Socket}.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the
     * {@code Socket}.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns a person to be viewed */
    ObservableList<Person> getViewedPerson();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the person to be viewed in filtered person list by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateViewedPerson(Person person);

    /**
     * Sorts the list of people by given {@code category}
     */
    void sortPersonList(String category);

    /**
     * Returns true if a project with the same identity as {@code project} exists in the {@code Socket}.
     */
    boolean hasProject(Project project);

    /**
     * Deletes the given project.
     * The project must exist in the {@code Socket}.
     */
    void deleteProject(Project target);

    /**
     * Adds the given project.
     * {@code project} must not already exist in the {@code Socket}.
     */
    void addProject(Project project);

    /**
     * Replaces the given project {@code target} with {@code editedProject}.
     * {@code target} must exist in the {@code Socket}.
     * The project identity of {@code editedProject} must not be the same as another existing project in the
     * {@code Socket}.
     */
    void setProject(Project target, Project editedProject);

    /**
     * Replaces the contents of the project list with {@code projects}.
     * {@code projects} must not contain duplicate projects.
     */
    void setProjects(List<Project> projects);

    /** Returns an unmodifiable view of the filtered project list */
    ObservableList<Project> getFilteredProjectList();

    /** Returns a project to be viewed */
    ObservableList<Project> getViewedProject();

    /**
     * Updates the filter of the filtered project list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredProjectList(Predicate<Project> predicate);

    /**
     * Updates the project to be viewed in filtered person list by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateViewedProject(Project project);

    /**
     * Sorts the list of projects by given {@code category}
     */
    void sortProjectList(String category);

    /**
     * Saves the current {@code Socket} state.
     */
    void commitSocket();

    /**
     * Restores the previous {@code Socket} state.
     */
    void undoSocket();

    /**
     * Restores a previously undone {@code Socket} state.
     */
    void redoSocket();

    /**
     * Checks if there are any previous {@code Socket} states to restore.
     */
    boolean canUndoSocket();

    /**
     * Checks if there are any undone {@code Socket} states to restore.
     */
    boolean canRedoSocket();
}
