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
import seedu.address.model.tutee.Tutee;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TuteeManagingSystem tuteeManagingSystem;
    private final UserPrefs userPrefs;
    private final FilteredList<Tutee> filteredTutees;

    /**
     * Initializes a ModelManager with the given tuteeManagingSystem and userPrefs.
     */
    public ModelManager(ReadOnlyTuteeManagingSystem tuteeManagingSystem, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(tuteeManagingSystem, userPrefs);

        logger.fine("Initializing with address book: " + tuteeManagingSystem + " and user prefs " + userPrefs);

        this.tuteeManagingSystem = new TuteeManagingSystem(tuteeManagingSystem);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTutees = new FilteredList<>(this.tuteeManagingSystem.getPersonList());
    }

    public ModelManager() {
        this(new TuteeManagingSystem(), new UserPrefs());
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
    public Path getTuteeManagingSystemPath() {
        return userPrefs.getTuteeManagingSystemPath();
    }

    @Override
    public void setTuteeManagingSystemPath(Path tuteeManagingSystemPath) {
        requireNonNull(tuteeManagingSystemPath);
        userPrefs.setAddressBookFilePath(tuteeManagingSystemPath);
    }

    //=========== TuteeManagingSystem ================================================================================

    @Override
    public void setTuteeManagingSystem(ReadOnlyTuteeManagingSystem tuteeManagingSystem) {
        this.tuteeManagingSystem.resetData(tuteeManagingSystem);
    }

    @Override
    public ReadOnlyTuteeManagingSystem getTuteeManagingSystem() {
        return tuteeManagingSystem;
    }

    @Override
    public boolean hasTutee(Tutee tutee) {
        requireNonNull(tutee);
        return tuteeManagingSystem.hasPerson(tutee);
    }

    @Override
    public void deleteTutee(Tutee target) {
        tuteeManagingSystem.removePerson(target);
    }

    @Override
    public void addTutee(Tutee tutee) {
        tuteeManagingSystem.addPerson(tutee);
        updateFilteredTuteeList(PREDICATE_SHOW_ALL_TUTEES);
    }

    @Override
    public void setTutee(Tutee target, Tutee editedTutee) {
        requireAllNonNull(target, editedTutee);

        tuteeManagingSystem.setPerson(target, editedTutee);
    }

    //=========== Filtered Tutee List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Tutee} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Tutee> getFilteredTuteeList() {
        return filteredTutees;
    }

    @Override
    public void updateFilteredTuteeList(Predicate<Tutee> predicate) {
        requireNonNull(predicate);
        filteredTutees.setPredicate(predicate);
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
        return tuteeManagingSystem.equals(other.tuteeManagingSystem)
                && userPrefs.equals(other.userPrefs)
                && filteredTutees.equals(other.filteredTutees);
    }

}
