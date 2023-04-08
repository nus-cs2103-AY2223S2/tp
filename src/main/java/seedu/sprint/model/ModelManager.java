package seedu.sprint.model;

import static java.util.Objects.requireNonNull;
import static seedu.sprint.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.sprint.commons.core.GuiSettings;
import seedu.sprint.commons.core.LogsCenter;
import seedu.sprint.model.application.Application;

/**
 * Represents the in-memory model of the sprint book data.
 * This class should replace (i.e. be renamed to) ModelManager eventually.
 * Comment to let merge operation detect file. To be deleted subsequently.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedInternshipBook versionedInternshipBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Application> filteredApplications;
    private final SortedList<Application> sortedApplications;

    /**
     * Initializes a ModelManager with the given applicationBook and userPrefs.
     */
    public ModelManager(ReadOnlyInternshipBook internshipBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(internshipBook, userPrefs);

        logger.fine("Initializing with application book: " + internshipBook + " and user prefs " + userPrefs);

        this.versionedInternshipBook = new VersionedInternshipBook(internshipBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredApplications = new FilteredList<>(versionedInternshipBook.getApplicationList());
        sortedApplications = new SortedList<>(filteredApplications);
    }

    public ModelManager() {
        this(new InternshipBook(), new UserPrefs());
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
    public Path getInternshipBookFilePath() {
        return userPrefs.getInternshipBookFilePath();
    }

    @Override
    public void setInternshipBookFilePath(Path internshipBookFilePath) {
        requireNonNull(internshipBookFilePath);
        userPrefs.setInternshipBookFilePath(internshipBookFilePath);
    }

    //=========== InternshipBook ================================================================================

    @Override
    public void setInternshipBook(ReadOnlyInternshipBook internshipBook) {
        versionedInternshipBook.resetData(internshipBook);
    }

    @Override
    public ReadOnlyInternshipBook getInternshipBook() {
        return versionedInternshipBook;
    }

    @Override
    public boolean hasApplication(Application application) {
        requireNonNull(application);
        return versionedInternshipBook.hasApplication(application);
    }

    @Override
    public void deleteApplication(Application target) {
        versionedInternshipBook.removeApplication(target);
    }

    @Override
    public void addApplication(Application application) {
        versionedInternshipBook.addApplication(application);
        updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
    }

    @Override
    public void setApplication(Application target, Application editedApplication) {
        requireAllNonNull(target, editedApplication);
        versionedInternshipBook.setApplication(target, editedApplication);
    }

    @Override
    public boolean applicationHasTask(Application application) {
        return application.hasTask();
    }

    @Override
    public void addTaskToApplication(Application target, Application editedApplication) {
        versionedInternshipBook.setApplication(target, editedApplication);
    }

    //=========== Filtered Application List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Application} backed by the internal list of
     * {@code versionedInternshipBook}.
     */
    @Override
    public ObservableList<Application> getFilteredApplicationList() {
        return filteredApplications;
    }

    @Override
    public void updateFilteredApplicationList(Predicate<Application> predicate) {
        requireNonNull(predicate);
        filteredApplications.setPredicate(predicate);
    }

    /**
     * Returns an unmodifiable view of the sorted list of {@code Application} backed by the internal list of
     * {@code versionedInternshipBook}.
     */
    @Override
    public ObservableList<Application> getSortedApplicationList() {
        return sortedApplications;
    }

    @Override
    public void updateSortedApplicationList(Comparator<Application> comparator) {
        requireNonNull(comparator);
        sortedApplications.setComparator(comparator);
    }

    //=========== Handle undo and redo commands =============================================================

    @Override
    public boolean canUndoInternshipBook() {
        return versionedInternshipBook.canUndo();
    }

    @Override
    public boolean canRedoInternshipBook() {
        return versionedInternshipBook.canRedo();
    }

    @Override
    public void undoInternshipBook() {
        versionedInternshipBook.undo();
    }

    @Override
    public void redoInternshipBook() {
        versionedInternshipBook.redo();
    }

    @Override
    public void commitInternshipBookChange() {
        versionedInternshipBook.commit();
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
        return versionedInternshipBook.equals(other.versionedInternshipBook)
                && userPrefs.equals(other.userPrefs)
                && filteredApplications.equals(other.filteredApplications)
                && sortedApplications.equals(other.sortedApplications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(versionedInternshipBook, userPrefs, filteredApplications, sortedApplications);
    }
}
