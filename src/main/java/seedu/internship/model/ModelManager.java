package seedu.internship.model;

import static java.util.Objects.requireNonNull;
import static seedu.internship.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.internship.commons.core.GuiSettings;
import seedu.internship.commons.core.LogsCenter;
import seedu.internship.model.event.Event;
import seedu.internship.model.internship.Internship;

/**
 * Represents the in-memory model of the internship catalogue data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final InternshipCatalogue internshipCatalogue;
    private final EventCatalogue eventCatalogue;
    private final UserPrefs userPrefs;
    private final FilteredList<Internship> filteredInternships;
    private final FilteredList<Event> filteredEvents;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyInternshipCatalogue internshipCatalogue, ReadOnlyEventCatalogue eventCatalogue,
                         ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(internshipCatalogue, eventCatalogue, userPrefs);

        logger.fine("Initializing with address book: " + "internships " + internshipCatalogue
                + ", events " + eventCatalogue + " and user prefs " + userPrefs);

        this.internshipCatalogue = new InternshipCatalogue(internshipCatalogue);
        this.eventCatalogue = new EventCatalogue(eventCatalogue);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredInternships = new FilteredList<>(this.internshipCatalogue.getInternshipList());
        filteredEvents = new FilteredList<>(this.eventCatalogue.getEventList());
    }

    public ModelManager() {
        this(new InternshipCatalogue(), new EventCatalogue(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getInternshipCatalogueFilePath() {
        return userPrefs.getInternshipCatalogueFilePath();
    }

    @Override
    public void setInternshipCatalogueFilePath(Path internshipCatalogueFilePath) {
        requireNonNull(internshipCatalogueFilePath);
        userPrefs.setInternshipCatalogueFilePath(internshipCatalogueFilePath);
    }

    @Override
    public Path getEventCatalogueFilePath() {
        return userPrefs.getEventCatalogueFilePath();
    }

    @Override
    public void setEventCatalogueFilePath(Path eventCatalogueFilePath) {
        requireNonNull(eventCatalogueFilePath);
        userPrefs.setEventCatalogueFilePath(eventCatalogueFilePath);
    }

    //=========== Internship Catalogue ================================================================================

    @Override
    public void setInternshipCatalogue(ReadOnlyInternshipCatalogue internshipCatalogue) {
        this.internshipCatalogue.resetData(internshipCatalogue);
    }

    @Override
    public ReadOnlyInternshipCatalogue getInternshipCatalogue() {
        return internshipCatalogue;
    }

    @Override
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return internshipCatalogue.hasInternship(internship);
    }

    @Override
    public void deleteInternship(Internship target) {
        internshipCatalogue.removeInternship(target);
    }

    @Override
    public void addInternship(Internship internship) {
        internshipCatalogue.addInternship(internship);
        updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
    }

    @Override
    public void setInternship(Internship target, Internship editedInternship) {
        requireAllNonNull(target, editedInternship);
        internshipCatalogue.setInternship(target, editedInternship);
    }

    //Current Internships Method
    @Override
    public void updateSelectedInternship(Internship intern) {
        this.internshipCatalogue.updateCurrent(intern);
    }

    @Override
    public void clearSelectedInternship() {
        this.internshipCatalogue.clearCurrent();
    }

    @Override
    public boolean hasSelectedInternship() {
        return this.internshipCatalogue.hasCurrent();
    }

    @Override
    public Internship getSelectedInternship() {
        return this.internshipCatalogue.getCurrent();
    }

    //=========== Event Catalogue ================================================================================
    @Override
    public void setEventCatalogue(ReadOnlyEventCatalogue eventCatalogue) {
        this.eventCatalogue.resetData(eventCatalogue);
    }

    @Override
    public ReadOnlyEventCatalogue getEventCatalogue() {
        return eventCatalogue;
    }

    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return eventCatalogue.hasEvent(event);
    }

    @Override
    public void deleteEvent(Event target) {
        eventCatalogue.removeEvent(target);
    }

    @Override
    public void addEvent(Event event) {
        eventCatalogue.addEvent(event);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);
        eventCatalogue.setEvent(target, editedEvent);
    }

    //=========== Filtered Internship List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Internship} backed by the internal list of
     * {@code versionedInternshipCatalogue}
     */
    @Override
    public ObservableList<Internship> getFilteredInternshipList() {
        return filteredInternships;
    }

    @Override
    public void updateFilteredInternshipList(Predicate<Internship> predicate) {
        requireNonNull(predicate);
        filteredInternships.setPredicate(predicate);
    }

    //=========== Filtered Event List Accessors =============================================================
    /**
     * Returns an unmodifiable view of the list of {@code Internship} backed by the internal list of
     * {@code versionedInternshipCatalogue}
     */
    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvents;
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        System.out.println(this.filteredInternships);
        System.out.println(other.filteredInternships);
        return internshipCatalogue.equals(other.internshipCatalogue)
                && eventCatalogue.equals(other.eventCatalogue)
                && userPrefs.equals(other.userPrefs)
                && filteredInternships.equals(other.filteredInternships)
                && filteredEvents.equals(other.filteredEvents);
    }

}

