package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.application.Application;

/**
 * Represents the in-memory model of the address book data.
 * This class should replace (i.e. be renamed to) ModelManager eventually.
 * Comment to let merge operation detect file. To be deleted subsequently.
 */
public class ApplicationModelManager implements ApplicationModel {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final InternshipBook internshipBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Application> filteredApplications;

    /**
     * Initializes a ModelManager with the given applicationBook and userPrefs.
     */
    public ApplicationModelManager(ReadOnlyInternshipBook internshipBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(internshipBook, userPrefs);

        logger.fine("Initializing with application book: " + internshipBook + " and user prefs " + userPrefs);

        this.internshipBook = new InternshipBook(internshipBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredApplications = new FilteredList<>(this.internshipBook.getApplicationList());
    }

    public ApplicationModelManager() {
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
        this.internshipBook.resetData(internshipBook);
    }

    @Override
    public ReadOnlyInternshipBook getInternshipBook() {
        return internshipBook;
    }

    @Override
    public boolean hasApplication(Application application) {
        requireNonNull(application);
        return internshipBook.hasApplication(application);
    }

    @Override
    public void deleteApplication(Application target) {
        internshipBook.removeApplication(target);
    }

    @Override
    public void addApplication(Application application) {
        internshipBook.addApplication(application);
        updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
    }

    @Override
    public void setApplication(Application target, Application editedApplication) {
        requireAllNonNull(target, editedApplication);

        internshipBook.setApplication(target, editedApplication);
    }

    //=========== Filtered Application List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Application} backed by the internal list of
     * {@code versionedInternshipBook}
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

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ApplicationModelManager)) {
            return false;
        }

        // state check
        ApplicationModelManager other = (ApplicationModelManager) obj;
        return internshipBook.equals(other.internshipBook)
                && userPrefs.equals(other.userPrefs)
                && filteredApplications.equals(other.filteredApplications);
    }

}
