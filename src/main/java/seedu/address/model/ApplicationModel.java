package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.application.Application;

/**
 * The API of the ApplicationModel component.
 * This class should replace (i.e. be renamed to) Model eventually.
 * Comment to let merge operation detect file. To be deleted subsequently.
 */
public interface ApplicationModel {
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
}
