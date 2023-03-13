package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.internship.Internship;

import java.nio.file.Path;
import java.util.function.Predicate;

/**
 * The API of the Model component.
 */
public interface Model1 {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Internship> PREDICATE_SHOW_ALL_INTERNSHIPS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs1 userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs1 getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' internship catalogue file path.
     */
    Path getInternshipCatalogueFilePath();

    /**
     * Sets the user prefs' internship catalogue file path.
     */
    void setInternshipCatalogueFilePath(Path internshipCatalogueFilePath);

    /**
     * Replaces internship catalogue data with the data in {@code internshipCatalogue}.
     */
    void setInternshipCatalogue(ReadOnlyInternshipCatalogue internshipCatalogue);

    /** Returns the InternshipCatalogue */
    ReadOnlyInternshipCatalogue getInternshipCatalogue();

    /**
     * Returns true if a internship with the same contents as {@code internship} exists in the internship catalogue.
     */
    boolean hasInternship(Internship internship);

    /**
     * Deletes the given internship.
     * The internship must exist in the internship catalogue.
     */
    void deleteInternship(Internship target);

    /**
     * Adds the given internship.
     * {@code internship} must not already exist in the internship catalogue.
     */
    void addInternship(Internship internship);

    /**
     * Replaces the given internship {@code target} with {@code editedInternship}.
     * {@code target} must exist in the internship.
     * The internship content of {@code editedInternship} must not be the same as another existing internship in the internship catalogue.
     */
    void setInternship(Internship target, Internship editedInternship);

    /** Returns an unmodifiable view of the filtered internship list */
    ObservableList<Internship> getFilteredInternshipList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredInternshipList(Predicate<Internship> predicate);
}

