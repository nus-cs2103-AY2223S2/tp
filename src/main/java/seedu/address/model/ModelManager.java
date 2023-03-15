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
import seedu.address.model.internship.Internship;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final InternBuddy internBuddy;
    private final UserPrefs userPrefs;
    private final FilteredList<Internship> filteredInternships;

    /**
     * Initializes a ModelManager with the given internBuddy and userPrefs.
     */
    public ModelManager(ReadOnlyInternBuddy internBuddy, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(internBuddy, userPrefs);

        logger.fine("Initializing with address book: " + internBuddy + " and user prefs " + userPrefs);

        this.internBuddy = new InternBuddy(internBuddy);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredInternships = new FilteredList<>(this.internBuddy.getInternshipList());
    }

    public ModelManager() {
        this(new InternBuddy(), new UserPrefs());
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
    public Path getInternBuddyFilePath() {
        return userPrefs.getInternBuddyFilePath();
    }

    @Override
    public void setInternBuddyFilePath(Path internBuddyFilePath) {
        requireNonNull(internBuddyFilePath);
        userPrefs.setInternBuddyFilePath(internBuddyFilePath);
    }

    //=========== InternBuddy ================================================================================

    @Override
    public void setInternBuddy(ReadOnlyInternBuddy internBuddy) {
        this.internBuddy.resetData(internBuddy);
    }

    @Override
    public ReadOnlyInternBuddy getInternBuddy() {
        return internBuddy;
    }

    @Override
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return internBuddy.hasInternship(internship);
    }

    @Override
    public void deleteInternship(Internship target) {
        internBuddy.removeInternship(target);
    }

    @Override
    public void addInternship(Internship internship) {
        internBuddy.addInternship(internship);
        updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
    }

    @Override
    public void setInternship(Internship target, Internship editedInternship) {
        requireAllNonNull(target, editedInternship);

        internBuddy.setInternship(target, editedInternship);
    }

    //=========== Filtered Internship List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Internship} backed by the internal list of
     * {@code versionedInternBuddy}
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
        return internBuddy.equals(other.internBuddy)
                && userPrefs.equals(other.userPrefs)
                && filteredInternships.equals(other.filteredInternships);
    }

}
