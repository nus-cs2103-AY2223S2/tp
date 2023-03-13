package seedu.patientist.model;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.patientist.commons.core.GuiSettings;
import seedu.patientist.commons.core.LogsCenter;
import seedu.patientist.model.person.Person;

/**
 * Represents the in-memory model of the patientist book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Patientist patientist;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given patientist and userPrefs.
     */
    public ModelManager(ReadOnlyPatientist patientist, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(patientist, userPrefs);

        logger.fine("Initializing with patientist book: " + patientist + " and user prefs " + userPrefs);

        this.patientist = new Patientist(patientist);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.patientist.getPersonList());
    }

    public ModelManager() {
        this(new Patientist(), new UserPrefs());
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
    public Path getPatientistFilePath() {
        return userPrefs.getPatientistFilePath();
    }

    @Override
    public void setPatientistFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setPatientistFilePath(addressBookFilePath);
    }

    //=========== Patientist ================================================================================

    @Override
    public void setPatientist(ReadOnlyPatientist patientist) {
        this.patientist.resetData(patientist);
    }

    @Override
    public ReadOnlyPatientist getPatientist() {
        return patientist;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return patientist.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        patientist.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        patientist.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        patientist.setPerson(target, editedPerson);
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
        return patientist.equals(other.patientist)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
