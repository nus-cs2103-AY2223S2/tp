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
import seedu.address.model.person.Person;
import seedu.address.model.person.User;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final EduMate eduMate;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given eduMate and userPrefs.
     */
    public ModelManager(ReadOnlyEduMate eduMate, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(eduMate, userPrefs);

        logger.fine("Initializing with address book: " + eduMate + " and user prefs " + userPrefs);

        this.eduMate = new EduMate(eduMate);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.eduMate.getPersonList());
    }

    public ModelManager() {
        this(new EduMate(), new UserPrefs());
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
    public Path getEduMateFilePath() {
        return userPrefs.getEduMateFilePath();
    }

    @Override
    public void setEduMateFilePath(Path eduMateFilePath) {
        requireNonNull(eduMateFilePath);
        userPrefs.setEduMateFilePath(eduMateFilePath);
    }

    //=========== EduMate ================================================================================

    @Override
    public void setEduMate(ReadOnlyEduMate eduMate) {
        this.eduMate.resetData(eduMate);
    }

    @Override
    public ReadOnlyEduMate getEduMate() {
        return eduMate;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return eduMate.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        eduMate.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {

        eduMate.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        eduMate.setPerson(target, editedPerson);
    }

    @Override
    public void resetPersons() {
        eduMate.resetPersons();
    }

    @Override
    public User getUser() {
        return eduMate.getUser();
    }

    @Override
    public void setUser(User user) {
        requireNonNull(user);

        eduMate.setUser(user);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedEduMate}
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
        return eduMate.equals(other.eduMate)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
