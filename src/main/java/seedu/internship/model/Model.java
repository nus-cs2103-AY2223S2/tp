package seedu.internship.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.internship.commons.core.GuiSettings;
import seedu.internship.model.event.Event;
import seedu.internship.model.internship.Internship;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Internship> PREDICATE_SHOW_ALL_INTERNSHIPS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;

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
     * The internship content of {@code editedInternship} must not be the same as another existing internship in the
     * internship catalogue.
     */
    void setInternship(Internship target, Internship editedInternship);

    /** Returns an unmodifiable view of the filtered internship list */
    ObservableList<Internship> getFilteredInternshipList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredInternshipList(Predicate<Internship> predicate);

    //Events
    /**
     * Returns the user prefs' event catalogue file path.
     */
    Path getEventCatalogueFilePath();

    /**
     * Sets the user prefs' event catalogue file path.
     */
    void setEventCatalogueFilePath(Path eventCatalogueFilePath);

    /**
     * Replaces event catalogue data with the data in {@code eventCatalogue}.
     */
    void setEventCatalogue(ReadOnlyEventCatalogue eventCatalogue);

    /** Returns the EventCatalogue */
    ReadOnlyEventCatalogue getEventCatalogue();

    /**
     * Returns true if an event with the same contents as {@code event} exists in the event catalogue.
     */
    boolean hasEvent(Event event);

    /**
     * Deletes the given event.
     * The event must exist in the event catalogue.
     */
    void deleteEvent(Event target);

    /**
     * Adds the given event.
     * {@code event} must not already exist in the event catalogue.
     */
    void addEvent(Event event);

    /**
     * Replaces the given event {@code target} with {@code editedEvent}.
     * {@code target} must exist in the event.
     * The event content of {@code editedEvent} must not be the same as another existing event in the
     * event catalogue.
     */
    void setEvent(Event target, Event editedEvent);

    /**
     * Returns an unmodifiable view of the filtered event list.
     */
    ObservableList<Event> getFilteredEventList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);

    //Selected Internships Method

    /**
     * Updates Current Internship in Internship Catalogue.
     */
    public void updateSelectedInternship(Internship intern);

    /**
     * Clears Current Internship in Internship Catalogue.
     */
    public void clearSelectedInternship();

    /**
     * Returns true if current Internship Exists in Internship Catalogue.
     */
    public boolean hasSelectedInternship();

    /**
     * Return Current Internship in Internship Catalogue.
     */
    public Internship getSelectedInternship();

}

