package seedu.sprint.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.sprint.commons.core.GuiSettings;
import seedu.sprint.model.application.Application;

/**
 * The API of the ApplicationModel component.
 * This class should replace (i.e. be renamed to) Model eventually.
 * Comment to let merge operation detect file. To be deleted subsequently.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Application> PREDICATE_SHOW_ALL_APPLICATIONS = unused -> true;

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
     * Returns the user prefs' internship book file path.
     */
    Path getInternshipBookFilePath();

    /**
     * Sets the user prefs' internship book file path.
     */
    void setInternshipBookFilePath(Path internshipBookFilePath);

    /**
     * Replaces internship book data with the data in {@code internshipBook}.
     */
    void setInternshipBook(ReadOnlyInternshipBook internshipBook);

    /** Returns the InternshipBook */
    ReadOnlyInternshipBook getInternshipBook();

    /**
     * Returns true if a duplicate Application exists in the internship book.
     */
    boolean hasApplication(Application application);

    /**
     * Deletes the given application.
     * The Application must exist in the internship book.
     */
    void deleteApplication(Application target);

    /**
     * Adds the given application.
     * {@code application} must not already exist in the internship book.
     */
    void addApplication(Application application);

    /**
     * Replaces the given application {@code target} with {@code editedApplication}.
     * {@code target} must exist in the internship book.
     * {@code editedApplication} must not be the same as another existing application in the internship book.
     */
    void setApplication(Application target, Application editedApplication);

    /** Returns an unmodifiable view of the filtered application list */
    ObservableList<Application> getFilteredApplicationList();

    /**
     * Updates the filter of the filtered application list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredApplicationList(Predicate<Application> predicate);

    /** Returns an unmodifiable view of the sorted application list */
    ObservableList<Application> getSortedApplicationList();

    /**
     * Updates the sorting of the application list using the given comparator {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateSortedApplicationList(Comparator<Application> comparator);

    /**
     * Check if user can undo the internship book of the model.
     *
     * @return true if user can undo the internship book of the model; false otherwise.
     */
    boolean canUndoInternshipBook();

    /**
     * Check if user can redo the internship book of the model.
     *
     * @return true if user can redo the internship book of the model; false otherwise.
     */
    boolean canRedoInternshipBook();

    /**
     * Undo the internship book of the model.
     */
    void undoInternshipBook();

    /**
     * Redo the internship book of the model.
     */
    void redoInternshipBook();

    /**
     * Save change in internship book of the model.
     */
    void commitInternshipBookChange();

    /**
     * Checks if given application already has an existing task attached to it.
     */
    boolean applicationHasTask(Application application);

    /**
     * Creates a new Application with the Task we want to add.
     * @param target the application to add the task to.
     * @param editedApplication the new application with task added.
     */
    void addTaskToApplication(Application target, Application editedApplication);
}
