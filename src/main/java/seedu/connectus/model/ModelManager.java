package seedu.connectus.model;

import static java.util.Objects.requireNonNull;
import static seedu.connectus.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.connectus.commons.core.GuiSettings;
import seedu.connectus.commons.core.LogsCenter;
import seedu.connectus.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ConnectUs connectUS;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyConnectUs addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.connectUS = new ConnectUs(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.connectUS.getPersonList());
    }

    public ModelManager() {
        this(new ConnectUs(), new UserPrefs());
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
    public Path getConnectUsFilePath() {
        return userPrefs.getConnectUsFilePath();
    }

    @Override
    public void setConnectUsFilePath(Path connectUsFilePath) {
        requireNonNull(connectUsFilePath);
        userPrefs.setConnectUsFilePath(connectUsFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setConnectUs(ReadOnlyConnectUs connectUs) {
        this.connectUS.resetData(connectUs);
    }

    @Override
    public ReadOnlyConnectUs getConnectUs() {
        return connectUS;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return connectUS.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        connectUS.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        connectUS.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        connectUS.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
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
        return connectUS.equals(other.connectUS)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
